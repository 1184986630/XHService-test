package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 页面信息
 * */
public class EntPageInfoData extends EntBaseInfoBase {
	private int page; // 当前页数                    
	private int total; // 总记录数
	private Object list; // 返回的数据
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
   
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntPageInfoData.class);
    }
}
