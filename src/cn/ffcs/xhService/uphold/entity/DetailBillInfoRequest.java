package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class DetailBillInfoRequest extends QueryBaseInfoRequest {
	private String month; // 月份
	private Integer page; // 当前页数
	private Integer size; // 每页条数
	private Integer callType; // 账单类型:1,直拨;2,被叫;3,发短信
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
		this.callType = callType;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DetailBillInfoRequest.class);
    }

	@Override
	public String toString() {
		return super.toString() + ", month='" + month + '\'' +
				", page=" + page + ", size=" + size +
				", callType='" + (callType == null ? "" : callType) + "'" ;
	}
}
