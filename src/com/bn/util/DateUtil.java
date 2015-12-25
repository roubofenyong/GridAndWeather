package com.bn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
 static SimpleDateFormat format = new SimpleDateFormat("yyyy<#>MM<#>dd");
 static long dateDiff = 0;
 static int ff = 0;
 static int year,month,day;
 public static int now_year,now_month,now_day,now_week;
 private static void dateDiff(String startTime,String endTime,String format)
 {
	 SimpleDateFormat sf = new SimpleDateFormat(format);
	 long nd = 1000 * 24 * 60 * 60;
	 long diff;
	 try
	 {
		 diff = sf.parse(endTime).getTime() - sf.parse(startTime).getTime();
		 long day = diff/nd;
		 dateDiff = Math.abs(day);
		 
	 }catch(ParseException e)
	 {
		 e.printStackTrace();
	 }
 }
 
 public static Date getDateAfter(Date d,int day)
 {
	 Calendar now = Calendar.getInstance();
	 now.setTime(d);
	 now.set(Calendar.DATE, now.get(Calendar.DATE)+day);
	 return now.getTime();	 
 }
 
 public static void getNowTime()
 {
	 final Calendar c = Calendar.getInstance();
	 c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
	 now_year = Integer.parseInt(String.valueOf(c.get(Calendar.YEAR)));
	 now_month = Integer.parseInt(String.valueOf(c.get(Calendar.MONTH)));
	 now_day = Integer.parseInt(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
	 now_week = Integer.parseInt(String.valueOf(c.get(Calendar.DAY_OF_WEEK)));
 }
 
 public static int[] wholeWeekDate(int weeksNum)
 {
	 currtWeeks();
	 int[] everyMonthDate = {31,28,31,30,31,30,31,31,30,31,30,31};
	 String[] info = getWeeksNumDate(weeksNum).split("<#>");
	 int temp_year = Integer.parseInt(info[0]);
	 int temp_month = Integer.parseInt(info[1]);
	 int temp_day = Integer.parseInt(info[2]);
	 int[] finalInfo = {temp_month,temp_day,0,0,0,0,0,0};
	 int temp;
	 if(temp_year % 400 == 0||(temp_year%4==0&&temp_year%100!=0))
	 {
		 everyMonthDate[1] = 29;
	 }
	 if(temp_month == 2)
	 {
		 for(int j = 2 ; j < 8 ; j++)
		 {
			 temp = temp_day + j -1;
			 if(temp > everyMonthDate[1])
			 {
				 finalInfo[j] = temp - everyMonthDate[1];
			 }
			 else
			 {
				 finalInfo[j] = temp;
			 }
		 }
	 }
	 else if(temp_month == 4||temp_month == 6|| temp_month == 9 || temp_month==11)
	 {
		 for(int j = 2 ; j < 8 ; j++)
		 {
			 temp = temp_day + j -1;
			 if(temp > 30)
			 {
				 finalInfo[j] = temp - 30;
			 }
			 else
			 {
				 finalInfo[j] = temp;
			 }
		 }
	 }
	 else
	 {
		for(int j = 2 ; j < 8 ; j++)
		{
			temp = temp_day + j -1;
			if(temp > 31)
			{
				finalInfo[j] = temp - 31;
			}
			else
			{
				finalInfo[j] = temp;
			}
		}
		 
		 
	 }
	 
	 return finalInfo;
 }

private static String getWeeksNumDate(int weeksNum) {
	// TODO Auto-generated method stub
	int diffWeek = weeksNum - Constant.currtWeeksNum;
	System.out.println("差的周数" + diffWeek);
	System.out.println("fff" + ff);
	int tt = -ff + 7 * diffWeek;
	System.out.println("减少的天数"+tt);
	Date date = new Date();
	date = getDateAfter(date, tt);
	return format.format(date);
}

public static int currtWeeks() {
	// TODO Auto-generated method stub
	getNowTime();
	startDate();
	dateDiff(new SimpleDateFormat("yyyy<#>MM<#>dd").format(new Date()),year+"<#>"+month+"<#>"+day,"yyyy<#>MM<#>dd");
    int trueDiff = 0;
    switch(now_week)
    {
    case 1: trueDiff = (int)(dateDiff-6); ff = 6;break;
    case 2: trueDiff = (int)(dateDiff); ff = 0; break;
    case 3: trueDiff = (int)(dateDiff-1); ff = 1; break;
    case 4: trueDiff = (int)(dateDiff-2); ff = 2; break;
    case 5: trueDiff = (int)(dateDiff-3); ff = 3; break;
    case 6: trueDiff = (int)(dateDiff-4); ff = 4; break;
    case 7: trueDiff = (int)(dateDiff-5); ff = 5; break;
    }
    Constant.currtWeeksNum = trueDiff/7+1;
    return Constant.currtWeeksNum;
}

private static void startDate() {
	// TODO Auto-generated method stub
	String[] startDate = SQLiteUtil.QueryFTime().split("<#>");
	year = Integer.parseInt(startDate[0]);
	month = Integer.parseInt(startDate[1]);
	day = Integer.parseInt(startDate[2]);
	int temp_year = year;
	int temp_month = month;
	int temp_day = day;
	if(temp_month == 1|| temp_month == 2)
	{
		temp_month += 12;
		temp_year -= 1;
	}
	int week = (temp_day + 2 * temp_month + 3 * (temp_month+1)/5 + temp_year + temp_year/4 - temp_year/100 + temp_year/400)%7;
	if(week != 0)
	{
		Date date = new Date(year,month,day);
		date = getDateAfter(date, -week);
		String[] dd = format.format(date).split("<#>");
		year = Integer.parseInt(dd[0]) - 1900;
		month = Integer.parseInt(dd[1]) -1;
		day = Integer.parseInt(dd[2]);
	}
}
}
