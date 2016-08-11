package cn.ffcs.xhService.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringEncoding {
    /**
     * 判断字符串的编码
     * 
     * @param str
     * @return
     */
    public static String getCharSet(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                return encode;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
    
    /**
     * 将字符串编码格式转成GB2312
     *
     * @param str
     * @return
     */
    public static String tranToGB2312(String str) {
        try {
            String strEncode = getCharSet(str);
            return new String(str.getBytes(strEncode), "GB2312");
        } catch (java.io.IOException ex) {
            return null;
        }
    }

    /**
     * 将字符串编码格式转成UTF-8
     *
     * @param str
     * @return
     */
    public static String tranToUTF8(String str) {
        try {
            String strEncode = getCharSet(str);
            return new String(str.getBytes(strEncode), "UTF-8");
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    
    /**
     * 统计字符串的字节数
     * 只要包含中文字符，全部按2个字节计算。
     * */
    public static int getBytesCount(String str) {
    	int len = 0; // 字符串字节数
    	if(StringUtils.isEmpty(str)) {
    		return len;
    	}
    	
    	boolean contains = false; // 是否包含中文字符
    	String regex = "[^\\x00-\\xff]";
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(str);
    	if(matcher.find()) {
    		contains = true;
    	}
    	
    	String charSet = getCharSet(str); // 字符串的编码方式
    	String temp = null;
    	String encode = "GBK";
    	if(!charSet.equals(encode)) {
    		temp = tranToGBK(str);
    	} else {
    		temp = str;
    	}
    	
    	if(contains) { // 包含中文字符
    		len = temp.length() * 2;
    	} else {
    		len = temp.length();
    	}
    	
    	return len;
    }
    
    /**
     * 将字符串编码格式转成GBK
     */
    public static String tranToGBK(String str) {
        try {
            String strEncode = getCharSet(str);
            String temp = new String(str.getBytes(strEncode), "GBK");
            return temp;
        } catch (java.io.IOException ex) {
            return null;
        }
    }
    
    /**
     * 部分特殊字符转换为Unicode编码
     * */
	public static String stringEncode(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}
		str = str.replaceAll("%", "%25").replaceAll("\\+", "%2B")
				.replaceAll("\\/", "%2F").replaceAll("\\?", "%3F")
				.replaceAll("#", "%23").replaceAll("&", "%26");
		return str;
	}

    public static void main(String args[]) {  
    }
}
