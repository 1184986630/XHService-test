package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealCustInfoService;

@Service("DealCustInfoServiceImpl")
public class DealCustInfoServiceImpl implements DealCustInfoService {
	@Resource
    private CommonDao commonDao;
}
