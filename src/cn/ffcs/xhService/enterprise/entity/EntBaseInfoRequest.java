package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业基础请求数据
 * */
public class EntBaseInfoRequest {
	private String spId; // 企业ID
	private String appId; // 应用ID

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
   
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntBaseInfoRequest.class);
    }
	
	public String toString() {
		return "spId='" + (spId == null ? "" : spId) + "', appId='" + (appId == null ? "" : appId) + "'";
	}
}
