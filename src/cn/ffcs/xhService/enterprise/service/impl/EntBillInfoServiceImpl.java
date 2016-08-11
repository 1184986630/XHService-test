package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.entity.EntSettleBillInfo;
import cn.ffcs.xhService.enterprise.model.EntBillInfo;
import cn.ffcs.xhService.enterprise.service.EntBillInfoService;

@Service("EntBillInfoServiceImpl")
public class EntBillInfoServiceImpl implements EntBillInfoService {

	@Resource
    private CommonDao commonDao;
	
	/**
	 * 增加企业详单信息
	 * */
	public void addEntBillInfo(EntBillInfo info) {
		commonDao.insertInfo("EntBillInfo.insertEntBillInfo", info);
	}

	public int getEntBillInfoCount(String spid, String appid, String phoneno, String virtualno,
			List<Integer> callTypes, Integer billtype, String curday, String endday,
			String startmonth, String endmonth) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneno);
		index.put("virtualno", virtualno);
		index.put("calltypes", callTypes);
		index.put("billtype", billtype);
		index.put("curday", curday);
		index.put("endday", endday);
		index.put("startmonth", startmonth);
		index.put("endmonth", endmonth);
		return commonDao.selectInfoCount("EntBillInfo.selectEntBillInfoCount", index);
	}

	/**
	 * 企业级账单查询
	 * */
	public List<EntSettleBillInfo> getEntBillInfo(String spid, String appid, String phoneno, String virtualno,
			List<Integer> callTypes, Integer billtype, String curday, String endday,
			String startmonth, String endmonth) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneno);
		index.put("virtualno", virtualno);
		index.put("calltypes", callTypes);
		index.put("billtype", billtype);
		index.put("curday", curday);
		index.put("endday", endday);
		index.put("startmonth", startmonth);
		index.put("endmonth", endmonth);
        
        return commonDao.selectInfo("EntBillInfo.selectEntBillInfo", index);
	}
}
