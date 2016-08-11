package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 通信账单信息
 * */
public class BillInfoRequest {
	private Integer billtype; // 账单类型（1：通话账单 2：录音账单）
	private String sessionid; // 唯一标识
	private String chargenbr; // 计费号码
	private String displaynbr; // 显示号码
	private String callernbr; // 主叫号码
	private String callednbr; // 被叫号码
	private String starttime; // 开始时间
	private String endtime; // 结束时间
	private String starttimecalled; // 被叫开始时间，在点击拨号账单中推送。（格式:yyyyMMddhhmmss）
	private String billsubtype; // 话单类型
	private String duration; // 通话时长（单位秒）,当BillSubtype为107短信账单时,表示短信条数
	private String points; // 消费点数
	
	public Integer getBilltype() {
		return billtype;
	}

	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	public String getStarttimecalled() {
		return starttimecalled;
	}

	public void setStarttimecalled(String starttimecalled) {
		this.starttimecalled = starttimecalled;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getChargenbr() {
		return chargenbr;
	}

	public void setChargenbr(String chargenbr) {
		this.chargenbr = chargenbr;
	}

	public String getDisplaynbr() {
		return displaynbr;
	}

	public void setDisplaynbr(String displaynbr) {
		this.displaynbr = displaynbr;
	}

	public String getCallernbr() {
		return callernbr;
	}

	public void setCallernbr(String callernbr) {
		this.callernbr = callernbr;
	}

	public String getCallednbr() {
		return callednbr;
	}

	public void setCallednbr(String callednbr) {
		this.callednbr = callednbr;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getBillsubtype() {
		return billsubtype;
	}

	public void setBillsubtype(String billsubtype) {
		this.billsubtype = billsubtype;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, BillInfoRequest.class);
    }
}
