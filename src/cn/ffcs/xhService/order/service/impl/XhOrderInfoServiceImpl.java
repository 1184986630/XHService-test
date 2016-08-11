package cn.ffcs.xhService.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.enums.OrderStatusEnum;
import cn.ffcs.xhService.order.model.XhOrderInfo;
import cn.ffcs.xhService.order.service.XhOrderInfoService;

@Service("XhOrderInfoServiceImpl")
public class XhOrderInfoServiceImpl implements XhOrderInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	
	@Resource
	private CommonDao commonDao;
	
	
	@Override
	public List<XhOrderInfo> getXhOrderInfo(String orderid) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("orderid", orderid);
		return commonDao.selectInfo("XhOrderInfo.selectXhOrderInfo", index);
	}
	
	@Override
	public int getXhOrderInfoCount(String orderid) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("orderid", orderid);
		return commonDao.selectInfoCount("XhOrderInfo.selectXhOrderInfoCount", index);
	}
	
	@Override
	public void updateOrderPayStatus(String orderid) throws Exception {
		if(orderid == null || getXhOrderInfoCount(orderid)<1) {
			throw new Exception("更新订单状态失败");
		}
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("orderid", orderid);
		index.put("paying", OrderStatusEnum.STATUS_BLZ.getCode());
		commonDao.updateInfo("XhOrderInfo.updateOrderPayStatusInfo", index);
		// TODO: 其他表的更新
	}

}
