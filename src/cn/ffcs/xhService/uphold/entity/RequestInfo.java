package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 请求参数信息
 * */
public class RequestInfo {
	private String request = "";
	
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, RequestInfo.class);
    }
    
    public static void main(String[] args) {
		String str = "{\"request\":{\"phoneNo\":\"13012920912\",\"virtualNo\":\"1832429992\",\"orderId\":\"8888888881\",\"salesId\":\"N00002\"}}";
//    	String str = "{'request': " +
//    			"[{	" +
//    			"'salesId': 'N00001',	'salesName': '体验套餐',	'salesType': 1,	'fee': 3,	'desc': '3元体验套餐，在7天内使用小号功能',	'useDays': 7,	'SMSAmount': 10	}, " +
//    			"{	'salesId': 'N00002',	'salesName': '体验套餐',	'salesType': 1,	'fee': 10,	'desc': '10元包月套餐，在30天内使用小号功能',	'useDays': 30,	'SMSAmount': 10}, " +
//    			"{	'salesId': 'N00003',	'salesName': '短信套餐',	'salesType': 2,	'fee': 5,	'desc': '5元短信套餐，含50条小号短信',	'useDays': -1,	'SMSAmount': 50}]}";
//    	String str = "{'request': " +
//    			"{	'salesId': 'N00003',	'salesName': '短信套餐',	'salesType': 2,	'fee': 5,	'desc': '5元短信套餐，含50条小号短信',	'useDays': -1,	'SMSAmount': 50}}";
		
    	
		RequestInfo ri = (RequestInfo) RequestInfo.fromJSONString(str);
		System.out.println(ri.getRequest());
		OpenBusinessRequest bq = (OpenBusinessRequest) OpenBusinessRequest.fromJSONString(ri.getRequest());
		System.out.println(bq.getPhoneNo());
//		List<PackageInfo> list = PackageInfo.fromJSONString2Array(ri.getRequest());
		//Object obj= PackageInfo.fromJSONString(ri.getRequest());
//		System.out.println(list.get(0).getUseDays());
	}
}
