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

import com.bn.gridandweather.AddCourseActivity;
import com.bn.gridandweather.R;
import com.bn.util.Constant;
import com.bn.util.SQLiteUtil;

public class MyBaseAdapter extends BaseAdapter{
    LayoutInflater inflator;
	String[] content = new String[5];
	Context context = null;
	List<String[]> list = new ArrayList<String[]>();
	int i = 0;
	String week = "";
	String oddlistselected = "";
	static List<String[]> ls = new ArrayList<String[]>();
	public static String[] allinfo = new String[5];
	public static String[] allinfo1 = new String[5];
	public static String[] allinfo2 = new String[5];
	public static String[] allinfo3 = new String[5];
	public static String[] allinfo4 = new String[5];
	public static String[] allinfo5 = new String[5];
	public static String[] allinfo6 = new String[5];
	int count = 0;
	
	public MyBaseAdapter(Context context,String week)
	{
		this.context = context;
		this.week = week;
		if(Constant.weeksNum != -1)
		{
			list = SQLiteUtil.QueryAllCourceMess(Constant.weeksNum+"");
		}
		if(list == null)
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				content[j] = "";
				allinfo[j] = "";
				allinfo1[j] = "";
				allinfo2[j] = "";
				allinfo3[j] = "";
				allinfo4[j] = "";
				allinfo5[j] = "";
				allinfo6[j] = "";
			}
		}
		else
		{
			int i = 0;
			for(String[] s : list)
			{
				String[] aa = new String[6];
				for(String ss : s)
				{
					aa[i] = ss;
					i++;
				}
				i =0;
				oddlistselected = aa[5];
				 String[] divide = aa[4].split(",");
				for(int z = 0 ; z < divide.length ; z++)
				{
					if((Constant.weeksNum+"").equals(divide[z]))
					{
						count++;
						aa[4] = AddCourseActivity.DivideString(aa[4]);
						JungleIfShowInSingle(s,aa);
					}
					else
					{
						if(count == 0 && z== divide.length - 1)
						{
							for(int j = 0 ; j < 5 ; j++)
							{
								allinfo[j]="";
								allinfo1[j]="";
								allinfo2[j]="";
								allinfo3[j]="";
								allinfo4[j]="";
								allinfo5[j]="";
								allinfo6[j]="";
							}
						}
						
						
					}
				}
			}
		}
		inflator = LayoutInflater.from(context);
	}
	private void JungleIfShowInSingle(String[] s, String[] aa) {
		// TODO Auto-generated method stub
		if(oddlistselected.equals("星期一"))
		{
			allinfo = GetSelectedInfo(Constant.weeksNum + "","星期一");
		}else if(oddlistselected.equals("星期二")){
			allinfo1=GetSelectedInfo(Constant.weeksNum+"","星期二");
		}else if(oddlistselected.equals("星期三")){
			allinfo2=GetSelectedInfo(Constant.weeksNum+"","星期三");
		}else if(oddlistselected.equals("星期四")){
			allinfo3=GetSelectedInfo(Constant.weeksNum+"","星期四");
		}else if(oddlistselected.equals("星期五")){
			allinfo4=GetSelectedInfo(Constant.weeksNum+"","星期五");
		}else if(oddlistselected.equals("星期六")){
			allinfo5=GetSelectedInfo(Constant.weeksNum+"","星期六");
		}else if(oddlistselected.equals("星期日")){
			allinfo6=GetSelectedInfo(Constant.weeksNum+"","星期日");
		}
		if(s[s.length-1].equals(week))
		{
			if(s[3].equals(Constant.nums[0]))
			{
				content[0] = aa[0] + "\n*" + aa[1] + "\n@" + aa[2] + "\n~"+aa[4];
				
			}
			else if(s[3].equals(Constant.nums[1])){
				content[1]=aa[0]+"\n*"+aa[1]+"\n@"+aa[2]+"\n~"+aa[4];
			}
			else if(s[3].equals(Constant.nums[2])){
				content[2]=aa[0]+"\n*"+aa[1]+"\n@"+aa[2]+"\n~"+aa[4];
			}
			else if(s[3].equals(Constant.nums[3])){
				content[3]=aa[0]+"\n*"+aa[1]+"\n@"+aa[2]+"\n~"+aa[4];
			}
			else if(s[3].equals(Constant.nums[4])){
				content[4]=aa[0]+"\n*"+aa[1]+"\n@"+aa[2]+"\n~"+aa[4];
			}
		}
		
	}
	public static String[] GetSelectedInfo(String num, String week) {
		// TODO Auto-generated method stub
		int[] x = new int[5];
		for(int z = 0 ; z < 5 ; z++)
		{
			x[z] = -1;
		}
		String[] result = new String[5];
		String[] temp = new String[100];
		ls = SQLiteUtil.GetCourceByWeeks(num, week);
		if(ls == null)
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				result[j] = "";
				temp[j] = "";
			}
		}else
		{
			for(int j = 0 ; j < 5 ; j++)
			{
				result[j] = "";
				temp[j] = "";
			}
			for(String[] ok : ls)
			{
				for(int h = 0 ; h < ok.length ; h++)
				{
					if(h == 3)
					{
						if(ok[3].equals(Constant.nums[0]))
						{
							x[0] = 0;
						}
						if(ok[3].equals(Constant.nums[1]))
						{
							x[1] = 1;
						}
						if(ok[3].equals(Constant.nums[2]))
						{
							x[2] = 2;
						}
						if(ok[3].equals(Constant.nums[3]))
						{
							x[3] = 3;
						}
						if(ok[3].equals(Constant.nums[4]))
						{
							x[4] = 4;
						}
					}
				}
			}
			int k = 0;
			for(String[] group :ls)
			{
				String[] divide = group[4].split(",");
				for(int z = 0 ; z < divide.length ; z++)
				{
					if((Constant.weeksNum+"").equals(divide[z]))
					{
						for(String str : group)
						{
							temp[k] += str+"<#>";
						}
					}
				}
				k++;
				
			}
			int s = 0;
			for(int a = 0 ; a < 5 ; a++)
			{
				if(x[a] >= 0)
				{
				  result[a] = temp[s];
				  s++;
				}
			}
			
			
		}
		
		
		return result;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Constant.nums.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll = (LinearLayout)convertView;
		if(ll == null)
		{
			ll = (LinearLayout)(inflator.inflate(R.layout.list,null).findViewById(R.id.list));
		}
		TextView tv1 = (TextView)ll.getChildAt(0);
		TextView tv2 = (TextView)ll.getChildAt(1);
		tv1.setText("\n" + Constant.nums[position] + "\n" );
		tv1.setTextSize(18);
		tv1.setGravity(Gravity.LEFT);
		tv2.setTextSize(24);
		tv2.setText(content[position]);
		tv2.setGravity(Gravity.LEFT);	
		return ll;
	}

}
