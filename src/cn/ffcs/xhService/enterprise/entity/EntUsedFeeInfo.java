package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业号码占用费信息
 * */
public class EntUsedFeeInfo extends EntBaseInfoBase {
	private String phoneNo; // 主号
	private String virtualNo; // 小号
	private Long usedFee; // 企业总计号码占用费

	public Long getUsedFee() {
		return usedFee;
	}

	public void setUsedFee(Long usedFee) {
		this.usedFee = usedFee;
	}
	
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

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntUsedFeeInfo.class);
    }
}
