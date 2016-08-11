package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.model.EntInfo;
import cn.ffcs.xhService.enterprise.model.EntRechargeInfo;
import cn.ffcs.xhService.enterprise.service.EntInfoService;


@Service("EntInfoServiceImpl")
public class EntInfoServiceImpl implements EntInfoService {

	@Resource
    private CommonDao commonDao;
	
	@Resource
	private RedisDao redisDao;
	
	/**
	 * 增加企业信息
	 * */
	public void addEntInfo(EntInfo info) {
		commonDao.insertInfo("EntInfo.insertEntInfo", info);
	}

	/**
	 * 修改企业信息
	 * */
	public void updateEntInfo(String spid, String appid, Integer status, Integer pushStatus, Long fee, List<EntDetailBillInfo> detailList) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("status", status);
		index.put("pushstatus", pushStatus);
		index.put("fee", fee);
        
        commonDao.updateInfo("EntInfo.updateEntInfo", index);
        commonDao.insertInfos("EntDetailBillInfo.insertEntDetailBillInfo", detailList, false);
	}
	
	/**
	 * 修改企业重置信息，同时增加充值记录
	 * */
	public void updateEntChargeInfo(String spid, String appid, Integer status, Long fee, EntRechargeInfo info) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("status", status);
		index.put("fee", fee);
        
        commonDao.updateInfo("EntInfo.updateEntInfo", index);
        commonDao.insertInfo("EntRechargeInfo.insertEntRechargeInfo", info);
	}

	/**
	 * 查询企业信息
	 * */
	public List<EntInfo> getEntInfo(String spid, String appid) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
        
        return commonDao.selectInfo("EntInfo.selectEntInfo", index);
	}
}
