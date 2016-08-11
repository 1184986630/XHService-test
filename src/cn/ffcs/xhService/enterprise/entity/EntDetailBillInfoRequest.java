package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业详单查询请求数据
 * */
public class EntDetailBillInfoRequest extends EntBaseInfoBase {

	private String phoneNo; // 主号
	private String virtualNo; // 小号
	private String callType; // 账单类型
	private String month; // 查询月份
	private Integer page; // 当前页数
	private Integer size; // 每页条数
	private Integer orderType; // 排序类型，默认按时间倒序
	
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
	
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
   
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntDetailBillInfoRequest.class);
    }

	public String toString() {
		return super.toString() + 
				", phoneNo='" + (phoneNo == null ? "" : phoneNo) + 
				"', virtualNo='" + (virtualNo == null ? "" : virtualNo) + 
				"', callType='" + (callType == null ? "" : callType) + 
				"', month='" + (month == null ? "" : month) + 
				"', page='" + (page == null ? "" : page) + 
				"', size='" + (size == null ? "" : size) + 
				"', orderType='" + (orderType == null ? "" : orderType) + "'";
	}
}
