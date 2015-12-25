package com.bn.gridandweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.bn.adapter.MyBaseAdapter;
import com.bn.util.Constant;
import com.bn.util.SQLiteUtil;

public class OddListSelectedActivity extends Activity{
	Button edit,back,delete;
	String value = "";
	String[] names;
	TextView title,tv1,tv2,tv3,tv4,tv5,tv6;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.odd_list_selected);
    	initButton();
    	initTextView();
    }

	private void initTextView() {
		// TODO Auto-generated method stub
		title=(TextView)this.findViewById(R.id.textshow);
		tv1=(TextView)this.findViewById(R.id.nameT01);
		tv2=(TextView)this.findViewById(R.id.teacherT01);
		tv3=(TextView)this.findViewById(R.id.addressT01);
		tv4=(TextView)this.findViewById(R.id.numT01);
		tv5=(TextView)this.findViewById(R.id.weeksT01);
		tv6=(TextView)this.findViewById(R.id.weekT01);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		value = bundle.getString("info");
		names = value.split("<#>");
		Constant.showWeeks = AddCourseActivity.DivideString(names[4]);
		title.setText(names[0]);
		tv1.append(":  "+names[0]);//课程名称
		tv2.append(":  "+names[1]);//课程教师
		tv3.append(":  "+names[2]);//课程地点
		tv4.append(":  "+names[3]);//课程节数
		tv5.append(":  "+Constant.showWeeks);//课程周数
		tv6.append(":  "+names[5]);//课程星期
				
	}

	private void initButton() {
		// TODO Auto-generated method stub
		edit = (Button)this.findViewById(R.id.edit);
		back = (Button)this.findViewById(R.id.back);
		delete = (Button)this.findViewById(R.id.delete);
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(OddListSelectedActivity.this, EditCourseActivity.class);
				intent.putExtra("name", value);
				OddListSelectedActivity.this.startActivity(intent);
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(OddListSelectedActivity.this, OddDaysActivity.class);
				OddListSelectedActivity.this.startActivity(intent);
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteUtil.DeleteCourse(names);
				Intent intent = new Intent();
				intent.setClass(OddListSelectedActivity.this, OddDaysActivity.class);
				OddListSelectedActivity.this.startActivity(intent);
				MondayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期一"));
				TuesdayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期二"));
				WednesdayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期三"));
				ThursdayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期四"));
				FridayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期五"));
				SaturdayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期六"));
				SundayActivity.listview.setAdapter(new MyBaseAdapter(OddListSelectedActivity.this,"星期日"));
			}
		});
	}
}
