package cn.ffcs.xhService.enterprise.model;

import com.alibaba.fastjson.JSON;

/**
 * 企业账单信息
 * */
public class EntBillUrlInfo {
	private String createtime; // 创建时间
	private String url; // 文件URL地址
	private Integer flag; // 处理标识：0，未处理；1，已处理。
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntBillUrlInfo.class);
    }
}
