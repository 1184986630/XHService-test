package cn.ffcs.xhService.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

public class Function {
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	
    public synchronized static long getIntID(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        } 
        Date d=new Date(); 
        long l=d.getTime(); 
        return l;
    }
    
    public static String dealNull(String str) {
        if (str == null || str.equals(""))
            return null;
        else
            return str;
    }

    public static String getCoordinate(String coor){
        Pattern pattern = Pattern.compile("[0-9]{1,9}.[0-9]{1,9}");
        Matcher matcher = pattern.matcher(coor);
        if (matcher.find()){
            return matcher.group();
        }
        return coor;
    }
    
    public static boolean isXmlValid(String xml){
        try {
            DocumentHelper.parseText(xml).getRootElement();
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }
    
    public static boolean VerifyMacAddr(String mac) {
        Pattern pattern = Pattern.compile("[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}");
        Matcher matcher = pattern.matcher(mac);
        if (matcher.find() &&
                matcher.group().length() == mac.length()){
//            System.out.println(matcher.group());
            return true;
        }
        return false;
    }

    public static boolean VerifyDateTime(String time) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}");
        Matcher matcher = pattern.matcher(time);
        if (matcher.find() &&
                matcher.group().length() == time.length()){
//            System.out.println(matcher.group());
            return true;
        }
        return false;
    }

    public static boolean VerifyDate(String Date) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
        Matcher matcher = pattern.matcher(Date);
        if (matcher.find() &&
                matcher.group().length() == Date.length()){
//            System.out.println(matcher.group());
            return true;
        }
        return false;
    }
    
    /**
	 * 判断是否为手机号码
	 * */
	public static boolean isTelphone(String str) {
		if (str == null) {
			return false;
		}
		
		Pattern p = Pattern.compile("^1\\d{10}$");
		Matcher match = p.matcher(str);  
		return match.matches();  
	}
	
	/**
	 * 是否全为数字
	 * */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		
		Pattern p = Pattern.compile("\\d*");
		Matcher match = p.matcher(str);  
		return match.matches(); 
	}
	
	/**
	 * 年校验
	 * YYYY
	 * */
	public static boolean VerifyYear(String year) {
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(year);
        if (matcher.find() && matcher.group().length() == year.length()){
            return true;
        }
        return false;
    }
	
	/**
	 * 年月份校验
	 * YYYYMM
	 * */
	public static boolean VerifyMonth(String date) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{1,2}");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find() && matcher.group().length() == date.length()){
            return true;
        }
        return false;
    }
	
	/**
	 * 日期校验
	 * HH:mm
	 * */
	public static boolean VerifyTime(String time) {
        Pattern pattern = Pattern.compile("[0-9]{1,2}:[0-9]{1,2}");
        Matcher matcher = pattern.matcher(time);
        if (matcher.find() && matcher.group().length() == time.length()){
            return true;
        }
        return false;
    }
	
	/**
	 * 校验时间是否正确
	 * 23:00 true
	 * 24:00 false
	 * 20150101 true
	 * 20150001 false
	 * */
	public static boolean timeValidate(String str, String pattern) {
		if(str == null || str.equals("")) {
			return false;
		}
		
		if(StringUtils.isEmpty(pattern)) {
			pattern = DateToolkit.YYYY_MM_DD_HH24_MM_SS;
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			
			Calendar calst = Calendar.getInstance(); // 起始时间
			calst.setTime(sdf.parse(str));
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * 解析XML
	 * @param str
	 * @return
	 */
	public static String getResultValue(String xml, String key){
		String responseText = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//字符串的形式读入
			InputStream in = new ByteArrayInputStream(xml.getBytes("UTF8"));
			org.w3c.dom.Document doc = db.parse(in);
 			XPath xpath = XPathFactory.newInstance().newXPath();
 			NodeList responseTextList = (NodeList)xpath.evaluate("//" + key, doc, XPathConstants.NODESET);
 			if(responseTextList != null && responseTextList.item(0) != null) {
 				responseText = responseTextList.item(0).getTextContent();
 			} else {
 				return null;
 			}
		} catch (Exception e) {
			logger.error("解析XML失败", e);
			return null;
		}
		return responseText;
	}
	
	/**
	 * 固话判断:7-8位
	 * */
	public static boolean landlineVerify(String str) {
		if (str == null) {
			return false;
		}
		
		Pattern p = Pattern.compile("\\d{7,8}");
		Matcher match = p.matcher(str);  
		return match.matches(); 
	}
	
	/**
	 * List<String>转List<Long>
	 * */
	public static List<Long> CollStringToLongList(List<String> strList){
		if(strList == null || strList.size() == 0) {
			return null;
		}
        List<Long> iList =new ArrayList<Long>(strList.size());
        try {
        	CollectionUtils.collect(strList, 
        			new Transformer(){
        		public java.lang.Object transform(java.lang.Object input){
        			return new Long((String)input);
        		}
        	} ,iList );
		} catch (Exception e) {
			logger.error("List<String>转List<Long>失败", e);
			return null;
		}
        return iList;
    }
	
	/**
	 * List<String>转List<Integer>
	 * */
	public static List<Integer> CollStringToIntegerList(List<String> strList){
		if(strList == null || strList.size() == 0) {
			return null;
		}
        List<Integer> iList =new ArrayList<Integer>(strList.size());
        try {
        	CollectionUtils.collect(strList, 
        			new Transformer(){
        		public java.lang.Object transform(java.lang.Object input){
        			return new Integer((String)input);
        		}
        	} ,iList );
		} catch (Exception e) {
			logger.error("List<String>转List<Integer>失败", e);
			return null;
		}
        return iList;
    }
}
