package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 底层小号订单报竣状态表
 */
public class DealXhDcOrderStatus {

	private String orderId;			// 小号订单号
	private String orderStatus;		// 订单报竣状态
	private String updataTime;		// 订单状态更新时间
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getUpdataTime() {
		return updataTime;
	}
	public void setUpdataTime(String updataTime) {
		this.updataTime = updataTime;
	}
	  
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealXhDcOrderStatus.class);
    }
}
