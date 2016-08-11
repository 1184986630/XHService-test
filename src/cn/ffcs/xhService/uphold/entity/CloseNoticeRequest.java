package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 销号提醒
 * */
public class CloseNoticeRequest {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String logoffTime; // 销号时间
	private int remainDays; // 销号天数
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVirtualNo() {
		return virtualNo;
	}

	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}

	public String getLogoffTime() {
		return logoffTime;
	}

	public void setLogoffTime(String logoffTime) {
		this.logoffTime = logoffTime;
	}

	public int getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, CloseNoticeRequest.class);
    }
}
