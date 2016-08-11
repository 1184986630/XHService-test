package cn.ffcs.xhService.uphold.model;

public class PhoneHdInfo {
	private String phoneNo; 		// 号段
	private String routingCode; 	// 路由码
	private String createtime; 		// 创建时间
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getRoutingCode() {
		return routingCode;
	}
	public void setRoutingCode(String routingCode) {
		this.routingCode = routingCode;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
