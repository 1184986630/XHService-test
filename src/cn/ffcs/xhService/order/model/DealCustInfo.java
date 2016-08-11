package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 入网信息表
 */
public class DealCustInfo {

	private String userId;			// 主键ID
	private String orderId;			//
	private String orderDetailId;	//
	private String crmCustId;		// 省CRM客户ID
	private String idCardNo;		// 身份证号码
	private String idCardPostCode;	// 身份证归属地邮编
	private String custName;		// 姓名
	private String custAffress;		// 家庭住址
	private String prodofferId;		// 套餐编号
	private String termId;			// 终端编号
	private String phoneNumber;		// 选择的号码
	private String areaPriCode;		// 省落地方编码
	private String areaCityCode;	// 市编码
	private String areaCountyCode;	// 县编码
	private String orderError;		// 正式单错误信息
	private String orderTranId;		// 正式单流水号
	private String wapTranId;		// 掌厅WAP流水号
	private String downFlag;		// 下发标识
	private String backInfo;		// 报文返回请求描述
	private String disableOpid;		// 修改人ID
	private String disableDate;		// 修改时间
	private String createDate;		// 创建时间
	private String remark;			// 备注
	private String contactNum;		// 联系号码
	private String accountType;		// 入网用户类型
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getCrmCustId() {
		return crmCustId;
	}
	public void setCrmCustId(String crmCustId) {
		this.crmCustId = crmCustId;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getIdCardPostCode() {
		return idCardPostCode;
	}
	public void setIdCardPostCode(String idCardPostCode) {
		this.idCardPostCode = idCardPostCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAffress() {
		return custAffress;
	}
	public void setCustAffress(String custAffress) {
		this.custAffress = custAffress;
	}
	public String getProdofferId() {
		return prodofferId;
	}
	public void setProdofferId(String prodofferId) {
		this.prodofferId = prodofferId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAreaPriCode() {
		return areaPriCode;
	}
	public void setAreaPriCode(String areaPriCode) {
		this.areaPriCode = areaPriCode;
	}
	public String getAreaCityCode() {
		return areaCityCode;
	}
	public void setAreaCityCode(String areaCityCode) {
		this.areaCityCode = areaCityCode;
	}
	public String getAreaCountyCode() {
		return areaCountyCode;
	}
	public void setAreaCountyCode(String areaCountyCode) {
		this.areaCountyCode = areaCountyCode;
	}
	public String getOrderError() {
		return orderError;
	}
	public void setOrderError(String orderError) {
		this.orderError = orderError;
	}
	public String getOrderTranId() {
		return orderTranId;
	}
	public void setOrderTranId(String orderTranId) {
		this.orderTranId = orderTranId;
	}
	public String getWapTranId() {
		return wapTranId;
	}
	public void setWapTranId(String wapTranId) {
		this.wapTranId = wapTranId;
	}
	public String getDownFlag() {
		return downFlag;
	}
	public void setDownFlag(String downFlag) {
		this.downFlag = downFlag;
	}
	public String getBackInfo() {
		return backInfo;
	}
	public void setBackInfo(String backInfo) {
		this.backInfo = backInfo;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	  
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealCustInfo.class);
    }
}
