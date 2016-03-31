package com.example.debugdemo;


import com.develop.wiki.R;
import android.os.Bundle;
import develop.wiki.android.global.BaseActivity;
import develop.wiki.android.global.debug.LogUtil;

public class CrashTestActivity extends BaseActivity{
	private static final String TAG = "CrashTestActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_crashtest);
		LogUtil.d("CrashTestActivity", "oncreate");
		monitorNativeCrash();
	}
	
	@Override
	protected String getTag() {
		// TODO Auto-generated method stub
		return TAG;
	}
	
	native void monitorNativeCrash();
	
}
