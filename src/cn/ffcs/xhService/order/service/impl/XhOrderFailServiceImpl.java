package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.XhOrderFailService;

@Service("XhOrderFailServiceImpl")
public class XhOrderFailServiceImpl implements XhOrderFailService {

	@Resource
    private CommonDao commonDao;
}
