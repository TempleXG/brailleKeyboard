package com.example.braillekeyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
//import android.view.Menu;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		initialize();
	}

	private void initialize(){
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				finish();
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
			}
		};
		handler.sendEmptyMessageDelayed(0, 200);
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
}
