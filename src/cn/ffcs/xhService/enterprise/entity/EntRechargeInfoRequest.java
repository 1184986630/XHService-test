package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业充值请求数据
 * */
public class EntRechargeInfoRequest extends EntBaseInfoBase {

	private Long amount; // 充值金额，单位：分
	private Integer rechargeType; // 充值类型，0：正常充值；1：争议账单调整
	
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
   
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntRechargeInfoRequest.class);
    }

	public String toString() {
		return super.toString() + 
				", amount='" + (amount == null ? "" : amount) + 
				"', rechargeType='" + (rechargeType == null ? "" : rechargeType) + "'";
	}
}
