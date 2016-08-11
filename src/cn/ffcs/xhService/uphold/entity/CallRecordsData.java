package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 返回客户端
 * 小号通话记录查询
 * */
public class CallRecordsData {
	private String phoneNo; // 主号
	private String virtualNo; // 小号
	private int page;//当前页数
	private int total;//总记录数
	private Object list; // 返回的数据

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

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}
	
	

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

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, CallRecordsData.class);
    }
}
