package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealOrderXhFlagService;

@Service("DealOrderXhFlagServiceImpl")
public class DealOrderXhFlagServiceImpl implements DealOrderXhFlagService {

	@Resource
    private CommonDao commonDao;
	
}
