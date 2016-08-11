package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 企业信息
 * */
public class EntInfo extends EntBaseInfoBase {
	private String spName; // 企业名称                           
	private Integer payType; // 付费类型，0:预付费，1：后付费      
	private Long account; // 账户余额                           
	private Long credits; // 信用额度                           
	private String createTime; // 创建时间                           
	private Integer status; // 状态，0：正常，1：欠费停机；3：冻结
	private Integer pushStatus; // 推送状态位，按照位操作存储         
	private Long redThreshold; // 红线阀值                           
	private Long yellowThreshold; // 黄线阀值                           
	private Long caasThreshold; // caas阀值                           
	private Integer dialDiscount; // 拨打折扣率
	private Integer answerDiscount; // 接听折扣率
	private Integer smsDiscount; // 短信折扣率
	private Integer usedFeeDiscount; // 号码占用费折扣率
	private String businessLinkno; // 企业联系电话
	private String entLinkno; // 企业联系电话
	private String pwd; // 登录密码
	
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

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public Long getCredits() {
		return credits;
	}

	public void setCredits(Long credits) {
		this.credits = credits;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
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

	public Long getCaasThreshold() {
		return caasThreshold;
	}

	public void setCaasThreshold(Long caasThreshold) {
		this.caasThreshold = caasThreshold;
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

	public String getBusinessLinkno() {
		return businessLinkno;
	}

	public void setBusinessLinkno(String businessLinkno) {
		this.businessLinkno = businessLinkno;
	}

	public String getEntLinkno() {
		return entLinkno;
	}

	public void setEntLinkno(String entLinkno) {
		this.entLinkno = entLinkno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntInfo.class);
    }
}
