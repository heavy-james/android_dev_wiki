package debug.util;

import java.lang.ref.WeakReference;

import android.R;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class ActivityCapturer {

	private Context mContext;
	private WindowManager mWManager;
	private TextView mContentView;
	private LayoutParams mLayoutParam;
	private static ActivityCapturer mInstance;
	private static final String LOG_SUFFIX = " UICapturer : ";

	public static synchronized ActivityCapturer getInstance(Context context) {
		if (null == mInstance) {
			Log.d("testest", "ActivityCapturer getInstance -->"
					+ Thread.currentThread().getId());
			mInstance = new ActivityCapturer();
			mInstance.initData(context);
		}
		return mInstance;
	}

	private void initData(Context context) {
		mContext = context;
		mWManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		mContentView = new TextView(mContext);
		mContentView.setTextSize(10);
		mContentView.setBackgroundColor(Color.TRANSPARENT);
		mContentView.setFocusable(false);
		int width = mContext.getResources().getDisplayMetrics().widthPixels / 3;
		int height = mContext.getResources().getDisplayMetrics().heightPixels / 20;
		mLayoutParam = new LayoutParams();
		mLayoutParam.type = LayoutParams.FIRST_SYSTEM_WINDOW + 1000;
		mLayoutParam.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		mLayoutParam.gravity = Gravity.LEFT | Gravity.TOP;
		mLayoutParam.x = 0;
		mLayoutParam.y = 0;
		mLayoutParam.width = width;
		mLayoutParam.height = height;
	}

	public void showInfo() {
		try {
			mWManager.addView(mContentView, mLayoutParam);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void hideInfo() {
		try {

			mWManager.removeView(mContentView);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateUI(String str) {
		Log.d("testest", "test addr-->" + mContentView.hashCode());
		mWManager.removeViewImmediate(mContentView);
		mContentView.setText("");
		mContentView.setText(LOG_SUFFIX + str);
		mContentView.invalidate();
		mWManager.addView(mContentView, mLayoutParam);
	}

}
