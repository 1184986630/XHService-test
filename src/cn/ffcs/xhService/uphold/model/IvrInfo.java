package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 主叫号码推送信息
 * */
public class IvrInfo {
	private String vsessionid;   // 会话ID                      
	private String vtype     ;   // 回调报告类型 4、 IVR主叫推送
	private String vcallernbr;   // 主叫号码                    
	private String vcallednbr;   // 被叫IVR触发号码
	private String state; // 状态： 1：正常转接  2：关机  3：关机留言
	private String createtime; // 创建时间
	private String callerno; // 处理后的主叫号码
	private String calledno; // 处理后的被叫号码
	
	public String getVsessionid() {
		return vsessionid;
	}

	public void setVsessionid(String vsessionid) {
		this.vsessionid = vsessionid;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getVcallernbr() {
		return vcallernbr;
	}

	public void setVcallernbr(String vcallernbr) {
		this.vcallernbr = vcallernbr;
	}

	public String getVcallednbr() {
		return vcallednbr;
	}

	public void setVcallednbr(String vcallednbr) {
		this.vcallednbr = vcallednbr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, IvrInfo.class);
    }
}
