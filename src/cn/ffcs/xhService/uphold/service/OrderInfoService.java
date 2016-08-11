package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.OrderInfo;

public interface OrderInfoService {
	public void addOrderInfo(OrderInfo info);
	public void addOrderInfos(List<OrderInfo> list);
	public List<OrderInfo> getOrderInfo(String orderid, String phoneno, String virtualno, String salesid, String sortorder);
}
