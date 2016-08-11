package cn.ffcs.xhService.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.order.service.XhOrderOptService;

@Service("XhOrderOptServiceImpl")
public class XhOrderOptServiceImpl implements XhOrderOptService {

	@Resource
    private CommonDao commonDao;
}
