package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 小号短信提醒推送
 * */
public class XhSmsInfoRequest {
	private String phoneNo; // 主号号码
	private String virtualNo; // 小号号码
	private String sendNo; // 发送号码
	private String content; // 短信内容
	private String createTime; // 接收时间
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getVirtualNo() {
		return virtualNo;
	}

	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
	}

	public String getSendNo() {
		return sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhSmsInfoRequest.class);
    }
}
