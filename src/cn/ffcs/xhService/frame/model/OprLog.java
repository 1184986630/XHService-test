package cn.ffcs.xhService.frame.model;

import com.alibaba.fastjson.JSON;

public class OprLog {
	private int logid;
	private String opuserid;
	private Integer usertype;
	private String optype;
	private String retcode;
	private String operation;
	private String appType; // 客户端类型 0:android,1:IOS
	private String appVersion; // 应用版本 0:掌厅版,1:单独版,2:wap版
	private String channel; // 渠道
	private String version; // 版本号
	private String model; // 手机机型
	private String extValue; // 预留字段
	private String spid; // 企业ID
	private String reachtime; // 请求到达时间
	private String optime; // 操作时间


	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	public String getOpuserid() {
		return opuserid;
	}
	public void setOpuserid(String opuserid) {
		this.opuserid = opuserid;
	}
	public String getOptype() {
		return optype;
	}
	public void setOptype(String optype) {
		this.optype = optype;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
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
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getReachtime() {
		return reachtime;
	}
	public void setReachtime(String reachtime) {
		this.reachtime = reachtime;
	}
	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, OprLog.class);
    }
}
