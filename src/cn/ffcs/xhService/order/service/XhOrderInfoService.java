package cn.ffcs.xhService.order.service;

import java.util.List;

import cn.ffcs.xhService.order.model.XhOrderInfo;


public interface XhOrderInfoService {
	public List<XhOrderInfo> getXhOrderInfo(String orderid);
	public int getXhOrderInfoCount(String orderid);
	public void updateOrderPayStatus(String orderid) throws Exception;
}
