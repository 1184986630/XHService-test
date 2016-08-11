package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 小号直播请求参数
 * */
public class DailInfoRequest extends QueryBaseInfoRequest {
	private String calledNo; // 被叫号码

	public String getCalledNo() {
		return calledNo;
	}

	public void setCalledNo(String calledNo) {
		this.calledNo = calledNo;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DailInfoRequest.class);
    }

	public String toString() {
		return super.toString() + ", calledNo='" + calledNo + '\'';
	}
}
