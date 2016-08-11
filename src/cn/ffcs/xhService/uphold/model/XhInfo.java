package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 小号信息
 * */
public class XhInfo {
	private String appid; // 应用ID
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String createTime; // 创建时间
	private String effectTime; // 生效日期
	private String endTime; // 失效时间
	private Integer remainSMS; // 短信剩余条数
	private Integer closeStatus; // 开关机状态
	private String closeBegin; // 关机开始时间
	private String closeEnd; // 关机结束时间
	private Integer remainDays; // 可用天数
	private Integer useDays; // 累计使用天数
	private String logoffTime; // 注销时间
	private String weekday; // 星期设置
	private String statusUpdateTime; // 用户手动开关机状态修改时间
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getRemainSMS() {
		return remainSMS;
	}

	public void setRemainSMS(Integer remainSMS) {
		this.remainSMS = remainSMS;
	}

	public Integer getCloseStatus() {
		return closeStatus;
	}

	public void setCloseStatus(Integer closeStatus) {
		this.closeStatus = closeStatus;
	}

	public String getCloseBegin() {
		return closeBegin;
	}

	public void setCloseBegin(String closeBegin) {
		this.closeBegin = closeBegin;
	}

	public String getCloseEnd() {
		return closeEnd;
	}

	public void setCloseEnd(String closeEnd) {
		this.closeEnd = closeEnd;
	}

	public Integer getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(Integer remainDays) {
		this.remainDays = remainDays;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhInfo.class);
    }

	public Integer getUseDays() {
		return useDays;
	}

	public void setUseDays(Integer useDays) {
		this.useDays = useDays;
	}

	public String getLogoffTime() {
		return logoffTime;
	}

	public void setLogoffTime(String logoffTime) {
		this.logoffTime = logoffTime;
	}

	public String getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(String statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
}
