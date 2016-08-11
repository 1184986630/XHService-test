package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

public class EntSettleBillInfoPageData extends EntPageInfoData {
	private String spName; // 企业名称
   
	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntSettleBillInfoPageData.class);
    }
}
