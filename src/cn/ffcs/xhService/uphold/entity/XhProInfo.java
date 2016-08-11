package cn.ffcs.xhService.uphold.entity;
/**
 * 产品实体类
 */
public class XhProInfo {
	private String id;
	private String product_id;
	private String pro_name;
	private String  xh_nbr;
	private String pro_desc;
	private double pro_fee;
	private String pro_type;
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getXh_nbr() {
		return xh_nbr;
	}
	public void setXh_nbr(String xh_nbr) {
		this.xh_nbr = xh_nbr;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public double getPro_fee() {
		return pro_fee;
	}
	public void setPro_fee(double pro_fee) {
		this.pro_fee = pro_fee;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
