package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 小号套餐续订请求信息
 * */
public class RenewalInfoRequest extends QueryBaseInfoRequest {
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
		return JSON.parseObject(jsonString, RenewalInfoRequest.class);
    }

	public String toString() {
		return super.toString() + ", orderId='" + orderId + '\'' +
				", salesId='" + salesId + '\'';
	}
}
