package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 到期提醒
 * */
public class ExpireNoticeRequest {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String expireTime; // 到期时间
	private int remainDays; // 到期时间
	
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

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
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
		return JSON.parseObject(jsonString, ExpireNoticeRequest.class);
    }
}
