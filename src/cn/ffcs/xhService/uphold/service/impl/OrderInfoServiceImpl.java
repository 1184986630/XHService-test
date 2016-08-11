package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.OrderInfo;
import cn.ffcs.xhService.uphold.service.OrderInfoService;

@Service("OrderInfoServiceImpl")
public class OrderInfoServiceImpl implements OrderInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加订购信息
	 * */
	public void addOrderInfo(OrderInfo info) {
		commonDao.insertInfo("OrderInfo.insertOrderInfo", info);
	}
	
	/**
	 * 批量增加订购信息
	 * */
	public void addOrderInfos(List<OrderInfo> list) {
    	commonDao.insertInfos("OrderInfo.insertOrderInfo", list, false);
    }

	/**
	 * 订单信息查询
	 * */
	public List<OrderInfo> getOrderInfo(String orderid, String phoneno, String virtualno, String salesid, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(orderid != null) {
			index.put("orderid", orderid);
		}
		if(phoneno != null) {
			index.put("phoneno", phoneno);
		}
		if(virtualno != null) {
			index.put("virtualno", virtualno);
		}
		if(salesid != null) {
			index.put("salesid", salesid);
		}
		if(sortorder != null) {
			index.put("sortorder", sortorder);
		}
		
        return commonDao.selectInfo("OrderInfo.selectOrderInfo", index);
	}
}
