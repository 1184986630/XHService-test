package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealOrderUserChooseService;

@Service("DealOrderUserChooseServiceImpl")
public class DealOrderUserChooseServiceImpl implements
		DealOrderUserChooseService {
	@Resource
    private CommonDao commonDao;
	
}
