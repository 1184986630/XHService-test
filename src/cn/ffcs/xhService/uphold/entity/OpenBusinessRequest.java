package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 统一平台业务开通 请求数据
 * */
public class OpenBusinessRequest extends QueryBaseInfoRequest {
	private String orderId; // 订单号
	private String salesId; // 销售品ID

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, OpenBusinessRequest.class);
	}

	public String toString() {
		return super.toString() + ", orderId='" + (orderId==null?"":orderId) + 
				"', salesId='" + (salesId==null?"":salesId) + "'";
	}

}
