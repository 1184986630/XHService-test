package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class SessionidResponse extends Response {
	private String Sessionid; // 应答报文编码
	
	public String getSessionid() {
		return Sessionid;
	}

	public void setSessionid(String sessionid) {
		Sessionid = sessionid;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, SessionidResponse.class);
    }
}
