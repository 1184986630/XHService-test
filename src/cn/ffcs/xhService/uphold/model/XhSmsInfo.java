package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

/**
 * 短信推送信息
 * */
public class XhSmsInfo {
	private String vtype;   // 回调报告类型 11、 短信接收推送                      
	private String vcaller;   // 发送短信号码
	private String vcallee;   // 接收短信号码
	private String vcontent;   // 短信内容
	private String createtime; // 创建时间
	private String callerno; // 处理后的发送短信号码
	private String calledno; // 处理后的接收短信号码
	private String sms_id;
	
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
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getVcontent() {
		return vcontent;
	}

	public void setVcontent(String vcontent) {
		this.vcontent = vcontent;
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
	
	

	public String getSmsid() {
		return sms_id;
	}

	public void setSmsid(String smsid) {
		this.sms_id = smsid;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhSmsInfo.class);
    }
}
