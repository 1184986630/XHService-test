package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 订单扩展信息表
 */
public class DealOrderExtendProperty {

	private String orderId;			// 订单号
	private String extendCode;		// 扩展属性编码
	private String extendValue;		// 扩展属性值
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getExtendCode() {
		return extendCode;
	}
	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	public String getExtendValue() {
		return extendValue;
	}
	public void setExtendValue(String extendValue) {
		this.extendValue = extendValue;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealOrderExtendProperty.class);
    }
}
