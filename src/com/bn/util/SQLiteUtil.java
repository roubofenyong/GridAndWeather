package com.bn.util;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtil {
  static String sql;
  static Cursor cur;
  static SQLiteDatabase sld;
  public static void createFirstTime()
  {
	  try
	  {
		  sld = SQLiteDatabase.openDatabase("/data/data/com.bn.gridandweather/mydb", null, SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
		  sql = "create table if not exists firsttime"
				  +"("
				  +"fid char(6) primary key,"
				  +"ftime varchar(20)"
				  +");";
		  sld.execSQL(sql);
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public static void createCource()
  {
	  try
	  {
		  sld = SQLiteDatabase.openDatabase("/data/data/com.bn.gridandweather/mydb", null, SQLiteDatabase.OPEN_READWRITE |SQLiteDatabase.CREATE_IF_NECESSARY);
		  sql = "create table if not exists course"
				  +"("
				  +"ccid char(6) primary key,"
				  +"coursename varchar(20),"
				  +"cteacher varchar(20),"
				  +"place varchar(50)"
				  +");";
		  sld.execSQL(sql);	  
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public static void createCourceTime()
  {
	  try
	  {
		  sld = SQLiteDatabase.openDatabase("/data/data/com.bn.gridandweather/mydb", null, SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY);
		  sql = "create table if not exists coursetime"
				  +"("
				  +"ccid char(6),"
				  +"tid char(6) primary key,"
				  +"cnum varchar(20),"
				  +"cweeks varchar(20),"
				  +"cweek varchar(20),"
				  +"constraint fk_mid foreign key(ccid) references course(ccid)"
				  +");";
		  sld.execSQL(sql);
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  public static String[] QueryOneCourceMess(String cname)
  {
	  String str[] = new String[3];
	  try
	  {
		  sql = "select cousename,cteacher,place from course where coursename ='"+cname+"';";
		  cur = sld.rawQuery(sql, null,null);
		  while(cur.moveToNext())
		  {
			  str[0] = cur.getString(0);
			  str[1] = cur.getString(1);
			  str[2] = cur.getString(2);
		  }
		  cur.close();
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return str;
  }
  
  public static void insertCourceTime(String cname,String str[])
  {
	  String id = NextCourceTimeID();
	  String cid = GetCourceIDByName(cname);
	  try
	  {
		  sql = "insert into coursetime values('"+cid+"','"+id+"','"+str[0]+"','"+str[1]+"','"+str[2]+"');";
	      sld.execSQL(sql);
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  public static String MaxCourceTimeID()
  {
	  String maxid = "";
	  try
	  {
		  sql = "select max(tid) from coursetime";
          cur = sld.rawQuery(sql, null);
          while(cur.moveToNext())
          {
        	  maxid = cur.getString(0);
          }
          cur.close();
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	return maxid;  
  }
  public static String NextCourceTimeID()
  {
	  String str = MaxCourceTimeID();
	  String mss = "";
	  if(str == null)
	  {
		  mss = "T10001";
	  }
	  else
	  {
		  mss = str.substring(1,str.length());
		  mss = str.substring(0,1)+(Integer.parseInt(mss)+1);
	   }
	  
	  return mss;
  }
  
  public static boolean QueryCourseIfExist(String cname)
  {
	  boolean bb = true;
	  sql = "select * from course where coursename='"+cname+"';";
	  cur = sld.rawQuery(sql, null);
	  if(cur.moveToNext())
	  {
		  bb = false;
	  }
	  else
	  {
		  bb = true;
	  }
	  return bb;
  }
  
  public static String GetCourceIDByName(String cname)
  {
	  String cid = "";
	  try
	  {
		  sql = "select ccid from course where coursename ='"+cname+"';";
		  cur = sld.rawQuery(sql,null,null);
		  while(cur.moveToNext())
		  {
			  cid = cur.getString(0);
		  }
		  cur.close();
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return cid;	  
  }
  public static String QueryFTime()
  {
	  String mess = "";
	  try
	  {
		  sql = "select ftime from firsttime";
		  cur = sld.rawQuery(sql,null);
		  if(!cur.moveToNext())
		  {
			  cur.close();
			  mess="";
		  }
		  else
		  {
			  cur.moveToPrevious();
			  while(cur.moveToNext())
			  {
				  mess = cur.getString(0);
			  }
		  }
		cur.close();	  
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return mess;
  }
  
  public static void InsertFTime(String ftime)
  {
	  try
	  {
		  sql = "insert into firsttime values('F10001','"+ftime+"');";
		  sld.execSQL(sql);
		  
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  //最大课程信息ID
 	public static String MaxCourceMessID(){
 		String maxid="";
 		try{
 			sql="select max(ccid) from course";    	
 			cur=sld.rawQuery(sql, null);
 	     	while(cur.moveToNext()){
 	     		maxid=cur.getString(0);
 	     	}
 	     	cur.close();		
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return maxid;
 	}
 	//添加课程信息开始ID
 	public static String NextCourceMessID(){
 		String str=MaxCourceMessID();
 		String mss="";
 		if(str==null){  
 			mss="C10001";
 		}
 		else{
 		 	mss= str.substring(1,str.length());
 		 	mss=str.substring(0,1)+(Integer.parseInt(mss)+1);
 		}
 		return mss;
 	}
 	public static List<String[]> QueryAllCourceMess(String cweeks)
 	{
 		List<String[]> list = new ArrayList<String[]>();
 		try
 		{
 			sql = "select coursename,cteacher,place,cnum,cweeks,cweek from course,coursetime where course.ccid = coursetime.ccid and cweeks like'%"+cweeks+"%';";
 			cur = sld.rawQuery(sql,null);
 			if(!cur.moveToNext())
 			{
 				list = null;
 				cur.close();
 			}
 			else
 			{
 				cur.moveToPrevious();
 				while(cur.moveToNext())
 				{
 					String str[] = new String[6];
 					for(int i = 0 ; i < str.length ; i++)
 					{
 						str[i] = cur.getString(i);
 					}
 					list.add(str);
 				}
 				cur.close();
 			}
 		}catch(Exception e)
 		{
 			e.printStackTrace();
 		}
 		return list;
 	}
 	
    public static String[] QueryOneCourceTime(String cname){
    	String cid=GetCourceIDByName(cname);
    	String str[]=new String[3];
    	try{
        	sql="select cnum,cweeks,cweek from coursetime where ccid='"+cid+"';";    	
        	cur=sld.rawQuery(sql, null, null);
        	while(cur.moveToNext()){
        		for(int i=0;i<str.length;i++){
        			str[0]=cur.getString(0);
            		str[1]=cur.getString(1);
            		str[2]=cur.getString(2);
        		}
        	}
        	cur.close();		
    	}catch(Exception e){
			e.printStackTrace();
		}
    	return str;
    }
    
    public static boolean QueryCourseIfRepeat(String[] courseTime)
    {
    	boolean bb = true;
    	sql = "select * from coursetime where cnum='"+courseTime[0]+"' and cweeks like '%"+courseTime[1]+"%' and cweek='"+courseTime[2]+"';";
        cur = sld.rawQuery(sql, null);
        if(cur.moveToNext())
        {
        	bb = false;
        }
        else
        {
        	bb = true;
        }
    	
    	return bb;
    }
    public static boolean QueryTimeIfExist(String cname,String[] info){
    	boolean bb=true;
    	String cid=GetCourceIDByName(cname);
    	sql="select * from coursetime where ccid='"+cid+"' and cnum='"+info[0]+"' and cweeks='"+info[1]+"' and cweek='"+info[2]+"';";
    	cur=sld.rawQuery(sql, null);
    	if(cur.moveToNext()){
    		bb=false;
    	}else{
    		bb=true;
    	}
    	return bb;
    }
  public static List<String[]> GetCourceByWeeks(String cweeks,String cweek)
  {
	  List<String[]> list = new ArrayList<String[]>();
	  try
	  {
		  sql = "select coursename,cteacher,place,cnum,cweeks,cweek from course,coursetime where course.ccid=coursetime.ccid and cweeks like '%"+cweeks+"%' and cweek ='"+cweek+"' order by cnum";
		  cur = sld.rawQuery(sql, null);
		  if(!cur.moveToNext())
		  {
			  list = null;
			  cur.close();
		  }
		  else
		  {
			  cur.moveToPrevious();
			  while(cur.moveToNext())
			  {
				  String str[] = new String[6];
				  for(int i = 0 ; i < str.length ; i++)
				  {
					  str[i] = cur.getString(i);
				  }
				  list.add(str);
			  }
			  cur.close();
		  }
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	    return list;
  }
  
  public static List<String[]> GetIdByCweeks(String cweeks)
  {
	  List<String[]> list = new ArrayList<String[]>();
	  try
	  {
		  sql = "select ccid,cnum,cweeks,cweek from coursetime where cweeks like '%"+cweeks+"%';";
		  cur = sld.rawQuery(sql,null,null);
		  if(!cur.moveToNext())
		  {
			  list = null;
			  cur.close();
		  }else
		  {
			  cur.moveToPrevious();
			  while(cur.moveToNext())
			  {
				  String str[] = new String[4];
				  for(int i = 0 ; i < str.length ; i++)
				  {
					  str[i] = cur.getString(i);
				  }
				  list.add(str);
			  }
			  cur.close();
		  }
		  
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return list;
  }
  
  public static String ReSetCource(String firstName,String[] updateMess,String[] updateTime)
  {
	  String cid = GetCourceIDByName(firstName);
	  try
	  {
		sql = "update course set coursename='"+updateMess[0]+"',place='"+updateMess[1]+"',cteacher='"+updateMess[2]+"' where ccid= '"+cid+"';";
		sld.execSQL(sql);
		sql = "update coursetime set cnum ='"+updateTime[0]+"',cweeks='"+updateTime[1]+"',cweek='"+updateTime[2]+"' where ccid='"+cid+"';";
		sld.execSQL(sql);
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	  
	  return "ok";
  }
  
  public static List<String[]> QueryWidgetMess(String cweek,String cweeks){
  	List<String[]> list=new ArrayList<String[]>();
  	try{
  		sql="select distinct cnum,coursename,place,cweeks from course,coursetime where course.ccid=coursetime.ccid and coursetime.ccid in (select ccid from coursetime where cweek='"+cweek+"' and cweeks like '%"+cweeks+"%') order by cnum;";    	
  		cur=sld.rawQuery(sql, null, null);
  		if(!cur.moveToNext()){
  			list=null;
  			cur.close();
  		}
  		else{
  			cur.moveToPrevious();
  			while(cur.moveToNext()){
  				String str[]=new String[4];
  				for(int j=0;j<str.length;j++){
  					str[j]=cur.getString(j);
  				}
  				list.add(str);
  			}
  			cur.close();		
  		}
  	}catch(Exception e){
  		e.printStackTrace();
  	}
  	return list;
 }
  //删除指定时间段的课程
  public static void DeleteCourse(String[] info)
  {
  	String cid=GetCourceIDByName(info[0]);
  	try{
  		sql="delete from coursetime where ccid='"+cid+"' and cnum='"+info[3]+"'and cweeks='"+info[4]+"' and cweek='"+info[5]+"';";
  		sld.execSQL(sql);
  	}catch(Exception e){
  		e.printStackTrace();
  	}
  }
 public static void insertCourceMess(String[] str)
 {
	 String id = NextCourceMessID();
	 try
	 {
		 sql = "insert into course values('"+id+"','"+str[0]+"','"+str[1]+"','"+str[2]+"');";
		 sld.execSQL(sql);
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
 }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
