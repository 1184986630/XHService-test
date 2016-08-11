package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 用户选择表
 */
public class DealOrderUserChoose {

	private String orderId;			
	private String orderDetailid;	
	private String chooseType;		// 选择类型
	private String chooseId;		// 当选择项类型为号卡时，此处记录号码
	private String chooseName;		
	private String remark;			
	private String parChoose;		// 逻辑关系
	private Float choosePrice;		// 选择项价格
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDetailid() {
		return orderDetailid;
	}
	public void setOrderDetailid(String orderDetailid) {
		this.orderDetailid = orderDetailid;
	}
	public String getChooseType() {
		return chooseType;
	}
	public void setChooseType(String chooseType) {
		this.chooseType = chooseType;
	}
	public String getChooseId() {
		return chooseId;
	}
	public void setChooseId(String chooseId) {
		this.chooseId = chooseId;
	}
	public String getChooseName() {
		return chooseName;
	}
	public void setChooseName(String chooseName) {
		this.chooseName = chooseName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParChoose() {
		return parChoose;
	}
	public void setParChoose(String parChoose) {
		this.parChoose = parChoose;
	}
	public Float getChoosePrice() {
		return choosePrice;
	}
	public void setChoosePrice(Float choosePrice) {
		this.choosePrice = choosePrice;
	}
	 
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealOrderUserChoose.class);
    }
}
