package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 月账单信息
 * */
public class BillsSettleInfo {
	private String phoneNo; // 主号号码                
	private String virtualNo; // 小号号码                
	private String month; // 月份，格式：yyyy-MM     
	private Long dailTotal; // 月累计呼出时长，单位：秒
	private Long dialTimes; // 月累计呼出次数          
	private Long answerTotal; // 月累计接听时长，单位：秒
	private Long answerTimes; // 月累计接听次数          
	private Long SMSTotal; // 月累计短信数            

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVirtualNo() {
		return virtualNo;
	}

	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getDailTotal() {
		return dailTotal;
	}

	public void setDailTotal(Long dailTotal) {
		this.dailTotal = dailTotal;
	}

	public Long getDialTimes() {
		return dialTimes;
	}

	public void setDialTimes(Long dialTimes) {
		this.dialTimes = dialTimes;
	}

	public Long getAnswerTotal() {
		return answerTotal;
	}

	public void setAnswerTotal(Long answerTotal) {
		this.answerTotal = answerTotal;
	}

	public Long getAnswerTimes() {
		return answerTimes;
	}

	public void setAnswerTimes(Long answerTimes) {
		this.answerTimes = answerTimes;
	}

	@JSONField(name="SMSTotal")
	public Long getSMSTotal() {
		return SMSTotal;
	}

	public void setSMSTotal(Long SMSTotal) {
		this.SMSTotal = SMSTotal;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, BillsSettleInfo.class);
    }
}
