package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 修改OCR识别的身份证姓名请求
 * */
public class UpNameRequest{
	private String id; // 身份证id
	private String name; // 客户修改过的名称
	private String orderid; // 订单号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, UpNameRequest.class);
    }
	
	
}
