package develop.wiki.android.global;

import develop.wiki.android.global.debug.LogUtil;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;

public class WiKiApplication extends Application
{

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.init(this, LogUtil.LEVEL_DEBUG, "/sdcard/android_dev_wiki_log/log.txt", null);
		LogUtil.setMode(false, true, false);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
	}

	@Override
	public void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		// TODO Auto-generated method stub
		super.registerActivityLifecycleCallbacks(callback);
	}

	@Override
	public void registerComponentCallbacks(ComponentCallbacks callback) {
		// TODO Auto-generated method stub
		super.registerComponentCallbacks(callback);
	}

	@Override
	public void registerOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		// TODO Auto-generated method stub
		super.registerOnProvideAssistDataListener(callback);
	}

	@Override
	public void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		// TODO Auto-generated method stub
		super.unregisterActivityLifecycleCallbacks(callback);
	}

	@Override
	public void unregisterComponentCallbacks(ComponentCallbacks callback) {
		// TODO Auto-generated method stub
		super.unregisterComponentCallbacks(callback);
	}

	@Override
	public void unregisterOnProvideAssistDataListener(
			OnProvideAssistDataListener callback) {
		// TODO Auto-generated method stub
		super.unregisterOnProvideAssistDataListener(callback);
	}
    
}
