package cn.ffcs.xhService.uphold.model;

import com.alibaba.fastjson.JSON;

public class XhSmsGroupInfo {
	private String last_sent_number;
	private String last_sent_time;
	private String last_sms_content;
	private long last_time;
	private String row_count;

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

	public long getLast_time() {
		return last_time;
	}

	public void setLast_time(long last_time) {
		this.last_time = last_time;
	}

	public String getRow_count() {
		return row_count;
	}

	public void setRow_count(String row_count) {
		this.row_count = row_count;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhSmsInfo.class);
	}
}
