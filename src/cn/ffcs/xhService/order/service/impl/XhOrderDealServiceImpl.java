package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.XhOrderDealService;

@Service("XhOrderDealServiceImpl")
public class XhOrderDealServiceImpl implements XhOrderDealService {

	@Resource
    private CommonDao commonDao;
}
