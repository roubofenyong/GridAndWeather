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

public class SundayActivity extends Activity {
	Context context;
	static ListView listview;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_activity);
		
		context=SundayActivity.this;
		listview=(ListView)this.findViewById(R.id.listView1);
		listview.setAdapter(new MyBaseAdapter(context,"星期日"));
		listview.setOnItemClickListener(//给ListView 添加监听
    			new OnItemClickListener()
    			{
    				@Override
    				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
    				{//重写选项被单击事件的处理方法
    					String msg=MyBaseAdapter.allinfo6[arg2];
    					if(msg==null||msg.trim().equals(""))
    					{
    						Toast.makeText(SundayActivity.this, "此时间段没有课程！", Toast.LENGTH_SHORT).show();
    					}
    					else
    					{
    						Intent intent=new Intent();
        					intent.setClass(SundayActivity.this,OddListSelectedActivity.class);
        					intent.putExtra("info", msg);	
        					SundayActivity.this.startActivity(intent);
    					}
    				}        	   
       });
	}
}
