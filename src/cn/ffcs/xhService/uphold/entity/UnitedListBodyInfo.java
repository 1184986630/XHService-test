package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 发送给统一平台List Body信息
 * */
public class UnitedListBodyInfo {
	private Object list;
	private String spId;
	
	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, UnitedListBodyInfo.class);
    }
}
