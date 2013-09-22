package com.example.braillekeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private InputMethodManager imm;
	private EditText editText;
	private int viewHeight, viewWidth;
	private Button[] btn = new Button[6];
	private Vibrator vibrator;
	private TouchView touchView;
	private int homeCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		touchView = (TouchView)this.findViewById(R.id.touchViewID);
		
		
		btn[0] = (Button)this.findViewById(R.id.btn0);
		btn[1] = (Button)this.findViewById(R.id.btn1);
		btn[2] = (Button)this.findViewById(R.id.btn2);
		btn[3] = (Button)this.findViewById(R.id.btn3);
		btn[4] = (Button)this.findViewById(R.id.btn4);
		btn[5] = (Button)this.findViewById(R.id.btn5);
		
		//touchView.setButton(btn);//view에 button 넘겨주기
		
		vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		
		touchView.setVibrator(vibrator);
		
		editText = (EditText)this.findViewById(R.id.editText);
		editText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			
			}
		});
		
		imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
				
	}
	
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
		touchView.setButton(btn);//view에 button 넘겨주기
		touchView.setEditText(this.editText);
		touchView.setHeight(touchView.getHeight());
		touchView.setWidth(touchView.getWidth());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public int getViewHeight(){
		return this.viewHeight;
	}
	public int getViewWidth(){
		return this.viewWidth;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_HOME)
		{			
			if(homeCount==0){
				homeCount++;
				
			}else if(homeCount==1){
				
				
				
			}
		}
		return true;		
	}
	
}
