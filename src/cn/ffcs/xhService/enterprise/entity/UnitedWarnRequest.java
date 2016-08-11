package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 统一平台告警通知请求
 * */
public class UnitedWarnRequest extends EntBaseInfoBase {
	private String spName; // 企业名称
	private String businessLinkNo; // 业务联系人
	private String entLinkNo; // 企业联系人
	private Integer warningType; // 预警类型，0：到达黄线阀值；1：到达红线阀值；2：黑线阀值，即欠停。
	private String warningTime; // 预警时间
	private Long account; // 账户余额
	private Long credits; // 信用额度
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
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
	public Integer getWarningType() {
		return warningType;
	}
	public void setWarningType(Integer warningType) {
		this.warningType = warningType;
	}
	public String getWarningTime() {
		return warningTime;
	}
	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
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
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
   
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, UnitedWarnRequest.class);
    }
}
