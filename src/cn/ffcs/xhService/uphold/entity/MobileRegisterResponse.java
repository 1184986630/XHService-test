package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 计费号码注册
 * */
public class MobileRegisterResponse extends Response {
	private String ims; // Ims号码
	private String Key; // 密钥(后续有关Ims号码的操作都需要该字段，需保)
	private String allPoint; // 可用点数(注册成功后平台赠送的点数)
	private String effectDate; // 使用有效期(格式：yyyy-MM-dd HH:mm:ss)
	
	public String getIms() {
		return ims;
	}
	public void setIms(String ims) {
		this.ims = ims;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public String getAllPoint() {
		return allPoint;
	}
	public void setAllPoint(String allPoint) {
		this.allPoint = allPoint;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, MobileRegisterResponse.class);
    }
}
