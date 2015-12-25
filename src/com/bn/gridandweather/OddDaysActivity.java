package com.bn.gridandweather;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import com.bn.adapter.MyBaseAdapter;
import com.bn.adapter.MyPagerAdapter;
import com.bn.gridandweather.R;
import com.bn.util.Constant;
import com.bn.util.DateUtil;
import com.bn.util.JSonInfoUtil;
import com.bn.util.SQLiteUtil;
import com.bn.util.TQYBInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

@SuppressLint("HandlerLeak")
@SuppressWarnings("deprecation")
public class OddDaysActivity extends Activity 
{
	String[] cities={"��ɽ","����","���","����","�Ϻ�","����","������","�ൺ","����","�人","����","����"};//�ɲ鿴�����ĳ���
	static Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,addCourse,wholeWeek,title;
	Intent intent,intent2,intent3,intent4,intent5,intent6,intent7;
    static Context context=null;
    private static ViewPager pager;
	LocalActivityManager manager=null;
	int index=0,xingqi,week=-1;
	ArrayList<View> list;
	TextView city,changeCity,tv;
	String MyCityName=null;
	Resources res;
	Bitmap bitmap;
	Spinner spinner;
	String[] num;
	String jStr;
	String[] week1={"����","��һ","�ܶ�","����","����","����","����"};
	String[] week2={"������","����һ","���ڶ�","������","������","������","������"};
	String msg="��ɽ";
	String str[]=new String[4];//���������Ϣ
	String cweek="";//��ý��������ڼ�
	String allmess[][]=new String[5][5];//�����ʾ��Widget�ϵĿγ���Ϣ
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.odd_days);
		
		context=OddDaysActivity.this;
		manager= new LocalActivityManager(this,true);
		manager.dispatchCreate(savedInstanceState);
		pager=(ViewPager)this.findViewById(R.id.viewpager);

		//�����ݿ�
		SQLiteUtil.createCource();//�����γ̱�
		SQLiteUtil.createCourceTime();//�����γ�ʱ���
		initCourseWidget();//��ʼ���γ̱��
		initSkin();//��ʼ��Ƥ��
		initWeek();//��ʼ������
		initWeeksButton();//��ʼ�����ڰ�ť
		initFunctionButton();//��ʼ�����ܰ�ť
		initPagerViewer();//��ʼ��ViewPager
	}
	public void initSkin()//��ʼ��Ƥ��
	{
		switch(Constant.skinSelection)
		{	 
			case 0:pager.setBackgroundResource(R.color.white);
    		break;
    		case 1:pager.setBackgroundResource(R.drawable.netskin);
    		break;
    		case 2:pager.setBackgroundResource(R.drawable.flower);
    		break;
    		case 3:pager.setBackgroundResource(R.drawable.fengche);
    		break;
    		case 4:pager.setBackgroundResource(R.drawable.bear);
    		break;
    		case 5:pager.setBackgroundResource(R.drawable.butterfly);
    		break;
    		case 6:pager.setBackgroundResource(R.drawable.green);
    		break;
		}
	}
	public void initWeek()//��ʼ������,Ĭ��ѡ��
	{
		final Calendar c = Calendar.getInstance();  
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  
        xingqi=Integer.parseInt(String.valueOf(c.get(Calendar.DAY_OF_WEEK))); 
        switch(xingqi)
        {
        case 1:week=6;break;//����
        case 2:week=0;break;//��һ
        case 3:week=1;break;//�ܶ�
        case 4:week=2;break;//����
        case 5:week=3;break;//����
        case 6:week=4;break;//����
        case 7:week=5;break;//����
        }
	}
	//���γ���ʾ��������
	public void initCourseWidget(){
		String cnums=DateUtil.currtWeeks()+"";//��õ�ǰ�ǵڼ���
		System.out.println("cnums   "+cnums);
		for(int i=0;i<week1.length;i++){//ת��������Ϣ��ʽ
			if(cweek.equals(week1[i])){
				cweek=week2[i];
			}
		}
		List<String[]> list=SQLiteUtil.QueryWidgetMess(cweek,cnums);//�����ݿ�����Ӧ�γ���Ϣ
		if(list!=null){
			String[][] mess = new String[list.size()][list.get(0).length];
			int k=0;
			int x=0;
			for(String[] s:list)
			{
				String[] aa=new String[4];
				for(String ss:s)
				{
					aa[k]=ss;
					k++;
				}
				k=0;
				String[] divide=aa[3].split(",");//��������Ϣת������Ҫ�ĸ�ʽ
				for(int z=0;z<divide.length;z++){
					if(cnums.equals(divide[z]))
					{
						mess[x]=aa;
						x++;
					}
				}
			}
			int a=0;
			for(int i=0;i<mess.length;i++)
			{
				int b=0;
				for(int j=0;j<mess[i].length;j++)
				{
					if(mess[i][j]!=null)
					{
						allmess[a][b]=mess[i][j];
						b++;
					}
				}
				a++;
			}
			for(int i=0;i<allmess.length;i++)//�������ϢΪ��ʱ�����⴫Intentʱ��������ת������Ҫ��ʽ
			{
				for(int j=0;j<allmess[i].length;j++)
				{
					if(allmess[i][j]==null)
					{
						allmess[i][j]="";
					}
				}
			}
			for(int i=1;i<=5;i++)//���η��Ϳγ���Ϣ��Intent
			{
						Intent intentm = new Intent("wyf.action.tc"+i+"");
						intentm.putExtra("tc"+i+"",allmess[i-1][1]);
						OddDaysActivity.this.sendBroadcast(intentm);
						
						Intent intentn = new Intent("wyf.action.tnum"+i+"");
						intentn.putExtra("tnum"+i+"",allmess[i-1][0]);
						OddDaysActivity.this.sendBroadcast(intentn);
						
						Intent intentp = new Intent("wyf.action.tp"+i+"");
						intentp.putExtra("tp"+i+"",allmess[i-1][2]);
						OddDaysActivity.this.sendBroadcast(intentp);
				}
			}
		else//������û�Σ�����ʾ"��û����κογ�"
		{
			String[] mess=new String[5];
			for(int i=0;i<mess.length;i++)
			{
				mess[i]="��û����κογ�";
			}
			for(int i=1;i<=mess.length;i++)
			{
				Intent intentm = new Intent("wyf.action.tc"+i+"");
				intentm.putExtra("tc"+i+"",mess[i-1]);
				OddDaysActivity.this.sendBroadcast(intentm);
			}
		}
	}
	//��ʼ�����ܰ�ť
	public void initFunctionButton()
	{
		addCourse=(Button)this.findViewById(R.id.addclass);//��ΰ�ť
		addCourse.setOnClickListener(//����ΰ�ť��Ӽ���
				new OnClickListener()
				{
					public void onClick(View v)
					{
						Intent intent=new Intent();
						intent.setClass(OddDaysActivity.this, AddCourseActivity.class);
						OddDaysActivity.this.startActivity(intent);
					}
				});
		//����
		wholeWeek=(Button)this.findViewById(R.id.oneweek);
		wholeWeek.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						Intent intent=new Intent();
						intent.setClass(OddDaysActivity.this, WholeWeekActivity.class);
						OddDaysActivity.this.startActivity(intent);
					}
				}
				); 
		
		spinner=(Spinner)this.findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Constant.spinnerInfo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(Constant.spinnerSelection);
		spinner.setOnItemSelectedListener(//��Spinner��Ӽ���
				new OnItemSelectedListener(){
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3)
					{
						switch(arg2){
						case 0:Constant.weeksNum=1;break;//ѡ���һ��
						case 1:Constant.weeksNum=2;break;//ѡ��ڶ���
						case 2:Constant.weeksNum=3;break;//ѡ�������
						case 3:Constant.weeksNum=4;break;//ѡ�������
						case 4:Constant.weeksNum=5;break;//ѡ�������
						case 5:Constant.weeksNum=6;break;//ѡ�������
						case 6:Constant.weeksNum=7;break;//ѡ�������
						case 7:Constant.weeksNum=8;break;//ѡ��ڰ���
						case 8:Constant.weeksNum=9;break;//ѡ��ھ���
						case 9:Constant.weeksNum=10;break;//ѡ���ʮ��
						case 10:Constant.weeksNum=11;break;//ѡ���ʮһ��
						case 11:Constant.weeksNum=12;break;//ѡ���ʮ����
						case 12:Constant.weeksNum=13;break;//ѡ���ʮ����
						case 13:Constant.weeksNum=14;break;//ѡ���ʮ����
						case 14:Constant.weeksNum=15;break;//ѡ���ʮ����
						case 15:Constant.weeksNum=16;break;//ѡ���ʮ����
						case 16:Constant.weeksNum=17;break;//ѡ���ʮ����
						case 17:Constant.weeksNum=18;break;//ѡ���ʮ����
						case 18:Constant.weeksNum=19;break;//ѡ���ʮ����
						case 19:Constant.weeksNum=20;break;//ѡ��ڶ�ʮ��
						}
						Constant.spinnerSelection=Constant.weeksNum-1;
						MondayActivity.listview.setAdapter(new MyBaseAdapter(context,"����һ"));
						TuesdayActivity.listview.setAdapter(new MyBaseAdapter(context,"���ڶ�"));
						WednesdayActivity.listview.setAdapter(new MyBaseAdapter(context,"������"));
						ThursdayActivity.listview.setAdapter(new MyBaseAdapter(context,"������"));
						FridayActivity.listview.setAdapter(new MyBaseAdapter(context,"������"));
						SaturdayActivity.listview.setAdapter(new MyBaseAdapter(context,"������"));
						SundayActivity.listview.setAdapter(new MyBaseAdapter(context,"������"));
					}
					@Override
					public void onNothingSelected(AdapterView<?> arg0) {}
				}
				);
		city=(TextView)this.findViewById(R.id.city);
        changeCity=(TextView)this.findViewById(R.id.changecity);
        changeCity.setOnClickListener(//�л����еļ�������
        		new OnClickListener(){
					@Override
					public void onClick(View v)
					{
						new AlertDialog.Builder(OddDaysActivity.this)
						.setTitle("��ѡ��Ҫ�鿴�ĳ���")
						.setIcon(android.R.drawable.ic_dialog_info)                
						.setSingleChoiceItems(cities, index,
						  new DialogInterface.OnClickListener() 
						  {              
						     public void onClick(DialogInterface dialog, int which)
						     {
						    	 index=which;
						    	 city.setText(cities[which]);
						    	 msg=city.getText().toString();
						    	 Intent intents = new Intent("wyf.action.city");
						    	 intents.putExtra("ccity", msg);
						    	 OddDaysActivity.this.sendBroadcast(intents);
						    	 try{
						    		 initThread();//�������ȡ������Ϣ
						 	     }catch(Exception e){
						 	    	e.printStackTrace();
						 	     }
						         dialog.dismiss();
						      }
						   })
						.setNegativeButton("ȡ��", null)
						.show();
					}
        		});
        try{
        	initThread();//�������ȡ������Ϣ
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}

	public void initThread()//�������ȡ������Ϣ
	{
		MyCityName=city.getText().toString();
		new Thread()
		{
			public void run()
			{
				//���ݳ������Ƶõ���ص�������Ϣ
				try {
					jStr=JSonInfoUtil.getJSonStr(MyCityName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	//��ʼ�� ViewPager
	private void initPagerViewer() 
	{ 
		//��������Ҫ��Activity����list�� ��������������
        list = new ArrayList<View>();
        intent = new Intent(context, MondayActivity.class);//��һ
        list.add(getView("MondayActivity", intent));
        intent2 = new Intent(context, TuesdayActivity.class);//�ܶ�
        list.add(getView("TuesdayActivity", intent2));
        intent3 = new Intent(context, WednesdayActivity.class);//���� 
        list.add(getView("WednesdayActivity", intent3));
        intent4 = new Intent(context, ThursdayActivity.class);//����
        list.add(getView("ThursdayActivity", intent4));
        intent5 = new Intent(context, FridayActivity.class);//����
        list.add(getView("FridayActivity", intent5));
        intent6 = new Intent(context, SaturdayActivity.class);//����
        list.add(getView("SaturdayActivity", intent6));
        intent7 = new Intent(context, SundayActivity.class);//����
        list.add(getView("SundayActivity", intent7));
        
        pager.setAdapter(new MyPagerAdapter(list));//��list��������������
        pager.setCurrentItem(week);
        //��Ӽ��� ��Activityʱ
        pager.setOnPageChangeListener(
        		new  OnPageChangeListener(){
        			@Override  
            	    public void onPageScrollStateChanged(int arg0) 
        			{   
            	    }
            	    @Override  
            	    public void onPageScrolled(int arg0, float arg1, int arg2) 
            	    {  
            	    }
            	    @Override  
            	    public void onPageSelected(int arg0) 
            	    {
            	    	initCourseWidget();
            	    	changeButton(arg0);
            	    }
        		});
	 }
	//����Ƥ��
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuItem ok=menu.add(0, 0, 0,"����Ƥ��");
    	OnMenuItemClickListener lsn=new OnMenuItemClickListener()
    	{//ʵ�ֲ˵������¼������ӿ�
			@Override
			public boolean onMenuItemClick(MenuItem item) 
			{ 
				new AlertDialog.Builder(OddDaysActivity.this)
				.setTitle("Ƥ��")
				.setIcon(android.R.drawable.ic_dialog_info)                
				.setSingleChoiceItems(Constant.skins, 0,
				  new DialogInterface.OnClickListener() 
				  {              
				     public void onClick(DialogInterface dialog, int which)
				     {  	
				    	 try{
				    		 switch(which){ 
				    		 case 0:pager.setBackgroundResource(R.color.white);
				    		 Constant.skinSelection=0;
				    		 break;
				    		 case 1:pager.setBackgroundResource(R.drawable.netskin);
				    		 Constant.skinSelection=1;
				    		 break;
				    		 case 2:pager.setBackgroundResource(R.drawable.flower);
				    		 Constant.skinSelection=2;
				    		 break;
				    		 case 3:pager.setBackgroundResource(R.drawable.fengche);
				    		 Constant.skinSelection=3;
				    		 break;
				    		 case 4:pager.setBackgroundResource(R.drawable.bear);
				    		 Constant.skinSelection=4;
				    		 break;
				    		 case 5:pager.setBackgroundResource(R.drawable.butterfly);
				    		 Constant.skinSelection=5;
				    		 break;
				    		 case 6:pager.setBackgroundResource(R.drawable.green);
				    		 Constant.skinSelection=6;
				    		 break;
				    		 }
				 	     }catch(Exception e){
				 	    	e.printStackTrace();
				 	     }
				         dialog.dismiss();
				      }
				   })
				.setNegativeButton("ȡ��", null)
				.show();
				return true;
			}    		
    	};
    	ok.setOnMenuItemClickListener(lsn);//��ȷ���˵�����Ӽ����� 
    	return true;
	}
	public void initCityList(String city) throws Exception{
		TQYBInfo info=null;
		//������ȡ��Ҫ������Ԥ����Ϣ
		info=JSonInfoUtil.parseJSon(jStr);
		try{
			//��ȡ����������Ԥ��ͼ��
			ImageView iv=(ImageView)findViewById(R.id.ImageView01);
			res=this.getResources();
			bitmap=BitmapFactory.decodeResource(res, info.pic);
			iv.setImageBitmap(bitmap);
			
			tv=(TextView)this.findViewById(R.id.date);
			tv.setText("����:"+info.date);
			str[0]=tv.getText().toString();
			cweek=str[0].substring(3, 5);
			
			tv=(TextView)this.findViewById(R.id.weather);
			tv.setText("����: "+info.tqms);
			str[1]=tv.getText().toString();
			
			tv=(TextView)this.findViewById(R.id.temperature);
			tv.setText("�¶ȣ�"+info.wd);
			str[2]=tv.getText().toString();
			
			tv=(TextView)this.findViewById(R.id.wind);
			tv.setText("��������"+info.flfx);
			initCourseWidget();
			
		}catch(Exception e){
			String msg="��ǰ�˳���������Ϣ�����ã�\n��������������Ƿ�������";		
			Toast.makeText(OddDaysActivity.this, msg, Toast.LENGTH_LONG).show();
		}
		//������Ϸ�����Intent�޸�widget�е�����
        Intent intent1 = new Intent("wyf.action.msg1");
        intent1.putExtra("xxq1", str[0]);
        OddDaysActivity.this.sendBroadcast(intent1);	
        
        Intent intent2 = new Intent("wyf.action.msg2");
        intent2.putExtra("xxq2", str[1]);
        OddDaysActivity.this.sendBroadcast(intent2);
        
        Intent intent3 = new Intent("wyf.action.msg3");
        intent3.putExtra("xxq3", str[2]);
        OddDaysActivity.this.sendBroadcast(intent3);
	}
	public Handler handler = new Handler()//��ȡ������Ϣ�ɹ��󣬳�ʼ�������б�
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(msg.what==0)//��ȡ������Ϣ�ɹ�
			{
				try {
					initCityList(MyCityName);//��ʼ�������б�
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private View getView(String id, Intent intent)
	{
		//ͨ��LocalActivityManager����Activity
	    return manager.startActivity(id, intent).getDecorView(); 
	}
	//��ʼ�����ڰ�ť
	public void initWeeksButton()
	{
		pager=(ViewPager)this.findViewById(R.id.viewpager);
		//�õ���һ�����հ�ť������
		bt1=(Button)this.findViewById(R.id.button1);
		bt2=(Button)this.findViewById(R.id.button2);
		bt3=(Button)this.findViewById(R.id.button3);
		bt4=(Button)this.findViewById(R.id.button4);
		bt5=(Button)this.findViewById(R.id.button5);
		bt6=(Button)this.findViewById(R.id.button6);
		bt7=(Button)this.findViewById(R.id.button7);
		//��ʼ����ť����ɫ
		bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
	    switch(week){
	    case 0:bt1.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 1:bt2.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 2:bt3.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 3:bt4.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 4:bt5.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 5:bt6.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    case 6:bt7.setBackgroundColor(context.getResources().getColor(R.color.dblue));
	    break;
	    }
		//Ϊ��һ�����հ�ť��Ӽ���
		bt1.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						changeButton(0);
					}
				});
		bt2.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						changeButton(1);
					}
				});
		bt3.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						changeButton(2);
					}
				});
		bt4.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						changeButton(3);
					}
				});
		bt5.setOnClickListener(
				new OnClickListener()
				{
		           public void onClick(View v)
		           {
		        	   changeButton(4);
		           }
				});
		bt6.setOnClickListener(
				new OnClickListener()
				{
		           public void onClick(View v)
		           {
		        	   changeButton(5);
		           }
				});
		bt7.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						changeButton(6);
					}
				});	
	}
	public static void changeButton(int i){
    	switch(i){
    	case 0:bt1.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 1:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 2:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 3:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 4:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 5:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	break;
    	case 6:bt1.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt2.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt3.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt4.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt5.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt6.setBackgroundColor(context.getResources().getColor(R.color.blue1));
    	bt7.setBackgroundColor(context.getResources().getColor(R.color.dblue));
    	break;
    	}
    	pager.setCurrentItem(i);
    }
}
