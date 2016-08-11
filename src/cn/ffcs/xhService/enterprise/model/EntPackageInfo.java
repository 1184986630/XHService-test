package cn.ffcs.xhService.enterprise.model;

import com.alibaba.fastjson.JSON;

/**
 * 企业套餐信息
 * */
public class EntPackageInfo {
	private String pkNBR; // 套餐id                
	private String pkName; // 套餐名称              
	private Integer pkType; // 套餐类型              
	private String pkDesc; // 套餐描述              
	private Long callerFee; // 拨打资费，单位：厘    
	private Long smsFee; // 短信资费，单位：厘    
	private Long calledFee; // 接听资费，单位：厘    
	private Long usedFee; // 号码占用资费，单位：厘
	private String createTime; // 创建时间              
	private Long usedays; // 使用天数，默认-1无期限   

	public String getPkNBR() {
		return pkNBR;
	}

	public void setPkNBR(String pkNBR) {
		this.pkNBR = pkNBR;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Integer getPkType() {
		return pkType;
	}

	public void setPkType(Integer pkType) {
		this.pkType = pkType;
	}

	public String getPkDesc() {
		return pkDesc;
	}

	public void setPkDesc(String pkDesc) {
		this.pkDesc = pkDesc;
	}

	public Long getCallerFee() {
		return callerFee;
	}

	public void setCallerFee(Long callerFee) {
		this.callerFee = callerFee;
	}

	public Long getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(Long smsFee) {
		this.smsFee = smsFee;
	}

	public Long getCalledFee() {
		return calledFee;
	}

	public void setCalledFee(Long calledFee) {
		this.calledFee = calledFee;
	}

	public Long getUsedFee() {
		return usedFee;
	}

	public void setUsedFee(Long usedFee) {
		this.usedFee = usedFee;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getUsedays() {
		return usedays;
	}

	public void setUsedays(Long usedays) {
		this.usedays = usedays;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntPackageInfo.class);
    }
}
