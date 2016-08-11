package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 企业详单信息
 * */
public class EntDetailBillInfo extends EntBaseInfoBase {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String calledNo; // 被/主叫号码
	private int callType; // 账单类型 1:呼叫,2:被叫,3:发短信,4:号码占用费
	private String beginTime; // 开始时间，格式：yyyy-MM-dd hh24:mi:ss
	private String endTime; // 结束时间
	private int useCount; // 处理后的使用时长/短信数
	private Long fee; // 通信费用
	private int vuseCount; // 使用时长/短信数

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

	public String getCalledNo() {
		return calledNo;
	}

	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
	}

	public int getCallType() {
		return callType;
	}

	public void setCallType(int callType) {
		this.callType = callType;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getVuseCount() {
		return vuseCount;
	}

	public void setVuseCount(int vuseCount) {
		this.vuseCount = vuseCount;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntDetailBillInfo.class);
    }
}
