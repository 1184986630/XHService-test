package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 订单失败表
 */
public class XhOrderFail {

	private String orderId;			// 订单号
	private String orderStatus;		// 订单状态
	private String completeStatus;	// 报竣状态
	
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
	public String getCompleteStatus() {
		return completeStatus;
	}
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhOrderFail.class);
    }
}
