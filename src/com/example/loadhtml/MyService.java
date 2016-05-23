package com.example.loadhtml;

import java.util.Date;



import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service{

    public void onCreate()
    {
//    	Notification notification=new Notification(R.drawable.shi,"Notification comes",System.currentTimeMillis());
//    	Intent notificationIntent=new Intent(this,MainActivity.class);
//    	PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, notificationIntent, 0);
//    	notification.setLatestEventInfo(this, "Message is coming", "This is Message", pendingIntent);
//    	long vibrates[]={0,1000,500,1000};
//    	notification.vibrate=vibrates;
//    	startForeground(1,notification);
    	
    	
    	
    }
    public int onStartCommand(Intent intent,int flags,int startId)
    {
    	new Thread(new Runnable()
		{
			public void run()
			{
				Log.d("data","executed at "+new Date().toString());
				
				
				
			}
			
		}).start();


      
		return super.onStartCommand(intent, flags, startId);
    }
    public void onDestory()
    {
    	super.onDestroy();
    	
    	Log.d("data", "onDestory executed");
    }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
