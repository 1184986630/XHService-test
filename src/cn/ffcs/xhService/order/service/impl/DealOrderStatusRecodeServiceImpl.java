package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealOrderStatusRecodeService;

@Service("DealOrderStatusRecodeServiceImpl")
public class DealOrderStatusRecodeServiceImpl implements
		DealOrderStatusRecodeService {
	@Resource
    private CommonDao commonDao;
	
}
