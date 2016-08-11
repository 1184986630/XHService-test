package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.DealInvoiceInfoService;

@Service("DealInvoiceInfoServiceImpl")
public class DealInvoiceInfoServiceImpl implements DealInvoiceInfoService {
	@Resource
    private CommonDao commonDao;
}
