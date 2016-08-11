package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 企业订单表
 * */
public class EntOrderInfo extends EntBaseInfoBase {
	private String orderid; // 订单号
	private String phoneno; // 主号号码
	private String virtualno; // 小号号码
	private String createtime; // 创建时间
	
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
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
		return JSON.parseObject(jsonString, EntOrderInfo.class);
    }
}
