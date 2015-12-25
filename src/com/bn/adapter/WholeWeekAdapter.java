package com.bn.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bn.gridandweather.R;
import com.bn.util.Constant;
import com.bn.util.SQLiteUtil;

public class WholeWeekAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<String[]> courseList = new ArrayList<String[]>();
    String[] content = new String[5];
    String[] content1 = new String[5];
    String[] content2 = new String[5];
    String[] content3 = new String[5];
    String[] content4 = new String[5];
    String[] content5 = new String[5];
    String[] content6 = new String[5];
    String[] content7 = new String[5];
    String[] day={"\n01\n  |\n02","\n03\n  |\n04","\n05\n  |\n06","\n07\n  |\n08\n","\n09\n  |\n10"};
	public WholeWeekAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		inflater = LayoutInflater.from(context);
		if(Constant.weeksNum2 != -1)
		{
			courseList = SQLiteUtil.QueryAllCourceMess(Constant.weeksNum2+"");
		}
		if(courseList==null)//如果本周没有课程
		{
			for(int i=0;i<5;i++){
				content[i]="";
				content1[i]="";
				content2[i]="";
				content3[i]="";
				content4[i]="";
				content5[i]="";
				content6[i]="";
				content7[i]="";
			}
		}
		else
		{
			for(String[] temp : courseList)
			{
				String[] divide = temp[4].split(",");
				for(int z = 0 ; z < divide.length ; z++)
				{
					if((Constant.weeksNum2+"").equals(divide[z]))
					{
						JungleIfShowInWhole(temp);
					}
				}
			}
		}
		
	}

	private void JungleIfShowInWhole(String[] temp) {
		// TODO Auto-generated method stub
		if(temp[temp.length-1].equals(Constant.week[0]))
		{
			content = MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[0]);
			content1 = SelectLocation(content);
		}
		else if(temp[temp.length-1].equals(Constant.week[1]))//如果是星期二
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[1]);
			content2=SelectLocation(content);
		}else if(temp[temp.length-1].equals(Constant.week[2]))//如果是星期三
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[2]);
			content3=SelectLocation(content);
		}else if(temp[temp.length-1].equals(Constant.week[3]))//如果是星期四
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[3]);
			content4=SelectLocation(content);
		}else if(temp[temp.length-1].equals(Constant.week[4]))//如果是星期五
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[4]);
			content5=SelectLocation(content);
		}else if(temp[temp.length-1].equals(Constant.week[5]))//如果是星期六
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[5]);
			content6=SelectLocation(content);
		}else if(temp[temp.length-1].equals(Constant.week[6]))//如果是星期日
		{
			content=MyBaseAdapter.GetSelectedInfo(Constant.weeksNum2+"", Constant.week[6]);
			content7=SelectLocation(content);
		}
	}

	private String[] SelectLocation(String[] content) {
		// TODO Auto-generated method stub
		String[] result = new String[5];
		String[] temp = new String[5];
        for(int i = 0 ; i < temp.length ; i++)
        {
        	result[i] = "";
        	temp[i] = "";
        }
        for(int i = 0 ; i < content.length ; i++)
        {
        	if(!content[i].equals(""))
        	{
        		temp = content[i].split("<#>");
        		result[i] = temp[0]+"@"+temp[2];
        	}
        }
        return result;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		LinearLayout ll = (LinearLayout)arg1;
		if(ll == null)
		{
			ll = (LinearLayout)(inflater.inflate(R.layout.wholeweek_detail, null).findViewById(R.id.WholeWeekLinearLayout));	
		}
		TextView tv = (TextView)ll.getChildAt(0);
		tv.setText(day[arg0]);
		tv.setTextSize(18);
		tv.setGravity(Gravity.LEFT);
		tv=(TextView)ll.getChildAt(1);//显示周一下的课程
		tv.setText(content1[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(2);//显示周二下的课程
		tv.setText(content2[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(3);//显示周三下的课程
		tv.setText(content3[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(4);//显示周四下的课程
		tv.setText(content4[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(5);//显示周五下的课程
		tv.setText(content5[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(6);//显示周六下的课程
		tv.setText(content6[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		tv=(TextView)ll.getChildAt(7);//显示周日下的课程
		tv.setText(content7[arg0]);
		tv.setGravity(Gravity.LEFT);
		tv.setTextSize(20);
		tv.setSingleLine(false);
		return ll;
	}

}
