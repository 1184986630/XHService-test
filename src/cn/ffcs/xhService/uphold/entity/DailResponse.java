package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class DailResponse extends Response {
	private String sessionid; // 唯一标识，中断请求、获取状态回调、账单查询时使用

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DailResponse.class);
    }
}
