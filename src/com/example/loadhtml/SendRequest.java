package com.example.loadhtml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.example.loadhtml.MainActivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
 public class SendRequest implements OnClickListener {
	   public static final int SHOW_RESPONSE=0;
       public static int SHOW_Text=2;
	   private Vibrator vibrator;
	  
	 	private  Handler handler=new Handler()
     	{
     	public void handleMessage(Message msg)
     	{
     		
     		switch(msg.what)               //what是实例msg的一个成员变量,返回类型为int
     		{
     		case SHOW_RESPONSE:
     		{
     			Log.d("data","msg.what");
     			String response=(String)msg.obj;
     			if((SHOW_Text)!=(response.length()))
     			{
     				MainActivity.changeVibrator();
                    Log.d("data","changeVibrator is ok");
     			     
     			  
     		         SHOW_Text=response.length();           
     				Log.d("data","show_text is "+SHOW_Text);
     			}
     			
     			Log.d("data","msg is coming");
			    Log.d("data","insert is ok");
     			MainActivity.responseText.setText(response);

   
     				
     			}
     			
     
     		}
     		}
     	};
       	public void onClick(View v)
       	{
       		if(v.getId()==R.id.button_action)
       		{
       			Log.d("data","button_action is coming");
       	           sendRequest();
       		}
       		if(v.getId()==R.id.button_stop)
       		{
       		      
       		    Log.d("data","stopSelf is ok");	
       		    
       		}
       	}
	   
	   
	   
    	private Timer timer=new Timer(true);
		public  void sendRequest()
		{
//			Runnable thread=new Runnable()
			TimerTask task=new TimerTask()
			{
				public void run()
				{
					Log.d("data", "ttt");
					HttpURLConnection connection=null;
					try{
					
						Log.d("data", "two");
						//URL url=new URL("http://10.0.125.10:8080/location_management/location/User-validateuser.action?username=zengxueliang&password=zengxueliang");
//						URL url=new URL("http://10.0.125.2:8080/DianDi/index.jsp");
//						URL url=new URL("http://192.168.43.244:8080/DianDi/index.jsp");
//						URL url=new URL("http://10.0.125.2:8080/DianDi/index.jsp");
						URL url=new URL("http://115.28.42.228:8080/DianDi/index.jsp");
						Log.d("Test", "three");
						Log.d("data", "three");
						System.out.print(4);
						connection=(HttpURLConnection)url.openConnection();
						
						Log.d("data", "four");
						connection.setRequestMethod("GET");//GET表示希望从服务器获取数据，而POST
						//则表示希望提交数据给服务器
					
						Log.d("data", "five");
				connection.setConnectTimeout(5000);//设置链接超时
			
				Log.d("data", "six");
						connection.setReadTimeout(5000);
						Log.d("Test", "six");
						InputStream in=connection.getInputStream();
						//获取输入流进行读取
						BufferedReader reader=new BufferedReader(new InputStreamReader(in));
						StringBuilder response=new StringBuilder();
						String line;
						Log.d("Test", "sever");
						while((line=reader.readLine())!=null)
						{
							response.append(line);
						}
			

						Log.d("data","response is "+response.toString());
				
						Message message=new Message();
						message.what=SHOW_RESPONSE;
						message.obj=QuencyChar.loopString(response.toString());//把StringBuild转化为String
						Log.d("Test", "eight");
						handler.sendMessage(message);
			
						

						
						
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					finally{
						if(connection!=null)
						{
							connection.disconnect();
						}
					}
				}
		 };
		 timer.schedule(task, 0,3*1000);//循环3秒执行它的
		 

	 
				}



		
		
		
		

}
