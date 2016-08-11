package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 充值记录表
 * */
public class EntRechargeInfo extends EntBaseInfoBase {
	private Long amount; // 充值金额，单位：分
	private Integer rechargeType; // 充值类型，0：正常充值；1：争议账单调整
	private String createTime; // 创建时间
	
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntRechargeInfo.class);
    }
}
