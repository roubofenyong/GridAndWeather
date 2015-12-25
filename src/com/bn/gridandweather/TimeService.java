package com.bn.gridandweather;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TimeService extends Service {
    Thread task;
    boolean flag = true;//线程循环标志
    public static String mWay,mYear,mMonth,mDay,mHour,mMinute;
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		task = new Thread()
		{
			public void run() {
				
			 while(flag)
			 {
				Intent intent = new Intent("wyf.action.time_update"); 
				 mWay = StringData();
				 intent.putExtra("time", mWay);
				 TimeService.this.sendBroadcast(intent);
				 intent = new Intent("wyf.action.load_xq");
				 TimeService.this.sendBroadcast(intent);
				 try
				 {
                      Thread.sleep(500);					 
				 }catch(InterruptedException e)
				 {
					 e.printStackTrace();
				 }
			 }
			}
		};
		
	}
	public static String StringData() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		mYear = String.valueOf(c.get(Calendar.YEAR));
		mMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        mHour = (c.get(Calendar.HOUR_OF_DAY)<10?"0":"")+String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        mMinute = (c.get(Calendar.MINUTE)<10?"0":"")+String.valueOf(c.get(Calendar.MINUTE));
        return mYear+"年"+mMonth+"月"+mDay+"日\t\t"+mHour+":"+mMinute;
	};
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
      task.start();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
      flag = false;
	}

}
