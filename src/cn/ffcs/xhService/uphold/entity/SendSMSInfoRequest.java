package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

public class SendSMSInfoRequest extends QueryBaseInfoRequest {
	private String calledNo; // 被叫号码，如果是多个被叫号码，以,隔开
	private String content; // 短信内容
	public String getCalledNo() {
		return calledNo;
	}
	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, SendSMSInfoRequest.class);
    }

	@Override
	public String toString() {
		return super.toString() + ", calledNo='" + calledNo + '\'' +
				", content='" + content + '\'';
	}
}
