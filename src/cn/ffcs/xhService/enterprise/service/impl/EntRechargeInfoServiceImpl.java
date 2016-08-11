package cn.ffcs.xhService.enterprise.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntRechargeInfo;
import cn.ffcs.xhService.enterprise.service.EntRechargeInfoService;

@Service("EntRechargeInfoServiceImpl")
public class EntRechargeInfoServiceImpl implements EntRechargeInfoService {

	@Resource
    private CommonDao commonDao;
	
	public void addEntRechargeInfo(EntRechargeInfo info) {
		commonDao.insertInfo("EntRechargeInfo.insertEntRechargeInfo", info);
	}

}
