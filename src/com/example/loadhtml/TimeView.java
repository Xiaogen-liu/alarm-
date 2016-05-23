package com.example.loadhtml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;

public class TimeView extends Thread {
      public void run()
      {
    	  do{try{
    	                 Thread.sleep(1000);
    	                 Message msg=new Message();
    	                 msg.what=5;
    	                 mbHandler.sendMessage(msg);
                     	  
    	  
    	  }catch(Exception e)
    	  {
    		  e.printStackTrace();
    	  }
    	  
    	  }while(true);
    	  }
      
      private Handler mbHandler = new Handler() {
    	          @Override
    	          public void handleMessage (Message msg) {
    	             super.handleMessage(msg);
    	              switch (msg.what) {
    	                   case 5:
    	                       long sysTime = System.currentTimeMillis();
    	                       CharSequence sysTimeStr = DateFormat.format("yyyyƒÍMM‘¬dd»’ E \n         hh:mm:ss      ", sysTime);
    	                       SpannableString spanText=new SpannableString(sysTimeStr);
    	                       spanText.setSpan(new RelativeSizeSpan(2.5f),25,38,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    	                       MainActivity.timeTextView.setText(spanText);
    	                      break;
    	                  
    	                  default:
    	                      break;
    	            }
    	          }
    	      };

      
      
      
      
      
    	  }
    	  
    	  
    	  
    	  
      
	   
	
	
	

