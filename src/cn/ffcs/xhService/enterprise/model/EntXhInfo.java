package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 企业小号列表
 * */
public class EntXhInfo extends EntBaseInfoBase {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String createTime; // 创建时间
	private String pkNBR; // 套餐NBR
	
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getPkNBR() {
		return pkNBR;
	}
	public void setPkNBR(String pkNBR) {
		this.pkNBR = pkNBR;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntXhInfo.class);
    }
}
