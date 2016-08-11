package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;

/**
 * 小号通话记录查询请求
 * */
public class CallRecordsRequest extends QueryBaseInfoRequest {
	private String lastTime; // 最后更新时间，格式yyyy-MM-dd hh24:mi:ss
	private String ids; // 通话id，多个英文逗号分隔
	private Integer page; // 当前页数
	private Integer size; // 每页条数
	private String callType; // 删除记录类型
	
	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, CallRecordsRequest.class);
    }
    
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
	
	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String toString() {
		return super.toString() + ", lastTime='"
				+ (lastTime == null ? "" : lastTime) + "', ids='"
				+ (ids == null ? "" : ids) + "', page='"
				+ (page == null ? "" : page) + "', size='"
				+ (size == null ? "" : size) + "', calltype='"
				+ (callType == null ? "" : callType) + "'";
	}
}
