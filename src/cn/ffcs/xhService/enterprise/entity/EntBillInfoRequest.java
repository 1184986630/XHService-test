package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 企业账单查询请求数据
 * */
public class EntBillInfoRequest extends EntBaseInfoBase {
	private Integer page; // 当前页数
	private Integer size; // 每页条数
	private Integer type; // 查询类型：1，按月；2，按年
	private String month; // 查询月份，格式yyyy-mm
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntBillInfoRequest.class);
    }

	public String toString() {
		return super.toString() + 
				", month='" + (month == null ? "" : month) + 
				"', type='" + (type == null ? "" : type) + 
				"', page='" + (page == null ? "" : page) + 
				"', size='" + (size == null ? "" : size) + "'";
	}
}
