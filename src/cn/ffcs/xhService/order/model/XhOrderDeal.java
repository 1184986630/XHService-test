package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 订单处理表
 */
public class XhOrderDeal {

	private String orderId;				// 订单号
	private String orderStatus;			// 订单状态
	private String completeStatus;		// 报竣状态
	private String payStatus;			// 支付状态
	private String orderCreatetime;		// 订单生成时间
	private String orderOvertime;		// 订单超时时间
	private String status;				// 处理状态
	
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
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getOrderCreatetime() {
		return orderCreatetime;
	}
	public void setOrderCreatetime(String orderCreatetime) {
		this.orderCreatetime = orderCreatetime;
	}
	public String getOrderOvertime() {
		return orderOvertime;
	}
	public void setOrderOvertime(String orderOvertime) {
		this.orderOvertime = orderOvertime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhOrderDeal.class);
    }
}
