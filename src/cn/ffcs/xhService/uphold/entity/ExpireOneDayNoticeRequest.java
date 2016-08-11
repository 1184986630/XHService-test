package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 到期一天提醒
 * */
public class ExpireOneDayNoticeRequest {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String logoffTime; // 注销时间
	
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

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, ExpireOneDayNoticeRequest.class);
    }
}
