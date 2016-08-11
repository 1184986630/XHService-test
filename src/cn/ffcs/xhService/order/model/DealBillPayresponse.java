package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 支付反馈记录表
 */
public class DealBillPayresponse {

	private String id;			// 主键
	private String uptranSeq;	// 支付平台交易流水号
	private String tranDate;	// 支付平台交易日期
	private String retnCode;	// 处理结果码
	private String retnInfo;	// 处理结果解释码
	private String orderReqTranSeq;		// 订单请求交易流水号
	private String orderSeq;		// 订单号
	private Float orderAmount;		// 订单总金额
	private Float productAmount;	// 产品金额
	private Float attachAmount;		// 附加金额
	private String curType;			// 币种
	private String encodeType;		// 加密方式
	private String attach;			// SP附加信息
	private String sign;			// 数字签名
	private String disableOpid;		// 修改操作员
	private String disableDate;		// 修改时间
	private String createDate;		// 创建时间
	private Integer recStatus;		// 记录状态
	private String remark;			// 备注
	private String platformId;		// 支付平台id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUptranSeq() {
		return uptranSeq;
	}
	public void setUptranSeq(String uptranSeq) {
		this.uptranSeq = uptranSeq;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getRetnCode() {
		return retnCode;
	}
	public void setRetnCode(String retnCode) {
		this.retnCode = retnCode;
	}
	public String getRetnInfo() {
		return retnInfo;
	}
	public void setRetnInfo(String retnInfo) {
		this.retnInfo = retnInfo;
	}
	public String getOrderReqTranSeq() {
		return orderReqTranSeq;
	}
	public void setOrderReqTranSeq(String orderReqTranSeq) {
		this.orderReqTranSeq = orderReqTranSeq;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	public Float getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Float getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Float productAmount) {
		this.productAmount = productAmount;
	}
	public Float getAttachAmount() {
		return attachAmount;
	}
	public void setAttachAmount(Float attachAmount) {
		this.attachAmount = attachAmount;
	}
	public String getCurType() {
		return curType;
	}
	public void setCurType(String curType) {
		this.curType = curType;
	}
	public String getEncodeType() {
		return encodeType;
	}
	public void setEncodeType(String encodeType) {
		this.encodeType = encodeType;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getDisableOpid() {
		return disableOpid;
	}
	public void setDisableOpid(String disableOpid) {
		this.disableOpid = disableOpid;
	}
	public String getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(Integer recStatus) {
		this.recStatus = recStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealBillPayresponse.class);
	}
}
