package com.example.braillekeyboard;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.AssetManager.AssetInputStream;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
				//do something
				dataLoad();
				
				finish();
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
			}
		};
		handler.sendEmptyMessageDelayed(0, 1000);
	}
	
	private void dataLoad()
	{
		AssetManager assetManager = getResources().getAssets();
		BufferedInputStream buf;
		
		int key;
		String ch;
		try{
			AssetInputStream ais = (AssetInputStream)assetManager.open(BarilleDataSet.SINGLETON.basicJsonFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(ais));
			StringBuilder sb = new StringBuilder();
			int bufferSize = 1024 * 1024;
			
			char readBuf [] = new char[bufferSize];
			int resultSize = 0;
			while((resultSize = br.read(readBuf)) != -1){
				if(resultSize == bufferSize){
					sb.append(readBuf);
				}else{
					for(int i=0; i <resultSize; i++){
						sb.append(readBuf[i]);
					}
				}
			}
			
			String jString = sb.toString();
			
			JSONArray jArr = new JSONArray(jString);
			for(int i=0; i<jArr.length(); i++)
			{
				JSONObject jsonObject = new JSONObject(jArr.getString(i).toString());
				
				key = Integer.parseInt(jsonObject.getString("key").toString());
				ch = jsonObject.getString("char").toString();
				
				BarilleDataSet.SINGLETON.getBasic().put(key, ch);
			}
			
		}catch(JSONException je){
			Log.e("jsonErr","json error",je);
		}catch(Exception e){
			Log.e("exception","no file",e);
		}
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
