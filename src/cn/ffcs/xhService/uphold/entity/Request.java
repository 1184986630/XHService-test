package cn.ffcs.xhService.uphold.entity;

import java.util.Map;

public class Request {
	private HeaderInfo headerInfo = new HeaderInfo();
	private BodyInfo bodyInfo = new BodyInfo();
	
	public HeaderInfo getHeaderInfo() {
		return headerInfo;
	}

	public void setHeaderInfo(HeaderInfo headerInfo) {
		this.headerInfo = headerInfo;
	}

	public BodyInfo getBodyInfo() {
		return bodyInfo;
	}

	public void setBodyInfo(BodyInfo bodyInfo) {
		this.bodyInfo = bodyInfo;
	}

	/**
	 * 设置接口名
	 * */
	public void setMethodName(String methodName) {
		headerInfo.setMethodName(methodName);
	}
	
	/**
	 * 设置报体数据
	 * */
	public void setFieldData(Map<String, Object> fieldData) {
		bodyInfo.setFieldData(fieldData);
	}

	/**
	 * 往报文体添加数据
	 * */
	public void put(String key, Object value) {
		bodyInfo.put(key, value);
	}
	
	/**
	 * 生成请求报文
	 * */
	public String toXmlString() {
		StringBuilder buf = new StringBuilder();
		String rootName = "Request";
		String less_than = "<";
		String greater_than = ">";
		
		buf.append(less_than);
		buf.append(rootName);
		buf.append(greater_than);
		
		buf.append(headerInfo.toXmlString());
		buf.append(bodyInfo.toXmlString());
		
		buf.append(less_than);
		buf.append("/");
		buf.append(rootName);
		buf.append(greater_than);
		
		return buf.toString();
	} 
	
	public static void main(String[] args) {
		String str = new Request().toXmlString();
		System.out.println(str);
	}
}
