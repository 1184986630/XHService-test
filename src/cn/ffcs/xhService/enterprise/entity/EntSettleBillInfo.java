package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

public class EntSettleBillInfo extends EntBaseInfoBase {
	private String createTime     ; // 账单月份
	private Long callerTotal    ; // 拨打时长
	private Long callerPrice    ; // 拨打单价
	private Long dialDiscount   ; // 拨打折扣
	private Long callerFee      ; // 拨打费用
	private Long calledTotal    ; // 接听时长
	private Long calledPrice    ; // 接听单价
	private Long calledDiscount ; // 接听折扣
	private Long calledFee      ; // 接听费用
	private Long smsTotal       ; // 短信条数
	private Long smsPrice       ; // 短信单价
	private Long smsDiscount    ; // 短信折扣
	private Long smsFee         ; // 短信费用
	private Long usedPrice      ; // 功能费价格
	private Long usedFeeDiscount; // 功能费折扣
	private Long usedFee        ; // 功能费用
	private Long totalFee       ; // 总计
	
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

	public Long getCallerPrice() {
		return callerPrice;
	}

	public void setCallerPrice(Long callerPrice) {
		this.callerPrice = callerPrice;
	}

	public Long getDialDiscount() {
		return dialDiscount;
	}

	public void setDialDiscount(Long dialDiscount) {
		this.dialDiscount = dialDiscount;
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

	public Long getCalledPrice() {
		return calledPrice;
	}

	public void setCalledPrice(Long calledPrice) {
		this.calledPrice = calledPrice;
	}

	public Long getCalledDiscount() {
		return calledDiscount;
	}

	public void setCalledDiscount(Long calledDiscount) {
		this.calledDiscount = calledDiscount;
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

	public Long getSmsPrice() {
		return smsPrice;
	}

	public void setSmsPrice(Long smsPrice) {
		this.smsPrice = smsPrice;
	}

	public Long getSmsDiscount() {
		return smsDiscount;
	}

	public void setSmsDiscount(Long smsDiscount) {
		this.smsDiscount = smsDiscount;
	}

	public Long getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(Long smsFee) {
		this.smsFee = smsFee;
	}

	public Long getUsedPrice() {
		return usedPrice;
	}

	public void setUsedPrice(Long usedPrice) {
		this.usedPrice = usedPrice;
	}

	public Long getUsedFeeDiscount() {
		return usedFeeDiscount;
	}

	public void setUsedFeeDiscount(Long usedFeeDiscount) {
		this.usedFeeDiscount = usedFeeDiscount;
	}

	public Long getUsedFee() {
		return usedFee;
	}

	public void setUsedFee(Long usedFee) {
		this.usedFee = usedFee;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntSettleBillInfo.class);
    }
}
