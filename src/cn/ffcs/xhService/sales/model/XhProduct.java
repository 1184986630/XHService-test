package cn.ffcs.xhService.sales.model;

/**
 * 产品配置表
 */
public class XhProduct {
	
	private String productId;	// 产品id
	private String xhNbr;		// 产品编码
	private String proType;		// 产品类型
	private Long smsAmount;		// 短信条数
	private Long useDays;		// 有效天数
	private String proName;		// 产品名称
	private String proDesc;		// 产品描述
	private Long proFee;		// 产品费用：分
	private String oprId;		// 创建人
	private String updId;		// 修改人
	private String createTime;	// 创建时间
	private String updTime;		// 修改时间
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getXhNbr() {
		return xhNbr;
	}
	public void setXhNbr(String xhNbr) {
		this.xhNbr = xhNbr;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public Long getSmsAmount() {
		return smsAmount;
	}
	public void setSmsAmount(Long smsAmount) {
		this.smsAmount = smsAmount;
	}
	public Long getUseDays() {
		return useDays;
	}
	public void setUseDays(Long useDays) {
		this.useDays = useDays;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public Long getProFee() {
		return proFee;
	}
	public void setProFee(Long proFee) {
		this.proFee = proFee;
	}
	public String getOprId() {
		return oprId;
	}
	public void setOprId(String oprId) {
		this.oprId = oprId;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}
	
}
