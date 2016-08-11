package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 账单明细
 * */
public class BillsInfo {
	private Integer id; // 通话ID
	private String phoneNo; // 主号号码                
	private String virtualNo; // 小号号码                
	private String calledNo; // 被/主叫号码     
	private Integer callType; // 账单类型1:直拨,2:被叫,3:发短信,4:收短信
	private Integer month; // 月份
	private String beginTime; // 开始时间，格式：yyyy-MM-dd hh24:mi:ss 
	private String endTime; // 结束时间，格式：yyyy-MM-dd hh24:mi:ss
	private Integer useCount; // 使用时长/短信数          

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCalledNo() {
		return calledNo;
	}

	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
	}

	public Integer getCallType() {
		return callType;
	}

	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, BillsInfo.class);
    }
}
