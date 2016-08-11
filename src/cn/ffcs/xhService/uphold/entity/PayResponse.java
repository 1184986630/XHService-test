package cn.ffcs.xhService.uphold.entity;

import cn.ffcs.xhService.model.EResponse;

import com.alibaba.fastjson.JSON;

public class PayResponse {
	private int code;
	private Object dataObject = "";
	private String errorDescription;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EResponse.class);
	}
}
