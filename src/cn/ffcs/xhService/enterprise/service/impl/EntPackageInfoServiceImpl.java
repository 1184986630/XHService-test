package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntPackageInfo;
import cn.ffcs.xhService.enterprise.service.EntPackageInfoService;

@Service("EntPackageInfoServiceImpl")
public class EntPackageInfoServiceImpl implements EntPackageInfoService {

	@Resource
    private CommonDao commonDao;
	
	/**
	 * 增加套餐信息
	 * */
	public void addEntPackageInfo(EntPackageInfo info) {
		commonDao.insertInfo("EntPackageInfo.insertEntPackageInfo", info);
	}

	/**
	 * 查询套餐
	 * */
	public List<EntPackageInfo> getEntPackageInfo(String pknbr) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("pknbr", pknbr);
		
		return commonDao.selectInfo("EntPackageInfo.selectEntPackageInfo", index);
	}

	/**
	 * 查询满足条件的套餐数
	 * */
	public int getEntPackageInfoCount(String pknbr) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("pknbr", pknbr);
		
		return commonDao.selectInfoCount("EntPackageInfo.selectEntPackageInfoCount", index);
	}
}
