package cn.ffcs.xhService.uphold.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 过期销号返回的错误信息
 * */
public class ExpireCloseErrorInfo {
	private String phone_no;
	private String code;
	private String errorDescription;

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String toString() {
		return "phone_no='" + phone_no == null ? "" : phone_no + "', code='"
				+ code == null ? "" : code + "', errorDescription='"
				+ errorDescription == null ? "" : errorDescription + "'";
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, ExpireCloseErrorInfo.class);
	}
	
	public static List<ExpireCloseErrorInfo> fromJSONString2Array(String jsonString) {
		return JSON.parseArray(jsonString, ExpireCloseErrorInfo.class);
    }
}
