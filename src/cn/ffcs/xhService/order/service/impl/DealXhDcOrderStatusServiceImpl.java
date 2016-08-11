package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealXhDcOrderStatusService;

@Service("DealXhDcOrderStatusServiceImpl")
public class DealXhDcOrderStatusServiceImpl implements
		DealXhDcOrderStatusService {

	@Resource
    private CommonDao commonDao;

}
