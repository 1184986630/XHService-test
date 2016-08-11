package cn.ffcs.xhService.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateToolkit {
    private static final Logger logger = LoggerFactory.getLogger(DateToolkit.class);

    public final static String YYYYMMDD = "yyyy/MM/dd";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";
    public final static String YYYY_MM_DD_HH24_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
    public final static String YYYYMMDDHH24MMSS = "yyyyMMddHHmmss";
    public final static String HH_MM = "HH:mm";
    public final static String YYYYMM = "yyyyMM";

    public final static int YEAR_PATTERN = Calendar.YEAR;
    public final static int MONTH_PATTERN = Calendar.MONTH;
    public final static int DAY_PATTERN = Calendar.DAY_OF_YEAR;
    public final static int HOUR_PATTERN = Calendar.HOUR_OF_DAY;
    public final static int MINUTE_PATTERN = Calendar.MINUTE;
    public final static int SECOND_PATTERN = Calendar.SECOND;

    /**
     * 获取两个日期间的全部日期
     * 
     * @author huangjx
     * @param d1
     *            - 格式:yyyy-MM-dd
     * @param d2
     *            - 格式:yyyy-MM-dd
     * @return GregorianCalendar[]
     */
    public static GregorianCalendar[] getBetweenDate(String d1, String d2) {

        Vector<GregorianCalendar> v = new Vector<GregorianCalendar>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
        GregorianCalendar tmpgc = new GregorianCalendar();
        try {
            gc1.setTime(sdf.parse(d1));
            gc2.setTime(sdf.parse(d2));
            if (gc1.after(gc2)) {
                tmpgc = gc1;
                gc1 = gc2;
                gc2 = tmpgc;
            }
        } catch (ParseException e) {
            logger.error("日期格式解析失败！", e);
        }
        do {
            GregorianCalendar gc3 = (GregorianCalendar) gc1.clone();
            v.add(gc3);
            gc1.add(Calendar.DAY_OF_MONTH, 1);
        } while (!gc1.after(gc2));
        return v.toArray(new GregorianCalendar[v.size()]);
    }

    /**
     * 将字符串转换为Date类型
     * 
     * @param str
     * @param fmt
     * @return
     */
    public static Date String2Date(String str, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            logger.error("日期格式解析失败！", e);
        }
        return date;
    }

    /**
     * 将Date类型转换为字符串格式
     * 
     * @param date
     * @param fmt
     * @return
     */
    public static String Date2String(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    /**
     * 获得与今天相差指定天数的目标日期
     * 
     * @author huangjx
     * @return
     */
    public static GregorianCalendar getDistDate(int days) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DAY_OF_MONTH, days);
        return gc;
    }

    /**
     * 获得与今天相差指定天数的目标日期
     * 
     * @author huangjx
     * @return
     */
    public static GregorianCalendar[] getDistBetweenDate(int days) {
        String date1, date2;
        GregorianCalendar gc = new GregorianCalendar();
        date1 = gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1)
                + "-" + gc.get(Calendar.DAY_OF_MONTH);
        gc.add(Calendar.DAY_OF_MONTH, days);
        date2 = gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1)
                + "-" + gc.get(Calendar.DAY_OF_MONTH);
        return getBetweenDate(date1, date2);
    }

    public static Date getDateZone(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date getMonthZone(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, days);
        return cal.getTime();
    }

    public static Date getAllZone(Date date, int num, int pattern) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(pattern, num);
        return cal.getTime();
    };

    public static Date parseDateFromStr(String dateStr, String patternStr) {
    	Date date = null;
    	if(StringUtils.isEmpty(dateStr)) {
    		return date;
    	}
    	if(StringUtils.isEmpty(patternStr)) {
    		patternStr = YYYY_MM_DD_HH24_MM_SS;
    	}
    	
        SimpleDateFormat sdf = new SimpleDateFormat(patternStr);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("日期转换出错...dateStr=" + dateStr + ", patternStr" + patternStr);
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间格式转换
     * @param date Date
     * @param patternStr String
     * @return String
     * */
    public static String pareseDate(Date date, String patternStr) {
    	if(StringUtils.isEmpty(patternStr)) {
    		patternStr = YYYY_MM_DD_HH24_MM_SS;
    	}
        SimpleDateFormat sdf = new SimpleDateFormat(patternStr);
        return sdf.format(date);
    }
    
    /**
     * 获取两个时间间的间隔天数:结束日期-开始日期
     * 
     * @param start
     *            - 格式:yyyy-MM-dd HH:mm:ss
     * @param end
     *            - 格式:yyyy-MM-dd HH:mm:ss
     * @return int
     */
    public static int getTimeBetween(String start, String end) {
    	if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
    		logger.debug("start=" + start + ",end=" + end);
    		return -1;
    	}
    	
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH24_MM_SS);
        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        try {
			fromCalendar.setTime(sdf.parse(start));
			fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
			fromCalendar.set(Calendar.MINUTE, 0);
			fromCalendar.set(Calendar.SECOND, 0);
			fromCalendar.set(Calendar.MILLISECOND, 0);
			
			toCalendar.setTime(sdf.parse(end));
			toCalendar.set(Calendar.HOUR_OF_DAY, 0);
			toCalendar.set(Calendar.MINUTE, 0);
			toCalendar.set(Calendar.SECOND, 0);
			toCalendar.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
			return -1;
		}
        long range = toCalendar.getTime().getTime() - fromCalendar.getTime().getTime();
        long devided = 1000 * 60 * 60 * 24;
        
        return new Long(range/devided).intValue();
    }
    
    /**
     * 获取两个时间间的间隔天数，向下取整:结束时间-开始时间
     * 
     * @param start
     *            - 格式:yyyy-MM-dd HH:mm:ss
     * @param end
     *            - 格式:yyyy-MM-dd HH:mm:ss
     * @return int
     */
    public static int getTimeBetweenFloor(String start, String end) {
    	if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
    		logger.debug("start=" + start + ",end=" + end);
    		return -1;
    	}
    	
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH24_MM_SS);
        Calendar fromCalendar = Calendar.getInstance();
        Calendar toCalendar = Calendar.getInstance();
        try {
			fromCalendar.setTime(sdf.parse(start));
			toCalendar.setTime(sdf.parse(end));
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
		}
        long range = toCalendar.getTime().getTime() - fromCalendar.getTime().getTime();
        long devided = 1000 * 60 * 60 * 24; // 1天有多少毫秒
        if(range >= 0) { // 正数
        	return new Long(range/devided).intValue();
        } else { // 负数
        	if(range % devided == 0) { // 整除
        		return new Long(range/devided).intValue();
        	} else { // 有小数，向下取整.如-2.3取-3
        		return new Long(range/devided).intValue() - 1;
        	}
        }
    }

	/**
	 * 时间加上天数 
	 * 时间格式:yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
	 * 返回根据传入的格式
	 * */
	public static String timeAddDays(String time, String pattern, int days) {
		String temp = "";
		if(StringUtils.isEmpty(time)) {
			return temp;
		}
		if(StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH24_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			Date date = sdf.parse(time);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.DATE, days);
			temp = sdf.format(cl.getTime());
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
		}
		return temp;
	}
	
	/**
	 * 时间加上月数
	 * */
	public static String timeAddMonths(String time, String pattern, int months) {
		if(StringUtils.isEmpty(time)) {
			return "";
		}
		if(StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH24_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String temp = "";
		try {
			Date date = sdf.parse(time);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.MONTH, months);
			temp = sdf.format(cl.getTime());
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
		}
		return temp;
	}
	
	/**
	 * 获取当天的截止时间
	 * YYYY-MM-DD 23:59:59
	 * */
	public static String getTodayEndTime(String today) {
		if(StringUtils.isEmpty(today)) {
			return "";
		}
		
		Date sysdate = String2Date(today, YYYY_MM_DD_HH24_MM_SS);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sysdate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return Date2String(cal.getTime(), YYYY_MM_DD_HH24_MM_SS);
	}
	
	/**
	 * 获取当天的开始时间
	 * YYYY-MM-DD 00:00:00
	 * */
	public static String getTodayBeginTime(String today) {
		if(StringUtils.isEmpty(today)) {
			return "";
		}
		
		Date sysdate = String2Date(today, YYYY_MM_DD_HH24_MM_SS);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sysdate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return Date2String(cal.getTime(), YYYY_MM_DD_HH24_MM_SS);
	}
	
	/**
	 * 时间加上分钟数
	 * 时间格式:yyyy-MM-dd HH:mm:ss
	 * */
	public static String timeAddMinutes(String time, int minutes) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH24_MM_SS);
		String temp = "";
		try {
			Date date = sdf.parse(time);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.MINUTE, minutes);
			temp = sdf.format(cl.getTime());
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
		}
		return temp;
	}
	
	/**
	 * 获得月份的1号日期
	 * @param time String 需要获取当前月份1日的时间
	 * @param pattern String 传入时间的格式
	 * @return String yyyy-mm-dd
	 * */
	public static String getMonthFirstDate(String time, String pattern) {
		String str = "";
		if(StringUtils.isEmpty(time)) {
			return str;
		}
		if(StringUtils.isEmpty(pattern)) {
			pattern = YYYY_MM_DD_HH24_MM_SS;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(time);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.set(Calendar.DAY_OF_MONTH, 1); // 设置1号
			
			sdf = new SimpleDateFormat(YYYY_MM_DD);
			str = sdf.format(cl.getTime());
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
		}
		return str;
	}
	
	/**
	 * 计算过期时长，单位秒
	 * String begin 开始时间
	 * String pat1 开始时间格式
	 * String end 结束时间
	 * String pat2 结束时间格式
	 * */
	public static Long calExpireTime(String begintime, String pat1, String endtime, String pat2) {
		Long range = 0L;
		if(StringUtils.isEmpty(begintime) || StringUtils.isEmpty(endtime)) {
			return range;
		}
		if(StringUtils.isEmpty(pat1)) {
			pat1 = YYYY_MM_DD_HH24_MM_SS;
		}
		if(StringUtils.isEmpty(pat2)) {
			pat2 = YYYY_MM_DD_HH24_MM_SS;
		}
		Date beginDate = null;
		Date endDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pat1);
			beginDate = sdf.parse(begintime);
			
			sdf = new SimpleDateFormat(pat2);
			endDate = sdf.parse(endtime);
		} catch (ParseException e) {
			logger.error("日期格式解析失败！", e);
			return 0L;
		}
		// 日期随意
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); //日期随意
		
		// 为了获取 时分秒
		Calendar beginCal = Calendar.getInstance();
		beginCal.setTime(beginDate);
		beginCal.set(YEAR_PATTERN, cal.get(YEAR_PATTERN));
		beginCal.set(MONTH_PATTERN, cal.get(MONTH_PATTERN));
		beginCal.set(DAY_PATTERN, cal.get(DAY_PATTERN));
		
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.set(YEAR_PATTERN, cal.get(YEAR_PATTERN));
		endCal.set(MONTH_PATTERN, cal.get(MONTH_PATTERN));
		endCal.set(DAY_PATTERN, cal.get(DAY_PATTERN));
		
		if(beginCal.compareTo(endCal) >= 0) { // 结束时间小于开始时间，结束时间加1天
			endCal.add(DAY_PATTERN, 1);
		}
		return (endCal.getTimeInMillis()-beginCal.getTimeInMillis())/1000;
	}
}
