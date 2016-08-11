package cn.ffcs.xhService.enterprise.entity;

import com.alibaba.fastjson.JSON;

/**
 * 停复机、冻结请求数据
 * */
public class EntStopRecoveryInfoRequest extends EntBaseInfoBase {

	private Integer opType; // 操作类型，1：停机；2：复机；3：冻结；4：解除冻结
	private String opDesc; // 操作说明
	
	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
	public String getOpDesc() {
		return opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
   
	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntStopRecoveryInfoRequest.class);
    }

	public String toString() {
		return super.toString() + 
				", opType='" + (opType == null ? "" : opType) +
				"', opDesc='" + (opDesc == null ? "" : opDesc) + "'";
	}
}
