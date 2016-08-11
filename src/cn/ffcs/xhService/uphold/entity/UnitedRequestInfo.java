package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 发送给统一平台请求参数信息
 * */
public class UnitedRequestInfo {
	private Object request;
	
	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, UnitedRequestInfo.class);
    }
    
    public static void main(String[] args) {
		Object str = "{\"request\":{\"phoneNo\":\"13012920912\",\"virtualNo\":\"1832429992\",\"orderId\":\"8888888881\",\"salesId\":\"N00002\"}}";
		
		UnitedRequestInfo ri = (UnitedRequestInfo) RequestInfo.fromJSONString((String) str);
		System.out.println(ri.getRequest());
		OpenBusinessRequest bq = (OpenBusinessRequest) OpenBusinessRequest.fromJSONString((String) ri.getRequest());
		System.out.println(bq.getPhoneNo());
	}
}
