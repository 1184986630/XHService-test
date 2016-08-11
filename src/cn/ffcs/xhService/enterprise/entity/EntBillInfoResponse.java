package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业账单信息
 * */
public class EntBillInfoResponse {
	private String createTime; // 账单日期，格式：yyyy-MM-dd       
	private Long callerTotal; // 累计拨打使用时长，单位：分钟
	private Long callerFee; // 累计拨打费用，单位：厘      
	private Long calledTotal; // 累计接听使用时长，单位：分钟
	private Long calledFee; // 累计接听费用，单位：厘  
	private Long smsTotal; // 套餐外累计短信使用条数            
	private Long smsFee; // 套餐外累计短信费用，单位：厘
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCallerTotal() {
		return callerTotal;
	}

	public void setCallerTotal(Long callerTotal) {
		this.callerTotal = callerTotal;
	}

	public Long getCallerFee() {
		return callerFee;
	}

	public void setCallerFee(Long callerFee) {
		this.callerFee = callerFee;
	}

	public Long getCalledTotal() {
		return calledTotal;
	}

	public void setCalledTotal(Long calledTotal) {
		this.calledTotal = calledTotal;
	}

	public Long getCalledFee() {
		return calledFee;
	}

	public void setCalledFee(Long calledFee) {
		this.calledFee = calledFee;
	}

	public Long getSmsTotal() {
		return smsTotal;
	}

	public void setSmsTotal(Long smsTotal) {
		this.smsTotal = smsTotal;
	}

	public Long getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(Long smsFee) {
		this.smsFee = smsFee;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntBillInfoResponse.class);
    }
}
