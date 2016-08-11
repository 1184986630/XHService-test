package cn.ffcs.xhService.uphold.model;

import cn.ffcs.xhService.uphold.entity.XhSmsInfoRequest;

import com.alibaba.fastjson.JSON;

public class RowCount extends XhSmsInfo {
	private String row_count;

	public String getRow_count() {
		return row_count;
	}

	public void setRow_count(String row_count) {
		this.row_count = row_count;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhSmsInfoRequest.class);
	}
}
