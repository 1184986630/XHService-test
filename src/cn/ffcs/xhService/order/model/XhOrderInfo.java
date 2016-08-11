package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 订单表
 */
public class XhOrderInfo {
	
	private String orderId;			// 订单号
	private String userId;			// 客户标识
	private String orderSource;		// 订单来源
	private String tsGsCode;		// 店铺编码
	private String tsGsNm;			// 店铺id
	private String provCode;		// 主号省编码
	private String provName;		// 主号省名称
	private String cityCode;		// 主号市编码
	private String cityName;		// 主号市名称
	private String provCodeV;		// 小号省编码
	private String provNameV; 		// 小号省名称
	private String cityCodeV;		// 小号市编码
	private String cityNameV;		// 小号市名称
	private String saleId;			// 销售品ID
	private String tsMkTypeId;		// 销售品类型
	private String pkId;			// 套餐ID
	private String kxbId;			// 可选包ID
	private Long price;				// 价格：分
	private String orderStatus;		// 订单状态
	private String payTranId;		// 订单支付流水号
	private String orderCreatetime;	// 订单生成时间
	private String orderConmpleteTime;	// 订单完成时间
	private String orderClosedTime;	// 订单关闭时间
	private String orderExpireTime;	// 订单失效时间
	private String orderOverTime;	// 订单超时时间
	private String orderPayTime;	// 支付完成时间
	private String orderRemark;		// 修改备注
	private String disableDate;		// 修改时间
	private String disableOpid;		// 修改操作员
	private String recStatus;		// 记录状态
	private String orderItemGroupId;// 订单项分组ID
	private String orderItemGroupStatus;	// 订单分组ID 报竣
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getTsGsCode() {
		return tsGsCode;
	}
	public void setTsGsCode(String tsGsCode) {
		this.tsGsCode = tsGsCode;
	}
	public String getTsGsNm() {
		return tsGsNm;
	}
	public void setTsGsNm(String tsGsNm) {
		this.tsGsNm = tsGsNm;
	}
	public String getProvCode() {
		return provCode;
	}
	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}
	public String getProvName() {
		return provName;
	}
	public void setProvName(String provName) {
		this.provName = provName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvCodeV() {
		return provCodeV;
	}
	public void setProvCodeV(String provCodeV) {
		this.provCodeV = provCodeV;
	}
	public String getProvNameV() {
		return provNameV;
	}
	public void setProvNameV(String provNameV) {
		this.provNameV = provNameV;
	}
	public String getCityCodeV() {
		return cityCodeV;
	}
	public void setCityCodeV(String cityCodeV) {
		this.cityCodeV = cityCodeV;
	}
	public String getCityNameV() {
		return cityNameV;
	}
	public void setCityNameV(String cityNameV) {
		this.cityNameV = cityNameV;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getTsMkTypeId() {
		return tsMkTypeId;
	}
	public void setTsMkTypeId(String tsMkTypeId) {
		this.tsMkTypeId = tsMkTypeId;
	}
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public String getKxbId() {
		return kxbId;
	}
	public void setKxbId(String kxbId) {
		this.kxbId = kxbId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPayTranId() {
		return payTranId;
	}
	public void setPayTranId(String payTranId) {
		this.payTranId = payTranId;
	}
	public String getOrderCreatetime() {
		return orderCreatetime;
	}
	public void setOrderCreatetime(String orderCreatetime) {
		this.orderCreatetime = orderCreatetime;
	}
	public String getOrderConmpleteTime() {
		return orderConmpleteTime;
	}
	public void setOrderConmpleteTime(String orderConmpleteTime) {
		this.orderConmpleteTime = orderConmpleteTime;
	}
	public String getOrderClosedTime() {
		return orderClosedTime;
	}
	public void setOrderClosedTime(String orderClosedTime) {
		this.orderClosedTime = orderClosedTime;
	}
	public String getOrderExpireTime() {
		return orderExpireTime;
	}
	public void setOrderExpireTime(String orderExpireTime) {
		this.orderExpireTime = orderExpireTime;
	}
	public String getOrderOverTime() {
		return orderOverTime;
	}
	public void setOrderOverTime(String orderOverTime) {
		this.orderOverTime = orderOverTime;
	}
	public String getOrderPayTime() {
		return orderPayTime;
	}
	public void setOrderPayTime(String orderPayTime) {
		this.orderPayTime = orderPayTime;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getDisableDate() {
		return disableDate;
	}
	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}
	public String getDisableOpid() {
		return disableOpid;
	}
	public void setDisableOpid(String disableOpid) {
		this.disableOpid = disableOpid;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getOrderItemGroupId() {
		return orderItemGroupId;
	}
	public void setOrderItemGroupId(String orderItemGroupId) {
		this.orderItemGroupId = orderItemGroupId;
	}
	public String getOrderItemGroupStatus() {
		return orderItemGroupStatus;
	}
	public void setOrderItemGroupStatus(String orderItemGroupStatus) {
		this.orderItemGroupStatus = orderItemGroupStatus;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, XhOrderInfo.class);
    }
}
