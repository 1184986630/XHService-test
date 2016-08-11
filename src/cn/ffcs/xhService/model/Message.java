package cn.ffcs.xhService.model;

import com.alibaba.fastjson.JSON;

public class Message {
    private String result;
    private String detail;

    public Message() {
        super();
    }
    public Message(String result, String detail) {
        super();
        this.result = result;
        this.detail = detail;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, Message.class);
    }
    public static void main(String[] args) {
    	//Message m = new Message();
    	String t = "{\"detail\":\"tst\",\"result\":\"00\",\"other\":\"1\"}";
    	Message m = (Message) Message.fromJSONString(t);
    	
//    	m.setDetail("tst");
//    	m.setResult("00");
    	System.out.println(m.toJSONString());
	}
}
