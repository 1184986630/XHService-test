package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业信息设置请求数据
 * */
public class EntInfoRequest extends EntBaseInfoBase {
	private String spName; // 企业名称
	private Integer payType; // 付费类型，0:预付费，1：后付费
	private Long credits; // 信用额度，单位：元
	private Long redThreshold; // 红线阀值，用于预警，余额+信用额度剩余X元进行预警，单位：元
	private Long yellowThreshold; // 黄线阀值，用于预警，余额+信用额度剩余X元进行预警，单位：元
	private Integer dialDiscount; // 拨打折扣率。80%传80
	private Integer answerDiscount; // 接听折扣率。80%传80
	private Integer smsDiscount; // 短信折扣率。80%传80
	private Integer usedFeeDiscount; // 号码占用费折扣率。80%传80
	private String businessLinkNo; // 企业联系电话
	private String entLinkNo; // 企业联系电话

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Long getCredits() {
		return credits;
	}

	public void setCredits(Long credits) {
		this.credits = credits;
	}

	public Long getRedThreshold() {
		return redThreshold;
	}

	public void setRedThreshold(Long redThreshold) {
		this.redThreshold = redThreshold;
	}

	public Long getYellowThreshold() {
		return yellowThreshold;
	}

	public void setYellowThreshold(Long yellowThreshold) {
		this.yellowThreshold = yellowThreshold;
	}

	public Integer getDialDiscount() {
		return dialDiscount;
	}

	public void setDialDiscount(Integer dialDiscount) {
		this.dialDiscount = dialDiscount;
	}
	
	public Integer getAnswerDiscount() {
		return answerDiscount;
	}

	public void setAnswerDiscount(Integer answerDiscount) {
		this.answerDiscount = answerDiscount;
	}

	public Integer getSmsDiscount() {
		return smsDiscount;
	}

	public void setSmsDiscount(Integer smsDiscount) {
		this.smsDiscount = smsDiscount;
	}

	public Integer getUsedFeeDiscount() {
		return usedFeeDiscount;
	}

	public void setUsedFeeDiscount(Integer usedFeeDiscount) {
		this.usedFeeDiscount = usedFeeDiscount;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
	
	public String getBusinessLinkNo() {
		return businessLinkNo;
	}

	public void setBusinessLinkNo(String businessLinkNo) {
		this.businessLinkNo = businessLinkNo;
	}

	public String getEntLinkNo() {
		return entLinkNo;
	}

	public void setEntLinkNo(String entLinkNo) {
		this.entLinkNo = entLinkNo;
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntInfoRequest.class);
    }

	public String toString() {
		return super.toString() + 
				", spName='" + (spName == null ? "" : spName) + 
				"', payType='" + (payType == null ? "" : payType) + 
				"', credits='" + (credits == null ? "" : credits) + 
				"', redThreshold='" + (redThreshold == null ? "" : redThreshold) + 
				"', yellowThreshold='" + (yellowThreshold == null ? "" : yellowThreshold) + 
				"', dialDiscount='" + (dialDiscount == null ? "" : dialDiscount) + 
				"', answerDiscount='" + (answerDiscount == null ? "" : answerDiscount) + 
				"', smsDiscount='" + (smsDiscount == null ? "" : smsDiscount) + 
				"', usedFeeDiscount='" + (usedFeeDiscount == null ? "" : usedFeeDiscount) + 
				"', businessLinkNo='" + (businessLinkNo == null ? "" : businessLinkNo) + 
				"', entLinkNo='" + (entLinkNo == null ? "" : entLinkNo) + "'";
	}
}
