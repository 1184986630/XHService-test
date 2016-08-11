package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * Mq重推信息
 * */
public class MqRepushInfo {
	private String host; // 推送主机
	private int port; // 推送端口
	private String addr; // 推送地址
	private Object pushinfo; // 推送信息，json格式
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Object getPushinfo() {
		return pushinfo;
	}

	public void setPushinfo(Object pushinfo) {
		this.pushinfo = pushinfo;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, MqRepushInfo.class);
    }
}
