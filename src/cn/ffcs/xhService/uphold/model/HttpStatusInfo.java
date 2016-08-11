package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * HTTP触发类能力呼叫状态回调信息
 * */
public class HttpStatusInfo {
	private String vType;
	private String vServiceType;
	private String vSessionsId;
	private String vCallerNbr;
	private String vCalleeNbr;
	private String vCallState;
	private String vIsincomingcall;
	private String vIstransfer;
	private String vStateTime;
	private String vReason;
	private String callerno;
	private String calledno;
	private String createtime;
	
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public String getvServiceType() {
		return vServiceType;
	}
	public void setvServiceType(String vServiceType) {
		this.vServiceType = vServiceType;
	}
	public String getvSessionsId() {
		return vSessionsId;
	}
	public void setvSessionsId(String vSessionsId) {
		this.vSessionsId = vSessionsId;
	}
	public String getvCallerNbr() {
		return vCallerNbr;
	}
	public void setvCallerNbr(String vCallerNbr) {
		this.vCallerNbr = vCallerNbr;
	}
	public String getvCalleeNbr() {
		return vCalleeNbr;
	}
	public void setvCalleeNbr(String vCalleeNbr) {
		this.vCalleeNbr = vCalleeNbr;
	}
	public String getvCallState() {
		return vCallState;
	}
	public void setvCallState(String vCallState) {
		this.vCallState = vCallState;
	}
	public String getvIsincomingcall() {
		return vIsincomingcall;
	}
	public void setvIsincomingcall(String vIsincomingcall) {
		this.vIsincomingcall = vIsincomingcall;
	}
	public String getvIstransfer() {
		return vIstransfer;
	}
	public void setvIstransfer(String vIstransfer) {
		this.vIstransfer = vIstransfer;
	}
	public String getvStateTime() {
		return vStateTime;
	}
	public void setvStateTime(String vStateTime) {
		this.vStateTime = vStateTime;
	}
	public String getvReason() {
		return vReason;
	}
	public void setvReason(String vReason) {
		this.vReason = vReason;
	}
	public String getCallerno() {
		return callerno;
	}
	public void setCallerno(String callerno) {
		this.callerno = callerno;
	}
	public String getCalledno() {
		return calledno;
	}
	public void setCalledno(String calledno) {
		this.calledno = calledno;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, HttpStatusInfo.class);
    }
}
