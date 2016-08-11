package cn.ffcs.xhService.uphold.model;

public class UnReadGroupInfo {
	private String last_sent_number;
	private String last_sent_time;
	private String last_sms_content;
	private int row_count;
	private String sent_timestamp;
	private long sms_receive_no;
	private String sms_receive_xh;
	private String smsid;
	public String getLast_sent_number() {
		return last_sent_number;
	}
	public void setLast_sent_number(String last_sent_number) {
		this.last_sent_number = last_sent_number;
	}
	public String getLast_sent_time() {
		return last_sent_time;
	}
	public void setLast_sent_time(String last_sent_time) {
		this.last_sent_time = last_sent_time;
	}
	public String getLast_sms_content() {
		return last_sms_content;
	}
	public void setLast_sms_content(String last_sms_content) {
		this.last_sms_content = last_sms_content;
	}
	public int getRow_count() {
		return row_count;
	}
	public void setRow_count(int row_count) {
		this.row_count = row_count;
	}
	public String getSent_timestamp() {
		return sent_timestamp;
	}
	public void setSent_timestamp(String sent_timestamp) {
		this.sent_timestamp = sent_timestamp;
	}
	public long getSms_receive_no() {
		return sms_receive_no;
	}
	public void setSms_receive_no(long sms_receive_no) {
		this.sms_receive_no = sms_receive_no;
	}
	public String getSms_receive_xh() {
		return sms_receive_xh;
	}
	public void setSms_receive_xh(String sms_receive_xh) {
		this.sms_receive_xh = sms_receive_xh;
	}
	public String getSmsid() {
		return smsid;
	}
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}

}
