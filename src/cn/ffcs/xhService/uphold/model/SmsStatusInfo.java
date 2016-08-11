package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * HTTP短信发送状态推送信息
 * */
public class SmsStatusInfo {
	private String vsessionid;   // 会话ID                      
	private String vtype     ;   // 回调报告类型 12、 短信接收推送
	private String vcaller;   // 发送短信号码     
	private String vcallee;   // 接收短信号码
	private String vstatus; // 短信状态（1：发送成功 2：提交成功 3：提交失败 4:发送失败）
	private String vlaststatus; // 发送失败时，对应的具体错误码
	private String callerno; // 处理后的发送短信号码
	private String calledno; // 处理后的接收短信号码
	
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

	public String getVcaller() {
		return vcaller;
	}

	public void setVcaller(String vcaller) {
		this.vcaller = vcaller;
	}

	public String getVcallee() {
		return vcallee;
	}

	public void setVcallee(String vcallee) {
		this.vcallee = vcallee;
	}

	public String getVstatus() {
		return vstatus;
	}

	public void setVstatus(String vstatus) {
		this.vstatus = vstatus;
	}

	public String getVlaststatus() {
		return vlaststatus;
	}

	public void setVlaststatus(String vlaststatus) {
		this.vlaststatus = vlaststatus;
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
		return JSON.parseObject(jsonString, SmsStatusInfo.class);
    }
}
