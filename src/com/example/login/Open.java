package com.example.login;

import com.example.loadhtml.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Open extends Activity {
   	public void onPause()
   	{
   		super.onPause();
   		finish();
   		
   	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("data","onCreate is coming ");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.open);
          OpenView();

		
		
	}

public void  OpenView()
{new Thread(new Runnable(){
     public void run()
     {
    	 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 Intent intent=new Intent(Open.this,Login.class);
    	 startActivity(intent);
    	 finish();
    	
    	 
     }}
		).start();



}}
