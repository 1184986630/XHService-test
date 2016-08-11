package cn.ffcs.xhService.uphold.model;

import java.util.List;

public class XhTcMxInfo {
		private Object cx ;
		private Object dx;
		private List<XhKxbInfo> kxbs;
		private String ll;
		private String prodCode;//产品编码
		private String tcMxId;//产品编号
		private String tcMxName;//产品明细描述
		private double  tcMxPrice;//产品费用
		private String tcmxname;//固定值--短信包
		private Object wifisc;
		private String yy;
		
		public Object getCx() {
			return cx;
		}
		public void setCx(Object cx) {
			this.cx = cx;
		}
		
		public Object getDx() {
			return dx;
		}
		public void setDx(Object dx) {
			this.dx = dx;
		}
		public List<XhKxbInfo> getKxbs() {
			return kxbs;
		}
		public void setKxbs(List<XhKxbInfo> kxbs) {
			this.kxbs = kxbs;
		}
		public String getLl() {
			return ll;
		}
		public void setLl(String ll) {
			this.ll = ll;
		}
		public String getProdCode() {
			return prodCode;
		}
		public void setProdCode(String prodCode) {
			this.prodCode = prodCode;
		}
		public String getTcMxId() {
			return tcMxId;
		}
		public void setTcMxId(String tcMxId) {
			this.tcMxId = tcMxId;
		}
		public String getTcMxName() {
			return tcMxName;
		}
		public void setTcMxName(String tcMxName) {
			this.tcMxName = tcMxName;
		}
		public double getTcMxPrice() {
			return tcMxPrice;
		}
		public void setTcMxPrice(double tcMxPrice) {
			this.tcMxPrice = tcMxPrice;
		}
		public String getTcmxname() {
			return tcmxname;
		}
		public void setTcmxname(String tcmxname) {
			this.tcmxname = tcmxname;
		}
	
		public Object getWifisc() {
			return wifisc;
		}
		public void setWifisc(Object wifisc) {
			this.wifisc = wifisc;
		}
		public String getYy() {
			return yy;
		}
		public void setYy(String yy) {
			this.yy = yy;
		}
		
		
}
