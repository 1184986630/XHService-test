package cn.ffcs.xhService.uphold.model;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 套餐信息
 * */
public class PackageInfo {
	private String salesId; // 销售品ID
	private String salesName; // 套餐名称
	private Integer salesType; // 套餐类型 1:语音包（含短信）,2:短信包
	private Float fee; // 资费
	private String salesDesc; // 套餐描述
	private Integer useDays; // 套餐有效天数
	private Integer SMSAmount; // 短信条数
	private String createTime; // 创建时间
	
	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public String getSalesDesc() {
		return salesDesc;
	}

	public void setSalesDesc(String salesDesc) {
		this.salesDesc = salesDesc;
	}

	public Integer getUseDays() {
		return useDays;
	}

	public void setUseDays(Integer useDays) {
		this.useDays = useDays;
	}

	public Integer getSMSAmount() {
		return SMSAmount;
	}

	public void setSMSAmount(Integer sMSAmount) {
		SMSAmount = sMSAmount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, PackageInfo.class);
    }
    
    public static List<PackageInfo> fromJSONString2Array(String jsonString) {
		return JSON.parseArray(jsonString, PackageInfo.class);
    }
}
