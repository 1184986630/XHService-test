package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 发票信息表
 */
public class DealInvoiceInfo {

	private String orderId;				// 订单号
	private String invoiceLevel;		// 类型等级
	private String invoiceTypeCode;		// 发票类型编码
	private String invoiceType;			// 发票类型 
	private String invoiceLbCode;		// 发票类别编码
	private String invoiceLb;			// 发票类别
	private String invoiceTitleCode;	// 发票title编码
	private String invoiceTitle;		// 发票title
	private String invoiceTsy;			// 提示语
	private String invoiceSm;			// 说明
	private Float price;				// 价格
	private String invoiceUrl;			// 电子发票  URL
	private String invoicePdfUrl;		// 电子发票PDF URL
	private String invoiceCode;			// 发票代码
	private String invoiceNumber;		// 发票号码
	private String invoiceCaptcha;		// 发票防伪码
	private String invoiceOuterPdfUrl;	//	
	private String invoiceEmail;		// 发票发送至邮箱
	private String invoiceStatus;		// 发票状态 
	private String invoicePhone;		// 发票发送至手机号		
	private String statusChangeTime;	// 发票状态变化时间	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceLevel() {
		return invoiceLevel;
	}
	public void setInvoiceLevel(String invoiceLevel) {
		this.invoiceLevel = invoiceLevel;
	}
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceLbCode() {
		return invoiceLbCode;
	}
	public void setInvoiceLbCode(String invoiceLbCode) {
		this.invoiceLbCode = invoiceLbCode;
	}
	public String getInvoiceLb() {
		return invoiceLb;
	}
	public void setInvoiceLb(String invoiceLb) {
		this.invoiceLb = invoiceLb;
	}
	public String getInvoiceTitleCode() {
		return invoiceTitleCode;
	}
	public void setInvoiceTitleCode(String invoiceTitleCode) {
		this.invoiceTitleCode = invoiceTitleCode;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceTsy() {
		return invoiceTsy;
	}
	public void setInvoiceTsy(String invoiceTsy) {
		this.invoiceTsy = invoiceTsy;
	}
	public String getInvoiceSm() {
		return invoiceSm;
	}
	public void setInvoiceSm(String invoiceSm) {
		this.invoiceSm = invoiceSm;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getInvoiceUrl() {
		return invoiceUrl;
	}
	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	public String getInvoicePdfUrl() {
		return invoicePdfUrl;
	}
	public void setInvoicePdfUrl(String invoicePdfUrl) {
		this.invoicePdfUrl = invoicePdfUrl;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceCaptcha() {
		return invoiceCaptcha;
	}
	public void setInvoiceCaptcha(String invoiceCaptcha) {
		this.invoiceCaptcha = invoiceCaptcha;
	}
	public String getInvoiceOuterPdfUrl() {
		return invoiceOuterPdfUrl;
	}
	public void setInvoiceOuterPdfUrl(String invoiceOuterPdfUrl) {
		this.invoiceOuterPdfUrl = invoiceOuterPdfUrl;
	}
	public String getInvoiceEmail() {
		return invoiceEmail;
	}
	public void setInvoiceEmail(String invoiceEmail) {
		this.invoiceEmail = invoiceEmail;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getInvoicePhone() {
		return invoicePhone;
	}
	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}
	public String getStatusChangeTime() {
		return statusChangeTime;
	}
	public void setStatusChangeTime(String statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}
	
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealInvoiceInfo.class);
    }
	
}
