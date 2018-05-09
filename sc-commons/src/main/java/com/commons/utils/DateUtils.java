package com.commons.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/***
	 * 获取当前时间字符串日期：yyyy-MM-DD HH:mm:ss
	 * @return
	 */
	public static String getDateStringForYMDHMS(){
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sm.format(date);
		return str;
	}
	
	/***
	 * 获取当前时间字符串日期：yyyyMMDD
	 * @return
	 */
	public static String getDateYMD(){
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
		String str = sm.format(date);
		return str;
	}

	/***
	 * 获取当前时间字符串日期：yyyy-MM-DD
	 * @return
	 */
	public static String getDateStringForYMD(){
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String str = sm.format(date);
		return str;
	}
	
	/***
	 *  获取当前时间字符串日期：x小时y分钟前 || xx天前 ||xx月前 || ""
	 * @param dateString yyyy-mm-dd hh:mm:ss
	 * @return
	 * @throws ParseException 
	 */
	public static String getDateStringForHM(String dateString) throws ParseException{
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fabu = sm.parse(dateString);
		long now = date.getTime();
		long old = fabu.getTime();
		long sec = now-old;
		long s = sec/1000L;
		long m = s/60L;
		long min = m%60L;
		long h = m/60L;
		long d = h/24L;
		long M = d/30;
		long y = M/12;
		String str = "";
		if(y>0){
			return "";
		}
		if(M>0){
			str = M+"月前";
			return str;
		}
		if(d>0){
			str = d+"天前";
			return str;
		}
		
		if(h>0){
			str = h+"小时前";
			return str;
		}
		if(min>0){
			str = min+"分钟前";
			return str;
		}
		
		return str;
	}
	
	/**
	 * 以字符串方式获取yyMMddHHmmssssss(到毫秒)格式的日期
	 * 
	 * @return
	 */
	public static String getDateTimeSsss() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
			return sdf.format(new Date());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 以字符串方式获取yyMMddHHmmssssss(到毫秒)格式的日期
	 * 
	 * @return
	 */
	public static String getDateTimess() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String st = date.getTime()+"";
			st = st.substring(st.length()-4);
			String datestr =  sdf.format(date);
			return datestr+st;
		} catch (Exception e) {
			return null;
		}
	}
	/***
	 * 判断文件创建时间是否小于当前时间的24时之前
	 * @param filename
	 * @return 小于 等于 true   大于 false
	 * @throws ParseException
	 */
	public static boolean getLessthan24Hour(String filename) throws ParseException {
		int index = filename.indexOf("_");
		if(index>0){
			filename = filename.substring(0, index);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
		Date before = sdf.parse(filename);
		Date now = new Date();
		long befnum = before.getTime();
		long nownum = now.getTime();
		long daytime = 24*60*60*1000;
		long cha = nownum-daytime;
		if(cha>befnum){
			return true;
		}else{
			return false;
		}
	}
	
	/***
	 * 判断文件创建时间是否小于当前时间的24时之前
	 * @param filename
	 * @return 小于 等于 true   大于 false
	 * @throws ParseException
	 */
	public static boolean getLessthan1s(String filename) throws ParseException {
		int index = filename.indexOf("_");
		if(index>0){
			filename = filename.substring(0, index);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
		Date before = sdf.parse(filename);
		Date now = new Date();
		long befnum = before.getTime();
		long nownum = now.getTime();
		long cha = nownum-befnum;
		if(cha<=1500){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 获取当前年月日yyyy-MM-dd HH:mm:ss
	* @Title: getDateTime 
	* @Description: TODO(...) 
	* @param @return    设定文件 
	* @return String    返回类型 
	 */
	public static String getDateTime(){
		String timeStr="";
				//实例Calendar对象
				Calendar myCalendar=Calendar.getInstance();
				//获取当前系统年
				String year=Integer.toString(myCalendar.get(Calendar.YEAR));
				//获取当前系统月
				String month=Integer.toString(myCalendar.get(Calendar.MONTH));
				//获取当前系统日
				String day=Integer.toString(myCalendar.get(Calendar.DAY_OF_MONTH));

				//获取当前系统时(24小时制)
				int inth=myCalendar.get(Calendar.HOUR_OF_DAY);
				//分
				int intm=myCalendar.get(Calendar.MINUTE);
				//秒
				int ints=myCalendar.get(Calendar.SECOND);
				String strh=null;
				String strm=null;
				String strs=null;
				if(inth<10){
					strh="0"+inth;
				}else{
					strh=Integer.toString(inth);
				}
				if(intm<10){
					strm="0"+intm;
				}else{
					strm=Integer.toString(intm);
				}
				if(ints<10){
					strs="0"+ints;
				}else{
					strs=Integer.toString(ints);
				}
				timeStr=year+"-"+month+"-"+day+" "+strh+":"+strm+":"+strs;
				return timeStr;

	}
	
	/**
	 * 判断是不是同一天
	* @Title: isSameDay 
	* @Description: TODO(...) 
	* @param @param stamp
	* @param @return    设定文件 
	* @return boolean    返回类型 
	 */
	public static boolean isSameDay(Date stamp) {

		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String dString = f.format(d);

//		Date currentTime = stamp;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateString = format.format(stamp);

		if (dateString.equals(dString)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 通过一个固定的时间和当前时间相比 得到天数
	 * 
	 * @param lastFecthTime
	 * @return
	 */
	public static int getDays(Date stamp) {
		long nowTime = (System.currentTimeMillis() - stamp.getTime()) / 1000;// 得到秒
		nowTime /= 60;// 得到分
		nowTime /= 60;// 得到小时
		nowTime /= 24;// 得到天
		// Date d = new Date(System.currentTimeMillis());
		// SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		// String dString = f.format(d);
		//
		// Date currentTime = stamp;
		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// String dateString = format.format(currentTime);
		return (int) nowTime;
	}
	

	/**
	 * 
	 * dateFormat:(). <br/>
	 * TODO("将字符串转换为毫秒时间").<br/>
	 * 
	 * @author zg
	 * @param dateStr
	 * @return
	 */
	public static long dateFormat(String dateStr) {
		SimpleDateFormat sdfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdfl.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		if (date != null) {
			return date.getTime();
		} else {
			return 0;
		}
	}
	


	/**
	 * overDayAndHour:(). <br/>
	 * TODO().<br/>
	 * 是否是第二天,并超过规定的小时
	 * 
	 * @author zg
	 * @param stamp
	 * @return true 表示超过了规定时间
	 */
	public static boolean overDayAndHour(Timestamp stamp, int hour) {
		// 当前时间
		Calendar c = Calendar.getInstance();
		// 上次更新时间
		Calendar l = Calendar.getInstance();
		l.setTimeInMillis(stamp.getTime());
		if ((c.getTimeInMillis() - stamp.getTime()) / 1000 / 60 / 60 >= 24) {
			return true;
		}

		// 判断是否是同一天
		if (c.get(Calendar.DAY_OF_YEAR) == l.get(Calendar.DAY_OF_YEAR)) {
			if (l.get(Calendar.HOUR_OF_DAY) >= hour) {
				return false;
			}
		}

		// 判断是否超过规定的小时
		if (c.get(Calendar.HOUR_OF_DAY) < hour) {
			return false;
		}

		return true;
	}
	


	/**
	 * 
	 * overDayAndMinute:(). <br/>
	 * TODO(是否是第二天,并超过规定的小时和规定分钟).<br/>
	 * 
	 * @author zg
	 * @param stamp
	 * @param hour
	 * @param minute
	 * @return true 表示超过了规定时间
	 */
	public static boolean overDayAndMinute(Timestamp stamp, int hour, int minute) {
		Calendar c = Calendar.getInstance();
		Calendar l = Calendar.getInstance();
		l.setTimeInMillis(stamp.getTime());
		if ((c.getTimeInMillis() - stamp.getTime()) / 1000 / 60 / 60 >= 24) {
			return true;
		}

		// 判断是否是同一天
		if (c.get(Calendar.DAY_OF_YEAR) == l.get(Calendar.DAY_OF_YEAR)) {
			if (l.get(Calendar.HOUR_OF_DAY) >= hour) {
				return false;
			}
		}

		// 判断是否超过规定的小时
		if (c.get(Calendar.HOUR_OF_DAY) < hour) {
			return false;
		}

		// 判断是否超过规定的分钟
		if (c.get(Calendar.HOUR_OF_DAY) < minute) {
			return false;
		}

		return true;
	}
	
	/**
	 * 判断今天 昨天 前天...
	* @Title: parseDate 
	* @Description: TODO(...) 
	* @param @param createTime
	* @param @return    设定文件 
	* @return int    返回类型 0:今天，1：昨天，2：前天。。。。
	 */
	public static int getNumDay(String createTime){    
        try {
            int ret = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long create = sdf.parse(createTime).getTime();
            Calendar now = Calendar.getInstance();
            long ms  = 1000*(now.get(Calendar.HOUR_OF_DAY)*3600+now.get(Calendar.MINUTE)*60+now.get(Calendar.SECOND));//毫秒数
            long ms_now = now.getTimeInMillis();
            if(ms_now-create<ms){
                ret = 0;
            }else if(ms_now-create<(ms+24*3600*1000)){
                ret = 1;
            }else if(ms_now-create<(ms+24*3600*1000*2)){
                ret = 2;
            }else{
                ret= 3;
            }
            return ret;
            } catch (Exception e) {
            e.printStackTrace();
            }
        return -1;
    }
	
	/**
	 * 
	 * isSameTime:(). <br/>
	 * TODO("判断两个时间是否在同一分钟").<br/>
	 * 
	 * @author zg
	 * @param curCalendar
	 * @param oldCalendar
	 * @return
	 */
	public static boolean isSameTimeByMinute(Timestamp curTimestamp, Timestamp oldTimestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

		String currentTimeString = format.format(curTimestamp);

		String oldTimeString = format.format(oldTimestamp);

		if (currentTimeString.equals(oldTimeString)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * getDay:(). <br/>
	 * TODO("通过两个毫秒数计算之间相差的天数").<br/>
	 * 
	 * @author zg
	 * @param maxTimeMillis
	 * @param minTimeMillis
	 * @return
	 */
	public static int getDay(long maxTimeMillis, long minTimeMillis) {
		int day = 0;
		if (maxTimeMillis >= minTimeMillis) {
			day = (int) ((maxTimeMillis - minTimeMillis) / 1000 / 60 / 60 / 24);
		}
		return day;
	}

	/**
	 * 
	 * dateFormat:(). <br/>
	 * TODO(将时间转换为指定格式的字符串).<br/>
	 * 
	 * @author zg
	 * @param pattern
	 *            格式
	 * @param obj
	 * @return
	 */
	public static String dateFormat(String pattern, Object obj) {
		return new SimpleDateFormat(pattern).format(obj);
	}
	
	/**
	 * 将格式的字符串转换为时间
	* @Title: dateParse 
	* @Description: TODO(...) 
	* @param @param pattern   格式
	* @param @param obj  转换的对象
	* @param @return
	* @param @throws ParseException    设定文件 
	* @return Date    返回类型 
	 */
	public static Date dateParse(String pattern,Object obj) throws ParseException{
		return new SimpleDateFormat(pattern).parse(pattern);
	}

	/**
	 * 
	 * nowAndTomorrow:(). <br/>
	 * TODO("指定的时间的明天的 00:00:00 点的毫秒数").<br/>
	 * 
	 * @author zg
	 * @param currentTimeMillis
	 * @return
	 */
	public static long nowAndTomorrow(long currentTimeMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(currentTimeMillis);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1, 0, 0,
			0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 
	 * isSameDay:(). <br/>
	 * TODO("判断两个时间是不是同一天").<br/>
	 * 
	 * @author zg
	 * @param stamp1
	 * @param stamp2
	 * @return
	 */
	public static boolean isSameDay(Date stamp1, Date stamp2) {

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String dString = f.format(stamp1);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dateString = format.format(stamp2);

		if (dateString.equals(dString)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 取得当月天数
	* @Title: getCurrentMonthLastDay 
	* @Description: TODO(...) 
	* @param @return    设定文件 
	* @return int    返回类型 
	 */
	public static int getCurrentMonthLastDay()  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	} 
	
	/**
	 * 得到指定月的天数   
	* @Title: getMonthLastDay 
	* @Description: TODO(...) 
	* @param @param year
	* @param @param month
	* @param @return    设定文件 
	* @return int    返回类型 
	 */
	public static int getMonthLastDay(int year, int month)  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.YEAR, year);  
	    a.set(Calendar.MONTH, month - 1);  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	}  
	
	
	/**
	 * 获取指定的日
	* @Title: getMonthName 
	* @Description: TODO(...) 
	* @param @param date
	* @param @return    设定文件 
	* @return String    返回类型 
	 */
	public static String getDayName(String date) throws Exception{
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date formatDate=sdf.parse(date);
		String month=new SimpleDateFormat("dd").format(formatDate);
		return month;
		
	}
	
	/**
	 * 获取指定的日
	* @Title: getMonthName 
	* @Description: TODO(...) 
	* @param @param date
	* @param @return    设定文件 
	* @return String    返回类型 
	*
	 */
	public static String getDayName(Date date) throws Exception{
		String month=new SimpleDateFormat("dd").format(date);
		return month;
		
	}
	
	/**
	 * 判断与当前时间是否是同一个月
	* @Title: TheSameMonth 
	* @Description: TODO(...) 
	* @param @param dateStr
	* @param @return
	* @param @throws Exception    设定文件 
	* @return boolean    返回类型 
	 */
	public static boolean TheSameMonth(String dateStr)throws Exception{
		Date d=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		String strMonth = new SimpleDateFormat("MM").format(d);
		//实例Calendar对象
		Calendar myCalendar=Calendar.getInstance();
		//获取当前系统月
		String month=Integer.toString(myCalendar.get(Calendar.MONTH)+1);
		
		if(month.equals(strMonth)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public static void main(String[] args)  {
		Calendar a = Calendar.getInstance(); 
		System.out.println(getCurrentMonthLastDay());
		Date date = new Date();  
        String year = new SimpleDateFormat("yyyy").format(date);  
        String month = new SimpleDateFormat("MM").format(date);
        
	}
}
