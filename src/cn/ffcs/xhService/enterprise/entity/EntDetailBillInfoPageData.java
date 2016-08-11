package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

public class EntDetailBillInfoPageData extends EntPageInfoData {
	private Long callerTotal; // 拨打总时长
	private Long calledTotal; // 接听总时长
	private Long smsTotal; // 短信总条数
   
	public Long getCallerTotal() {
		return callerTotal;
	}

	public void setCallerTotal(Long callerTotal) {
		this.callerTotal = callerTotal;
	}

	public Long getCalledTotal() {
		return calledTotal;
	}

	public void setCalledTotal(Long calledTotal) {
		this.calledTotal = calledTotal;
	}

	public Long getSmsTotal() {
		return smsTotal;
	}

	public void setSmsTotal(Long smsTotal) {
		this.smsTotal = smsTotal;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntDetailBillInfoPageData.class);
    }
}
