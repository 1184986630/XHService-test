package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 统一平台小号信息查询 基础请求数据（小号、主号、企业spid）
 * */
public class QueryBaseInfoRequest {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码

	private String spId; // 企业spid

	private String appType; // 客户端类型 0:android,1:IOS
	private String appVersion; // 应用版本 0:掌厅版,1:单独版,2:wap版
	private String channel; // 渠道
	private String version; // 版本号
	private String model; // 手机机型
	private String extValue; // 预留字段

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

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getExtValue() {
		return extValue;
	}

	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, QueryBaseInfoRequest.class);
	}

	public String toString() {
		return "phoneNo='" + phoneNo + "', virtualNo='" + virtualNo
				+ "', spId='" + (spId == null ? "" : spId) + "', appType='"
				+ (appType == null ? "" : appType) + "', appVersion='"
				+ (appVersion == null ? "" : appVersion) + "', channel='"
				+ (channel == null ? "" : channel) + "', version='"
				+ (version == null ? "" : version) + "', model='"
				+ (model == null ? "" : model) + "', extValue='"
				+ (extValue == null ? "" : extValue) + "'";
	}
}
