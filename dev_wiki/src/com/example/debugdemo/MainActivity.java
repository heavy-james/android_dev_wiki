package com.example.debugdemo;

import com.develop.wiki.R;

import debug.util.ActivityCapturer;
import debug.util.UIService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_charge_activity);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        float density = getResources().getDisplayMetrics().density;

        Log.d("zhf", "wdith-->" + width + ";height-->" + height + ";density-->" + density);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Button button = (Button) view;
                Intent intent = new Intent(MainActivity.this, UIService.class);
                if (button.getText().equals("startCaptureUI"))
                {
                    startService(intent);
                    button.setText("stopCaptureUI");
                }
                else
                {
                    stopService(intent);
                    button.setText("startCaptureUI");
                }
            }
        });;
    }
}
