package com.bn.gridandweather;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bn.util.DateUtil;
import com.bn.util.SQLiteUtil;

public class LandingActivity extends Activity {
	EditText starttime;
	String dateInfo = "";
	final int DATE_DIALOG = 0;
	Calendar c = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.landing);
    	SQLiteUtil.createCource();
    	SQLiteUtil.createCourceTime();
    	SQLiteUtil.createFirstTime();
    	if(!SQLiteUtil.QueryFTime().equals(""))
    	{
    		Intent intent = new Intent();
    		intent.setClass(LandingActivity.this, OddDaysActivity.class);
    		LandingActivity.this.startActivity(intent);
    	    LandingActivity.this.finish();
    	}
    	else
    	{
    		DateUtil.getNowTime();
    		starttime = (EditText)this.findViewById(R.id.starttime);
    		starttime.setBackgroundColor(Color.TRANSPARENT);
    		starttime.setText(DateUtil.now_year +"年"+ DateUtil.now_month+"月"+DateUtil.now_day+"日");
    		dateInfo = DateUtil.now_year+"<#>"+DateUtil.now_month+"<#>"+DateUtil.now_day+"<#>";
    		starttime.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
				    showDialog(DATE_DIALOG);
					return false;
				}
			});
    		Button next = (Button)this.findViewById(R.id.next);
    		next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					SQLiteUtil.InsertFTime(dateInfo);
					Intent intent = new Intent();
					intent.setClass(LandingActivity.this, OddDaysActivity.class);
					LandingActivity.this.startActivity(intent);
					LandingActivity.this.finish();
				}
			});
    		
    	}
    }
    @Override
    protected Dialog onCreateDialog(int id) {
    	// TODO Auto-generated method stub
     Dialog dialog = null;
     switch(id)
     {
     case DATE_DIALOG:
    	 c = Calendar.getInstance();
         dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
			        monthOfYear = monthOfYear + 1;
			        dateInfo = year + "<#>" + monthOfYear + "<#>" + dayOfMonth;
			        starttime.setText(year + "年" + monthOfYear + "月" + dayOfMonth +"日");
			        
			}
		},c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
         break;
     }
    return dialog;
    }
    
    
    
}
