package cn.ffcs.xhService.enterprise.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntOrderInfo;
import cn.ffcs.xhService.enterprise.service.EntOrderInfoService;

@Service("EntOrderInfoServiceImpl")
public class EntOrderInfoServiceImpl implements EntOrderInfoService {

	@Resource
    private CommonDao commonDao;
	
	public void addEntOrderInfo(EntOrderInfo info) {
		commonDao.insertInfo("EntOrderInfo.insertEntOrderInfo", info);
	}

}
