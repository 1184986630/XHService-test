package cn.ffcs.xhService.uphold.model;

import java.util.List;

public class XhTcInfosResponse {
	private String tcTypeId;//产品id
	private String tcTypeName;//体验包
	private List<XhTcMxInfo> tcmxs;
	public String getTcTypeId() {
		return tcTypeId;
	}
	public void setTcTypeId(String tcTypeId) {
		this.tcTypeId = tcTypeId;
	}
	public String getTcTypeName() {
		return tcTypeName;
	}
	public void setTcTypeName(String tcTypeName) {
		this.tcTypeName = tcTypeName;
	}
	public List<XhTcMxInfo> getTcmxs() {
		return tcmxs;
	}
	public void setTcmxs(List<XhTcMxInfo> tcmxs) {
		this.tcmxs = tcmxs;
	}
	
	
}
