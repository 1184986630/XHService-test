package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 小号通话记录
 * */
public class CallRecordsInfo {
	private Integer id; // 主键
	private String calledNo; // 被/主叫号码
	private String area; // 号码归属地，格式：归属省份,归属地市
	private Integer callType; // 账单类型1:直拨,2:被叫,3:发短信,4:收短信
	private String beginTime; // 开始时间，格式：yyyy-MM-dd hh24:mi:ss 
	private Integer useCount; // 使用时长/短信数          

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, CallRecordsInfo.class);
    }
}
