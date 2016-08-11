package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealBillPayresponseService;

@Service("DealBillPayresponseServiceImpl")
public class DealBillPayresponseServiceImpl implements
		DealBillPayresponseService {
	@Resource
    private CommonDao commonDao;
}
