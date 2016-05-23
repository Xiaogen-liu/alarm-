package com.example.login;

import com.example.loadhtml.MainActivity;
import com.example.loadhtml.R;
import com.example.loadhtml.*;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
      // AutoCompleteTextView cardNumAuto;
       EditText passwordET;
       Button logBT;
       EditText account;
       CheckBox savePasswordCB;
       SharedPreferences sp;
       String cardNumStr;
       String passwordStr;
       SharedPreferences.Editor editor;
   	//定时
   	public void TimerLi() 
   	{
   		      new Thread(new Runnable(){
   	         	public void run() {
   	   		// TODO Auto-generated method stub
   	   		try {
   				Thread.sleep(2000);
   				
   				  Intent intent=new Intent(Login.this,MainActivity.class);
   			        startActivity(intent);
   			     finish();
   			} catch (InterruptedException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	   	}});
  	
   	}
	
   	public void onPause()
   	{
   		super.onPause();
   		finish();
   		
   	}
   	
   	
   	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		//cardNumAuto=(AutoCompleteTextView)findViewById(R.id.cardNumAuto);
		passwordET=(EditText)findViewById(R.id.password_edit);
		logBT=(Button)findViewById(R.id.login);
		account=(EditText)findViewById(R.id.account_edit);
		savePasswordCB=(CheckBox)findViewById(R.id.checkBox1);
		passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
		// 隐藏密码为InputType.TYPE_TEXT_VARIATION_PASSWORD，也就是0x81
		// 显示密码为InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD，也就是0x91
		
		//按钮按键效果
		logBT.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v,MotionEvent event)
			{
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{
					logBT.setBackgroundColor(Color.GRAY);
				}
				else if(event.getAction()==MotionEvent.ACTION_UP)
				{
					logBT.setBackgroundColor(Color.parseColor("#99CC33"));
					
				}
				return false;
			}
			
			
		}
				
				);
		
		
		
		
		sp=PreferenceManager.getDefaultSharedPreferences(this);
		boolean isRemember=sp.getBoolean("remember", false);
		if(isRemember)
		{
			String account1=sp.getString("Account", "");
			String password1=sp.getString("Password", "");
			
			Log.d("data","读取成功");
			account.setText(account1);
			passwordET.setText(password1);
		      savePasswordCB.setChecked(true);	
		      
		      
		      
		      
		      
		      
			
		}
		
		
		
		
		
		
		

		logBT.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				cardNumStr=account.getText().toString();
				passwordStr=passwordET.getText().toString();
				Log.d("data","carNumStr id "+cardNumStr);
				Log.d("data","passwordStr id "+passwordStr);
				
				String account2=account.getText().toString();
				String password2=passwordET.getText().toString();
				editor=sp.edit();
				
				if(cardNumStr.equals("小根")&&passwordStr.equals("1234"))
				{
					if(savePasswordCB.isChecked())//登录才保存密码
					{
						//sp.edit().putString(cardNumStr, passwordStr).commit();
						//隐藏密码
					passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				   //存储数据，密码及账号
					editor.putBoolean("remember", true);
					editor.putString("Account", account2);
					editor.putString("Password", password2);
					
					
					}
					else{ editor.clear();}//CheckBox没有选中，就清除数据
					editor.commit();//存入数据
					Toast.makeText(Login.this, "登录成功，请点击", Toast.LENGTH_SHORT).show();
					//开启定时
					TimerLi();
	//////////////////////////////////////
					Intent intent1=new Intent(Login.this,MainActivity.class);
					intent1.putExtra("account","小根");
					startActivity(intent1);
					}
				else if((cardNumStr.equals("雪亮")&&passwordStr.equals("zb304")))
				{
					if(savePasswordCB.isChecked())//登录才保存密码
					{
						//隐藏密码
					passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
					   //存储数据，密码及账号
						editor.putBoolean("remember", true);
						editor.putString("Account", account2);
						editor.putString("Password", password2);
					
					
					
					}		else{ editor.clear();}//CheckBox没有选中，就清除数据
					editor.commit();//存入数据
					
					
					Toast.makeText(Login.this, "登录成功，正在为您加加油油", Toast.LENGTH_SHORT).show();
					
					
					
					
					
					//开启定时
					TimerLi();
					Intent intent1=new Intent(Login.this,MainActivity.class);
					//向下一个传递信息
					intent1.putExtra("account","雪亮");
					startActivity(intent1);
					
					
				}

				else if((cardNumStr.equals("思华")&&passwordStr.equals("zb304"))){
					if(savePasswordCB.isChecked())//登录才保存密码
					{
						//隐藏密码
					passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
					   //存储数据，密码及账号
						editor.putBoolean("remember", true);
						editor.putString("Account", account2);
						editor.putString("Password", password2);
					
					
					}
					else{ editor.clear();}//CheckBox没有选中，就清除数据
					editor.commit();//存入数据
					Toast.makeText(Login.this, "登录成功，正在为您加加油油", Toast.LENGTH_SHORT).show();
					//开启定时
					TimerLi();
					Intent intent1=new Intent(Login.this,MainActivity.class);
					//向下一个传递信息
					intent1.putExtra("account","思华");
					startActivity(intent1);
					
					
					
					
					

				}
				else {
					       Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
					
				}
				
				
			}
		
			
			
		}	
				);
		
		
		
		//文字监听
		passwordET.addTextChangedListener(new TextWatcher()
		{
			public void afterTextChanged(Editable s)
			{
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
				if(passwordET.getText().toString()!=null)
				{
					logBT.setBackgroundColor(Color.GREEN);
				}
				else {
					
					logBT.setBackgroundColor(Color.GRAY);
				}
				
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if(passwordET.getText().toString()!=null)
				{
					logBT.setBackgroundColor(Color.GREEN);
				}
				else {
					logBT.setBackgroundColor(Color.GRAY);
				}
				
			}
			
		});
		
		
		
		
		
		
		savePasswordCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					//显示密码
					passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					
				}
				else{
					//隐藏密码
					passwordET.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
			}
			
			
			
		});
		

		
		
	}




}
	



