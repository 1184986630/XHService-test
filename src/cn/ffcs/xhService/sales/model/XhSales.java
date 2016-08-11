package cn.ffcs.xhService.sales.model;

/**
 * 小号销售品
 */
public class XhSales {
	
	private String sale_id;		// 销售品id
	private String sale_name;	// 销售品名称
	private String sale_dis_name;	// 销售品展示名称
	private String sale_desc;	// 销售品描述
	private String ts_sp_ab;	// 销售品ab
	private String ts_mk_type_id;	// 销售品类型，对应销售品类型表设置
	private String ts_offer_type;	// 销售品类型
	private String ts_gs_code;		// 店铺编码
	private String ts_gs_nm;		// 店铺id
	private String status;			// 状态：0上架，1下架
	private String order_effect_time;	//订单待支付有效时间
	private String oprid;	// 创建人
	private String updid;	// 修改人
	private String createtime;	// 创建时间
	private String updtime;	// 修改时间
	
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	public String getSale_dis_name() {
		return sale_dis_name;
	}
	public void setSale_dis_name(String sale_dis_name) {
		this.sale_dis_name = sale_dis_name;
	}
	public String getSale_desc() {
		return sale_desc;
	}
	public void setSale_desc(String sale_desc) {
		this.sale_desc = sale_desc;
	}
	public String getTs_sp_ab() {
		return ts_sp_ab;
	}
	public void setTs_sp_ab(String ts_sp_ab) {
		this.ts_sp_ab = ts_sp_ab;
	}
	public String getTs_mk_type_id() {
		return ts_mk_type_id;
	}
	public void setTs_mk_type_id(String ts_mk_type_id) {
		this.ts_mk_type_id = ts_mk_type_id;
	}
	public String getTs_offer_type() {
		return ts_offer_type;
	}
	public void setTs_offer_type(String ts_offer_type) {
		this.ts_offer_type = ts_offer_type;
	}
	public String getTs_gs_code() {
		return ts_gs_code;
	}
	public void setTs_gs_code(String ts_gs_code) {
		this.ts_gs_code = ts_gs_code;
	}
	public String getTs_gs_nm() {
		return ts_gs_nm;
	}
	public void setTs_gs_nm(String ts_gs_nm) {
		this.ts_gs_nm = ts_gs_nm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrder_effect_time() {
		return order_effect_time;
	}
	public void setOrder_effect_time(String order_effect_time) {
		this.order_effect_time = order_effect_time;
	}
	public String getOprid() {
		return oprid;
	}
	public void setOprid(String oprid) {
		this.oprid = oprid;
	}
	public String getUpdid() {
		return updid;
	}
	public void setUpdid(String updid) {
		this.updid = updid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdtime() {
		return updtime;
	}
	public void setUpdtime(String updtime) {
		this.updtime = updtime;
	}

}
