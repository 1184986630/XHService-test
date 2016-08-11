package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 开关机请求参数
 * */
public class CloseSettingInfoRequest extends QueryBaseInfoRequest {
	private String closeStatus; // 开关机状态
	private String weekday; // 星期设置
	private String closeBegin; // 关机开始时间
	private String closeEnd; // 关机结束时间
	public String getCloseStatus() {
		return closeStatus;
	}
	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
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
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, CloseSettingInfoRequest.class);
    }

	@Override
	public String toString() {
		return super.toString() + ", closeStatus='" + closeStatus + '\'' +
				", weekday='" + weekday + '\'' +
				", closeBegin='" + closeBegin + '\'' +
				", closeEnd='" + closeEnd + '\'';
	}
}
