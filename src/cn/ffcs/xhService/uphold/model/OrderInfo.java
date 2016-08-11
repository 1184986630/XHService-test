package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 通信账单信息
 * */
public class OrderInfo {
	private String orderid; // 订单号
	private String salesid; // 销售品id
	private String phoneno; // 主号号码
	private String virtualno; // 小号号码
	private String createtime; // 创建时间
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getSalesid() {
		return salesid;
	}

	public void setSalesid(String salesid) {
		this.salesid = salesid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getVirtualno() {
		return virtualno;
	}

	public void setVirtualno(String virtualno) {
		this.virtualno = virtualno;
	}
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, OrderInfo.class);
    }
}
