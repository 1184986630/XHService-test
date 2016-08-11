package cn.ffcs.xhService.uphold.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.CryptToolkit;

/**
 * 报文头
 * */
public class HeaderInfo {

	private String methodName; // 接口名
	private String spid;
	private String appid;
	private String passwd;
	private String timeStamp; // 报文发送时的时间yyyyMMddhhmmss
	private String authenticator;

	public HeaderInfo() {
		spid = Constants.HEADER_SPID;
		appid = Constants.HEADER_APPID;
		passwd = Constants.HEADER_PASSWD;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(String authenticator) {
		this.authenticator = authenticator;
	}

	public String toXmlString() {
		StringBuilder xml = new StringBuilder();
		Class<?> clazz = this.getClass();
		// 设置时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		this.timeStamp = sdf.format(new Date());
		// 设置认证码
		String auth = this.getTimeStamp() + this.getMethodName() + this.getSpid() + this.getPasswd();
		this.setAuthenticator(CryptToolkit.sha1Encrypt(auth));
		
		// 拼接报文头
		String className = "Head";
		xml.append("<").append(className).append(">");
		Field fields[] = clazz.getDeclaredFields();
		try {
			for (Field f : fields) {
				String filed = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				String methodName = "get" + filed;
				Method getMethod = clazz.getMethod(methodName);
				Object value = getMethod.invoke(this, new Object[] {});
				if(value == null) {
					value = "";
				}
				
				xml.append("<").append(filed).append(">");
				xml.append(value.toString());
				xml.append("</").append(filed).append(">");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		xml.append("</").append(className).append(">");
		return xml.toString();
	}
}
