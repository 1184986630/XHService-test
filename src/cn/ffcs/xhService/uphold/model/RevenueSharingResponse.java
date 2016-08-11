package cn.ffcs.xhService.uphold.model;

public class RevenueSharingResponse {
    private float amount;//总金额
	private String revenueSharing ;//分账信息
	private String orderSubmitTime;//订单提交时间
	private String merchantId ;//由翼支付网关平台统一分配的商户号
	private String secretKey;//商户号对应的支付密钥
	private String merchantCode;//商户代码
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getRevenueSharing() {
		return revenueSharing;
	}
	public void setRevenueSharing(String revenueSharing) {
		this.revenueSharing = revenueSharing;
	}
	public String getOrderSubmitTime() {
		return orderSubmitTime;
	}
	public void setOrderSubmitTime(String orderSubmitTime) {
		this.orderSubmitTime = orderSubmitTime;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
}
