package com.example.loadhtml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import com.example.login.Login;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button sendRequest, stopRequest;
	static TextView responseText, address1;
	private static int counter = 0;
	private static String condition = null;
	private static ImageView im;
	private final String xue = "雪亮";
	private final String si = "思华";
	private final String gen = "小根";
	private TextView tv2;
	private LocationManager locationManager;
	static String provider;
	static String coordinate;
	static String addressStr = "no address \n";
	static SimpleDateFormat sdf;
	static TextView timeTextView;
	static Vibrator vibrator;
	static Intent intent1;
	static Notification notification;
	static NotificationManager manager;

	private Integer[] mImages = { R.drawable.sihua, R.drawable.xueliang,
			R.drawable.xiaogen };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐去电池等图标
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		setContentView(R.layout.main);
		Log.d("data", "MainActivity is coimng");
		sendRequest = (Button) findViewById(R.id.button_action);
		Log.d("data", "sendButton is true");
		stopRequest = (Button) findViewById(R.id.button_stop);
		Log.d("data", "stopButton is true");
		responseText = (TextView) findViewById(R.id.textView_condition);
		Log.d("data", "Butto is coimng");
		im = (ImageView) findViewById(R.id.imageView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		timeTextView = (TextView) findViewById(R.id.time);
		address1 = (TextView) findViewById(R.id.address);
		// 启动servic的intent1
		// intent1 = new Intent(MainActivity.this, MyService.class);
		// startService(intent1);
		new TimeView().start();

		// 通知的方法
             notification = new Notification(R.drawable.shi,
				"Notification comes", System.currentTimeMillis());
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Intent notificationIntent = new Intent(MainActivity.this,
				MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				MainActivity.this, 0, notificationIntent, 0);
		notification.setLatestEventInfo(MainActivity.this, "Message is coming",
				"This is Message", pendingIntent);
		//震动
		long vibrates[] = { 0, 1000, 500, 1000 };
		notification.vibrate = vibrates;
		//铃声
		notification.defaults=Notification.DEFAULT_SOUND;
		
		
		// 从上一个Activity传递到现在的数据，利用键值对的形式

		Intent intent = getIntent();
		String value = intent.getStringExtra("account");
		Log.d("data", "value is " + value);
		if (value.equals(si)) {
			im.setImageResource(mImages[0]);
			tv2.setText("姓名：杨思华 \n工号：01\n职位：护士");
		} else if (value.equals(xue)) {
			im.setImageResource(mImages[1]);
			tv2.setText("姓名：曾雪亮 \n工号：02\n职位：医师");
		} else if (value.equals(gen)) {
			im.setImageResource(mImages[2]);
			tv2.setText("姓名：刘小根\n工号：03\n职位：医师");
		}

		Log.d("Test", "one");
		Log.d("data", "one");

		Log.d("data", "location beginning");
		// 定位这个地方
		locationCome();

		sendRequest.setOnClickListener(new SendRequest());

		// 监听按键按下的变化情况
		sendRequest.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					sendRequest.setBackgroundColor(Color.GRAY);
					sendRequest.setTextColor(Color.RED);
					sendRequest.setText("Loading......");           
					
				   
				    
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					sendRequest.setBackgroundResource(R.drawable.anniu);
					
				

				}
				return false;
			}

		}

		);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void locationCome() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 获取所有的定位提供其
		List<String> providerList = locationManager.getProviders(true);
		Log.d("data", "list is ok");
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;

		} else {
			// Toast.makeText(MainActivity.this, "No location provider to use ",
			// Toast.LENGTH_SHORT).show();
			return;

		}
		Location location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			// 显示当前设备的位置
			getLocationAddress(location);

		}
		locationManager.requestLocationUpdates(provider, 2000, 1,
				locationListener);

	}

	// 监听地理位置
	LocationListener locationListener = new LocationListener() {
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		public void onProviderEnabled(String provider) {

		}

		public void onProviderDisabled(String provider) {

		}

		public void onLocationChanged(Location location) {
			getLocationAddress(location);
		}

	};

	public void getLocationAddress(Location location) {

		double lat = location.getLatitude();
		double lng = location.getLongitude();
		coordinate = "Latitude：" + lat + "\nLongitude：" + lng;
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
		try {
			List<Address> addresses = geocoder.getFromLocation(latitude,
					longitude, 1);

			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
					sb.append(address.getAddressLine(i)).append("\n");
					Log.d("data", "address is " + sb);
				}

				Log.d("data", "getAddress is " + address.getAddressLine(0));
				Log.d("data", "getAddress2 is " + address.getAddressLine(1));

				sb.append(address.getLocality()).append("\n");
				Log.d("data", "getLocality  is  " + address.getLocality());
				address1.setText("定位于：" + address.getAddressLine(0)
						+ " \n     " + address.getAddressLine(1));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 按两次退出程序的功能
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 当字符发生改变时，震动
	public static void changeVibrator() {

		// vibrator.vibrate(new long[]{0,1500,500,1500},0);
        Log.d("data","changeVibrator is coming");
		manager.notify(1, notification);

	}

	@Override
	protected void onStart() {

		Log.d("data", "onstart is coming");
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);

		super.onStart();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d("data", "onresume is coming");
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);

		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.d("data", "onstop is coming");
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);
		super.onStop();
	}

}
