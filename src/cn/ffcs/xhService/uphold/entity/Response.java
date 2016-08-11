package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class Response {
	private String retCode; // 应答报文编码
	private String retMsg; // 应答报文结果描述
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, Response.class);
    }
}
