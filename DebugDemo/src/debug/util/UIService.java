package debug.util;

import java.lang.ref.WeakReference;

import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class UIService extends Service {

	private String mLogStr;
	private String mFrameInfoStr;
	private String mActInfoStr;
	private static ActivityManager mAManager;
	private static final int MSG_UPDATE_UI_INFO = 0x0001;
	private static final int MSG_UPDATE_CONTENT_VIEW = 0x0002;
	private static final int MSG_UPDATE_FRAME_INFO = 0x0003;
	
	private BroadcastReceiver mFrameInfoReciever;
	
	@Override
	public void onCreate() {
		Log.d("testest", "service oncreate");
		ActivityCapturer cap = ActivityCapturer.getInstance(getApplicationContext());
		cap.showInfo();
		mAManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//		HandlerThread ht;
//		ht.start();
		final UIHandler mHandler = new UIHandler(this);
		mHandler.sendEmptyMessage(MSG_UPDATE_UI_INFO);
		mFrameInfoReciever = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				if("com.ktcp.tentcent.frame.detect".equals(intent.getAction())){
					Log.d("zhf", "frame info on recieve name-->" + intent.getStringExtra("name"));
					Message msg = mHandler.obtainMessage();
					msg.what = MSG_UPDATE_FRAME_INFO;
					msg.obj = intent;
					mHandler.sendMessage(msg);
				}
			}
		};
		
		if(mFrameInfoReciever != null){
			Log.d("zhf", "onbind register reciever");
			IntentFilter filter = new IntentFilter();
			filter.addAction("com.ktcp.tentcent.frame.detect");;
			registerReceiver(mFrameInfoReciever, filter);
		}
		
		super.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		ActivityCapturer cap = ActivityCapturer.getInstance(getApplicationContext());
		cap.hideInfo();
		if(mFrameInfoReciever != null){
			unregisterReceiver(mFrameInfoReciever);
		}
		super.onDestroy();
	}
	
	
	static class UIHandler extends Handler {
		
		private WeakReference<UIService> ref;
		
		UIHandler(UIService service){
			ref = new WeakReference<UIService>(service);
		}
		
		@Override
		public void handleMessage(Message msg) {
			
			if(ref.get() == null){
				return;
			}
			
			switch (msg.what) {
			case MSG_UPDATE_CONTENT_VIEW:
				ref.get().mLogStr = ref.get().mActInfoStr + "\n" + ref.get().mFrameInfoStr;
				Log.d("testest", "mLogStr-->" + ref.get().mLogStr);
				ActivityCapturer.getInstance(ref.get().getApplicationContext()).updateUI(ref.get().mLogStr);
				break;
			case MSG_UPDATE_UI_INFO:
				ComponentName componentName = ref.get().mAManager.getRunningTasks(1).get(0).topActivity;
				String componentInfoStr = componentName.getPackageName() + "/" + componentName.getClassName();
				if(!componentInfoStr.equals(ref.get().mActInfoStr)){
					ref.get().mActInfoStr = componentInfoStr;
					sendEmptyMessageDelayed(MSG_UPDATE_CONTENT_VIEW, 100);
				}
				sendEmptyMessageDelayed(MSG_UPDATE_UI_INFO, 3000);
				break;
			case MSG_UPDATE_FRAME_INFO:
				Intent intent = (Intent)msg.obj;
				Log.d("zhf","handle message obj -->" + msg.obj);
				if(intent != null){
					int type = intent.getIntExtra("type", -1);
					String framekey = intent.getStringExtra("framekey");
					String name = intent.getStringExtra("name");
					String extras = intent.getStringExtra("extras");
					ref.get().mFrameInfoStr = "Frame Info--> type : " + type + ";name : " + name + "; extras : " + extras;
					sendEmptyMessageDelayed(MSG_UPDATE_CONTENT_VIEW, 100);
				}
				break;
				
			}
		}
	}

}
