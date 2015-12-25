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

public class FridayActivity extends Activity {
	Context context=null;
	static ListView listview;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_activity);
		
		context=FridayActivity.this;
		listview=(ListView)this.findViewById(R.id.listView1);
		listview.setAdapter(new MyBaseAdapter(context,"������"));
		listview.setOnItemClickListener(//ListView��������
    			new OnItemClickListener()
    			{
    				@Override
    				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
    				{//��дѡ������¼��Ĵ�����
    					String msg=MyBaseAdapter.allinfo4[arg2];
    					if(msg==null||msg.trim().equals(""))
    					{
    						Toast.makeText(FridayActivity.this, "��ʱ���û�пγ̣�", Toast.LENGTH_SHORT).show();
    					}
    					else
    					{
    						Intent intent=new Intent();
        					intent.setClass(FridayActivity.this,OddListSelectedActivity.class);
        					intent.putExtra("info", msg);	
        					FridayActivity.this.startActivity(intent);
    					}
    				}        	   
       });
	}
}
