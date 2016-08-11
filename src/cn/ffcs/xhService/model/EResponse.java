package cn.ffcs.xhService.model;

import com.alibaba.fastjson.JSON;

public class EResponse {
	private String description; // 应答报文编码
	private int code; // 应答报文结果描述
	private Object dataObject = "";
	private String errorDescription;
	public Object getDataObject() {
		return dataObject;
	}
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EResponse.class);
    }
}
