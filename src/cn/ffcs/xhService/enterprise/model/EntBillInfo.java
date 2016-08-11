package cn.ffcs.xhService.enterprise.model;

import cn.ffcs.xhService.enterprise.entity.EntBaseInfoBase;

import com.alibaba.fastjson.JSON;

/**
 * 企业账单信息
 * */
public class EntBillInfo extends EntBaseInfoBase {
	private Integer billtype;    // 账单类型：0：日账单，1月账单      
	private String createtime;   // 账单日期                          
	private Long callertotal ; // 累计拨打使用时长，单位：分钟
	private Long callerfee   ; // 累计拨打费用，单位：厘      
	private Long calledtotal ; // 累计接听使用时长，单位：分钟
	private Long calledfee   ; // 累计接听费用，单位：厘      
	private Long smstotal    ; // 累计短信使用条数            
	private Long smsfee      ; // 累计短信费用，单位：厘  

	public Integer getBilltype() {
		return billtype;
	}

	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Long getCallertotal() {
		return callertotal;
	}

	public void setCallertotal(Long callertotal) {
		this.callertotal = callertotal;
	}

	public Long getCallerfee() {
		return callerfee;
	}

	public void setCallerfee(Long callerfee) {
		this.callerfee = callerfee;
	}

	public Long getCalledtotal() {
		return calledtotal;
	}

	public void setCalledtotal(Long calledtotal) {
		this.calledtotal = calledtotal;
	}

	public Long getCalledfee() {
		return calledfee;
	}

	public void setCalledfee(Long calledfee) {
		this.calledfee = calledfee;
	}

	public Long getSmstotal() {
		return smstotal;
	}

	public void setSmstotal(Long smstotal) {
		this.smstotal = smstotal;
	}

	public Long getSmsfee() {
		return smsfee;
	}

	public void setSmsfee(Long smsfee) {
		this.smsfee = smsfee;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, EntBillInfo.class);
    }
}
