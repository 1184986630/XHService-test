package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealOrderExtendPropertyService;

@Service("DealOrderExtendPropertyServiceImpl")
public class DealOrderExtendPropertyServiceImpl implements
		DealOrderExtendPropertyService {
	@Resource
    private CommonDao commonDao;
	
}
