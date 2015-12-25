package com.bn.gridandweather;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bn.adapter.MyBaseAdapter;
import com.bn.util.Constant;
import com.bn.util.SQLiteUtil;

public class AddCourseActivity extends Activity {
	int click[] = new int[20];
	Button[] bt = new Button[20];
	String[] info = new String[20];
	String[] insertCourse = new String[3];
	String[] insertTime = new String[3];
	boolean bb1 = false;// 判断添加课程是否成功
	boolean bb2 = false;
	EditText et1, et2, et3, et4, et5, et6;
	final int COMMON_DIALOG0 = 0;
	final int COMMON_DIALOG1 = 1;
	final int COMMON_DIALOG2 = 2;
	Button singleB, doubleB, allB;
	Spinner sp3, sp4;
    String selectWeek = "";
    String selectNum = "";
    public AlertDialog.Builder builder = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcource_activity);
		initEdit();
		initButton();
	}

	private void initButton() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			insertCourse[i] = "";
			insertTime[i] = "";
		}
		Button bt = (Button) this.findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bb1 = false;
				bb2 = false;
				insertCourse = new String[3];
				insertCourse[0] = et1.getText().toString();
				insertCourse[1] = et2.getText().toString();
				insertCourse[2] = et3.getText().toString();
				if (SQLiteUtil.QueryCourseIfExist(insertCourse[0])
						|| ((!insertCourse[0].trim().equals(""))
								&& (!insertCourse[1].trim().equals("")) && (!insertCourse[2]
								.trim().equals("")))) {
					SQLiteUtil.insertCourceMess(insertCourse);
					bb1 = true;
				}
				insertTime = new String[3];// 获得课程时间新型
				insertTime[0] = et4.getText().toString();
				insertTime[1] = Constant.editText;
				insertTime[2] = et6.getText().toString();
				if (insertTime[0].trim().equals("请选择上课节数")
						|| insertTime[1].trim().equals("请选择上课周数")
						|| insertTime[2].trim().equals("请输入上课星期")
						|| insertTime[0].trim().equals("")
						|| insertTime[1].trim().equals("")
						|| insertTime[2].trim().equals("")) {
					Toast.makeText(AddCourseActivity.this, "添加的课程不符合要求，请重新添加！",
							Toast.LENGTH_SHORT).show();
					bb2 = false;
				} else {
					if (SQLiteUtil
							.QueryTimeIfExist(insertCourse[0], insertTime)
							&& SQLiteUtil.QueryCourseIfRepeat(insertTime)) {// 如果此课程的此时间段没有添加
						SQLiteUtil.insertCourceTime(insertCourse[0], insertTime);// 将此课程的时间添加进数据库
						bb2 = true;
					}
				}
				if (bb1 && bb2) {
					Toast.makeText(AddCourseActivity.this, "此课程添加成功!",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(AddCourseActivity.this, "添加的课程不符合要求，请重新添加!",
							Toast.LENGTH_SHORT).show();
				}
				MondayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期一"));
				TuesdayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期二"));
				WednesdayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期三"));
				ThursdayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期四"));
				FridayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期五"));
				SaturdayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期六"));
				SundayActivity.listview.setAdapter(new MyBaseAdapter(
						AddCourseActivity.this, "星期日"));

			}
		});

	}

	private void initEdit() {
		// TODO Auto-generated method stub
		et1 = (EditText) this.findViewById(R.id.editText1);
		et2 = (EditText) this.findViewById(R.id.editText2);
		et3 = (EditText) this.findViewById(R.id.editText3);
		et4 = (EditText) this.findViewById(R.id.editText4);
		et5 = (EditText) this.findViewById(R.id.editText5);
		et6 = (EditText) this.findViewById(R.id.editText6);
		et1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et1.setText("");
				return false;
			}
		});
		et2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et2.setText("");
				return false;
			}
		});
		et3.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et3.setText("");
				return false;
			}
		});
		et4.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et4.setText("");
				showDialog(COMMON_DIALOG0);
				return false;
			}
		});
		et5.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et5.setText("");
				showDialog(COMMON_DIALOG1);
				return false;
			}
		});
		et6.setOnTouchListener(new OnTouchListener() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				et6.setText("");
				showDialog(COMMON_DIALOG2);
				return false;
			}
		});

	}

	public void initWeeksSpinner(final LayoutInflater inflater) {
		for (int i = 0; i < bt.length; i++) {
			bt[i].setOnClickListener(new MyOnClickListener(this, i));
		}
		singleB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int j = 0; j < click.length; j++) {
					click[j] = 0;
				}
				for (int k = 0; k < bt.length; k++) {
					if (k % 2 == 0) {
						click[k]++;
						bt[k].setBackgroundResource(R.color.yellow1);
						info[k] = bt[k].getText().toString();
					} else {
						bt[k].setBackgroundResource(R.color.white);
						info[k] = "";
					}
				}

			}
		});
		doubleB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int j = 0; j < click.length; j++) {
					click[j] = 0;
				}
				for (int k = 0; k < bt.length; k++) {
					if (k % 2 == 0) {
						bt[k].setBackgroundResource(R.color.white);
						info[k] = "";
					} else {

						click[k]++;
						bt[k].setBackgroundResource(R.color.yellow1);
						info[k] = bt[k].getText().toString();
					}
				}

			}
		});
		allB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int j = 0; j < click.length; j++) {
					click[j] = 0;
				}
				for (int k = 0; k < bt.length; k++) {
					click[k]++;
					bt[k].setBackgroundResource(R.color.yellow1);
					info[k] = bt[k].getText().toString();
				}
			}
		});
	}

	public void initDaysSpinner(final LayoutInflater inflater) {
		BaseAdapter ba1 = new BaseAdapter() {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return Constant.weekId.length;
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

			@SuppressLint("InflateParams")
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout ll = (LinearLayout) convertView;
				if (ll == null) {
					ll = (LinearLayout) (inflater.inflate(R.layout.spinnertext1, null).findViewById(R.id.listlayout));
				}
				TextView tv = (TextView) ll.getChildAt(0);
				tv.setTextSize(20);
				tv.setText(Constant.weekId[position]);
				return ll;
			}

		};
		sp4.setAdapter(ba1);
		sp4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
                  selectWeek = getResources().getText(Constant.weekId[position]) + "";
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
	}

	public void initCource(final LayoutInflater inflater) {
		BaseAdapter ba2=new BaseAdapter()
		 {
			 @Override
			 public int getCount() {
				 return Constant.nums.length;//总共7项
			 }

			 @Override
			 public Object getItem(int arg0) { return null; }

			 @Override
			 public long getItemId(int arg0) { return 0; }

			 @Override
			 public View getView(int arg0, View arg1, ViewGroup arg2) {
				 LinearLayout ll=(LinearLayout)arg1;
				 if(ll==null)
				 {
  		  			ll=(LinearLayout)(inflater.inflate(R.layout.spinnertext2,null).findViewById(R.id.listlayout2));
  		  			}
				 //初始化TextView
				 TextView tv=(TextView)ll.getChildAt(0);
				 tv.setText(Constant.nums[arg0]);//设置内容
				 tv.setTextSize(20);//设置字体大小
				 return ll;
			 }        	
		 };
		 sp3.setAdapter(ba2);//为Spinner设置内容适配器
		 //设置节数选项选中的监听器
		 sp3.setOnItemSelectedListener(
		       new OnItemSelectedListener()
			   {
		    	   @Override
		    	   public void onItemSelected(AdapterView<?> arg0, View arg1,
		    			   int arg2, long arg3) {//重写选项被选中事件的处理方法
		    		   selectNum=getResources().getText(Constant.numId[arg2])+"";
		    	   }
		    	   @Override
		    	   public void onNothingSelected(AdapterView<?> arg0) { }        	   
			   }
		 );
	}

	public Dialog onCreateDialog(int id) {
		Dialog  dialog = null;
		final LayoutInflater inflater = LayoutInflater.from(AddCourseActivity.this);
		switch(id)
		{
		case COMMON_DIALOG0:
		builder = new Builder(this);
		View viewDialog0 = inflater.inflate(R.layout.courcespinner, null);
		sp3 = (Spinner)viewDialog0.findViewById(R.id.spinner1);
		builder.setView(viewDialog0);
		builder.setTitle("请选择课程时间安排");
		builder.setNegativeButton("取消", null);
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				et4.setText(selectNum);
			}
		});
		initCource(inflater);
		dialog = builder.create();
		break;
		case COMMON_DIALOG1:
			builder = new Builder(this);
			View viewDialog1 = inflater.inflate(R.layout.tableweekslayout, null);
			singleB = (Button)viewDialog1.findViewById(R.id.danzhou);
			doubleB = (Button)viewDialog1.findViewById(R.id.shuangzhou);
			allB = (Button)viewDialog1.findViewById(R.id.quanxuan);
			for(int j = 0 ; j < bt.length ; j++)
			{
				bt[j] = (Button)viewDialog1.findViewById(Constant.buttonId[j]);
			    click[j] = 0;
			    info[j] = "";
			}
			builder.setView(viewDialog1);
			builder.setTitle("请选择周数安排");
			builder.setNegativeButton("取消", null);
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				   Constant.editText = "";
				   for(int i = 0 ; i < info.length ; i++)
				   {
					   if(!info[i].equals(""))
					   {
						   Constant.editText += info[i].trim() + ",";
					   }
				   }
				   if(Constant.editText.length() != 0)
				   {
					   Constant.editText = Constant.editText.substring(0,Constant.editText.length()-1);
					   Constant.showWeeks = DivideString(Constant.editText);
				   }
				   et5.setText(Constant.showWeeks);
				   
				}
			});
			initWeeksSpinner(inflater);
			dialog = builder.create();
			break;
		case COMMON_DIALOG2:
			builder = new Builder(this);
			View viewDialog2 = inflater.inflate(R.layout.daysspinner, null);
			sp4 = (Spinner)viewDialog2.findViewById(R.id.spinner3);
			builder.setTitle("请选择星期安排");
			builder.setView(viewDialog2);
			builder.setNegativeButton("取消", null);
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				   et6.setText(selectWeek);	
				}
			});
			initDaysSpinner(inflater);
			dialog = builder.create();
			break;
			
		}
		return dialog;
	}
//已验证
	public static String DivideString(String msg) {
		String result="";
		String[] group=msg.split(",");
		int last = Integer.parseInt(group[0]);
		int first = last;
		for(int i=1;i<group.length;i++){
			if(Integer.parseInt(group[i]) != last + 1){
				if(first!=last){
					result+=first + "-" + group[i - 1]+",";
				}else{
					result+=first+",";
				}
				first = Integer.parseInt(group[i]);
			}
			last = Integer.parseInt(group[i]);
		}
		if(first!=last){
			result+=first + "-" + group[group.length - 1];
		}else{
			result+=first;
		}	
		return result;
	}

}

class MyOnClickListener implements OnClickListener {
	AddCourseActivity ta;
	int kk = 0;

	public MyOnClickListener(AddCourseActivity ta, int kk) {
		this.ta = ta;
		this.kk = kk;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ta.click[kk]++;
		if (ta.click[kk] % 2 != 0) {
			ta.bt[kk].setBackgroundResource(R.color.yellow1);
			ta.info[kk] = ta.bt[kk].getText().toString();
		} else {
			ta.bt[kk].setBackgroundResource(R.color.white);
			ta.info[kk] = "";
		}
	}

}
