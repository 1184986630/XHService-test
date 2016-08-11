package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

public class XhSmsInfoResponse {
	private String sent_date;//发送时间
	private String sent_number;//发送电话号码
	private String sms_receive_no;//接收电话主号
	private String sms_receive_xh;//接收电话小号
	private long sent_timestamp;
	private String sms_content;
	private String	smsid;
	public String getSent_date() {
		return sent_date;
	}
	public void setSent_date(String sent_date) {
		this.sent_date = sent_date;
	}
	public String getSent_number() {
		return sent_number;
	}
	public void setSent_number(String sent_number) {
		this.sent_number = sent_number;
	}
	public long getSent_timestamp() {
		return sent_timestamp;
	}
	public void setSent_timestamp(long sent_timestamp) {
		this.sent_timestamp = sent_timestamp;
	}
	public String getSms_content() {
		return sms_content;
	}
	public void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}
	public String getSmsid() {
		return smsid;
	}
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
	
	
	public String getSms_receive_no() {
		return sms_receive_no;
	}
	public void setSms_receive_no(String sms_receive_no) {
		this.sms_receive_no = sms_receive_no;
	}
	public String getSms_receive_xh() {
		return sms_receive_xh;
	}
	public void setSms_receive_xh(String sms_receive_xh) {
		this.sms_receive_xh = sms_receive_xh;
	}
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhSmsInfo.class);
    }
}
