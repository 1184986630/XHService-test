package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class UnreadResponse{
	private Object unreadlist;

	public Object getUnreadlist() {
		return unreadlist;
	}

	public void setUnreadlist(Object unreadlist) {
		this.unreadlist = unreadlist;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DataResponse.class);
    }
}
