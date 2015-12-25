package com.bn.gridandweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.bn.adapter.MyBaseAdapter;

public class WednesdayActivity extends Activity {
Context context;
static ListView listview;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.listview_activity);
	context = WednesdayActivity.this;
	listview = (ListView)this.findViewById(R.id.listView1);
	listview.setAdapter(new MyBaseAdapter(context, "星期三"));
	listview.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String msg = MyBaseAdapter.allinfo2[position];
			if(msg == null||msg.trim().equals(""))
			{
				Toast.makeText(WednesdayActivity.this, "此时间段没有课程!", Toast.LENGTH_LONG).show();
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(WednesdayActivity.this, OddListSelectedActivity.class);
				intent.putExtra("info", msg);
				WednesdayActivity.this.startActivity(intent);
			}
		}
	});
}
}
