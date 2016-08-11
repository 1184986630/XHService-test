package cn.ffcs.xhService.order.enums;

/**
 * 订单状态
 * @author Administrator
 *
 */
public enum OrderStatusEnum {
	
	STATUS_BLZ("20100", "办理中"), 
	STATUS_BLCG("20101", "办理成功"), 
	STATUS_BLSB("20102", "办理失败"), 
	STATUS_DDZF("10100", "等待支付"),
	STATUS_DZGB("10104", "订单关闭"),
	STATUS_ZFSB("10703", "支付失败"),
	STATUS_TKZ("11201", "退款中"),
	STATUS_TKCG("11202", "退款成功"),
	STATUS_TKSB("11203", "退款失败"),
	STATUS_ZJSHBTG("10103", "证件审核不通过"),
	STATUS_YQX("10701", "已取消"),
	;
	
	private String code;
	private String desc;
	
	private OrderStatusEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 根据code 获取订单状态枚举
	 * @param code
	 * @return
	 */
	public static OrderStatusEnum fromvalue(String code) {
		for(OrderStatusEnum inv :OrderStatusEnum.values())
			if(inv.code.equals(code))
				return inv;
		return null;
	}
	
	/**
	 * 根据code 获取订单状态枚举
	 * @param code
	 * @return
	 */
	public static OrderStatusEnum fromvalue(Integer code) {
		if(code==null)
			return null;
		return fromvalue(code.toString());
	}
	
}
