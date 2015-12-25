package com.bn.gridandweather;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bn.adapter.WholeWeekAdapter;
import com.bn.util.Constant;
import com.bn.util.DateUtil;

public class WholeWeekActivity extends Activity {
	LinearLayout rtlayout;
	Button oddDays, changeFace;
	int index = 0;
	Spinner spinner;
	ListView listView;
	TextView curmonth, monday, tuesday, wednesday, thursday, friday, saturday,
			sunday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whole_week);
		rtlayout = (LinearLayout) this.findViewById(R.id.layout);
		initFace();
		initButton();
		changTimes();
		initListView();
	}

	private void initListView() {
		// TODO Auto-generated method stub
         listView = (ListView)this.findViewById(R.id.listView1);
         listView.setAdapter(new WholeWeekAdapter(WholeWeekActivity.this));
	}

	private void changTimes() {
		// TODO Auto-generated method stub
		curmonth = (TextView) this.findViewById(R.id.month);
		monday = (TextView) this.findViewById(R.id.textView1);
		tuesday = (TextView) this.findViewById(R.id.textView2);
		wednesday = (TextView) this.findViewById(R.id.textView3);
		thursday = (TextView) this.findViewById(R.id.textView4);
		friday = (TextView) this.findViewById(R.id.textView5);
		saturday = (TextView) this.findViewById(R.id.textView6);
		sunday = (TextView) this.findViewById(R.id.textView7);
		monday.setText("");
		tuesday.setText("");
		wednesday.setText("");
		thursday.setText("");
		friday.setText("");
		saturday.setText("");
		sunday.setText("");
		int[] WholeWeekDate = DateUtil.wholeWeekDate(Constant.spinnerSelection + 1);
		curmonth.setText(WholeWeekDate[0]+"月");
		monday.setText(WholeWeekDate[1] + "\n" + "周一");
		tuesday.setText(WholeWeekDate[2]+"\n"+"周二");
	    wednesday.setText(WholeWeekDate[3]+"\n"+"周三");
	    thursday.setText(WholeWeekDate[4]+"\n"+"周四");
	    friday.setText(WholeWeekDate[5]+"\n"+"周五");
	    saturday.setText(WholeWeekDate[6]+"\n"+"周六");
	    sunday.setText(WholeWeekDate[7]+"\n"+"周日");
	    if(Constant.spinnerSelection+1 == Constant.currtWeeksNum)
	    {
	    	switch(DateUtil.now_week)
	    	{
	    	case 1 : sunday.setBackgroundColor(Color.GRAY);
	    	break;
	    	case 2:monday.setBackgroundColor(Color.GRAY);//周一
    		break;
    	case 3:tuesday.setBackgroundColor(Color.GRAY);//周二
    		break;
    	case 4:wednesday.setBackgroundColor(Color.GRAY);//周三
    		break;
    	case 5:thursday.setBackgroundColor(Color.GRAY);//周四
    		break;
    	case 6:friday.setBackgroundColor(Color.GRAY);//周五
    		break;
    	case 7:saturday.setBackgroundColor(Color.GRAY);//周六
			break;
	    	}
	    }
	    else
	    {
	    	curmonth.setBackgroundColor(Color.TRANSPARENT);
	    	sunday.setBackgroundColor(Color.TRANSPARENT);
	    	saturday.setBackgroundColor(Color.TRANSPARENT);//周六
	    	monday.setBackgroundColor(Color.TRANSPARENT);//周一
	    	tuesday.setBackgroundColor(Color.TRANSPARENT);//周二
	    	wednesday.setBackgroundColor(Color.TRANSPARENT);//周三
	    	thursday.setBackgroundColor(Color.TRANSPARENT);//周四
	    	friday.setBackgroundColor(Color.TRANSPARENT);//周五
	    }
	}

	private void initButton() {
		// TODO Auto-generated method stub
		oddDays = (Button) this.findViewById(R.id.odddays);
		oddDays.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WholeWeekActivity.this, OddDaysActivity.class);
				WholeWeekActivity.this.startActivity(intent);
			}
		});
		changeFace = (Button) this.findViewById(R.id.changeFace);
		changeFace.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(WholeWeekActivity.this)
						.setTitle("皮肤")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setSingleChoiceItems(Constant.skins, index,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										index = arg1;
										try {
											switch (arg1) {
											case 0:
												rtlayout.setBackgroundResource(R.color.white);
												Constant.skinSelection = 0;
												break;
											case 1:
												rtlayout.setBackgroundResource(R.drawable.netskin);
												Constant.skinSelection = 1;
												break;
											case 2:
												rtlayout.setBackgroundResource(R.drawable.flower);
												Constant.skinSelection = 2;
												break;
											case 3:
												rtlayout.setBackgroundResource(R.drawable.fengche);
												Constant.skinSelection = 3;
												break;
											case 4:
												rtlayout.setBackgroundResource(R.drawable.bear);
												Constant.skinSelection = 4;
												break;
											case 5:
												rtlayout.setBackgroundResource(R.drawable.butterfly);
												Constant.skinSelection = 5;
												break;
											case 6:
												rtlayout.setBackgroundResource(R.drawable.green);
												Constant.skinSelection = 6;
												break;

											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										arg0.dismiss();
									}
								}).setNegativeButton("取消", null).show();
			}
		});
		spinner = (Spinner) this.findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, Constant.spinnerInfo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(Constant.spinnerSelection);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Constant.weeksNum2 = 1;
					break;
				case 1:
					Constant.weeksNum2 = 2;
					break;
				case 2:
					Constant.weeksNum2 = 3;
					break;
				case 3:
					Constant.weeksNum2 = 4;
					break;
				case 4:
					Constant.weeksNum2 = 5;
					break;
				case 5:
					Constant.weeksNum2 = 6;
					break;
				case 6:
					Constant.weeksNum2 = 7;
					break;
				case 7:
					Constant.weeksNum2 = 8;
					break;
				case 8:
					Constant.weeksNum2 = 9;
					break;
				case 9:
					Constant.weeksNum2 = 10;
					break;
				case 10:
					Constant.weeksNum2 = 11;
					break;
				case 11:
					Constant.weeksNum2 = 12;
					break;
				case 12:
					Constant.weeksNum2 = 13;
					break;
				case 13:
					Constant.weeksNum2 = 14;
					break;
				case 14:
					Constant.weeksNum2 = 15;
					break;
				case 15:
					Constant.weeksNum2 = 16;
					break;
				case 16:
					Constant.weeksNum2 = 17;
					break;
				case 17:
					Constant.weeksNum2 = 18;
					break;
				case 18:
					Constant.weeksNum2 = 19;
					break;
				case 19:
					Constant.weeksNum2 = 20;
					break;
				}
				Constant.spinnerSelection = Constant.weeksNum2 - 1;
				changTimes();
				listView.setAdapter(new WholeWeekAdapter(WholeWeekActivity.this));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void initFace() {
		// TODO Auto-generated method stub
		switch (Constant.skinSelection) {
		case 0:
			rtlayout.setBackgroundResource(R.color.white);
		case 1:
			rtlayout.setBackgroundResource(R.drawable.netskin);
			break;
		case 2:
			rtlayout.setBackgroundResource(R.drawable.flower);
			break;
		case 3:
			rtlayout.setBackgroundResource(R.drawable.fengche);
			break;
		case 4:
			rtlayout.setBackgroundResource(R.drawable.bear);
			break;
		case 5:
			rtlayout.setBackgroundResource(R.drawable.butterfly);
			break;
		case 6:
			rtlayout.setBackgroundResource(R.drawable.green);
			break;

		}
	}
}
