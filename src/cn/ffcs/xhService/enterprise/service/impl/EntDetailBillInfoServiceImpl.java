package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.service.EntDetailBillInfoService;

@Service("EntDetailBillInfoServiceImpl")
public class EntDetailBillInfoServiceImpl implements EntDetailBillInfoService {

	@Resource
    private CommonDao commonDao;
	
	/**
	 * 增加企业详单信息
	 * */
	public void addEntDetailBillInfo(EntDetailBillInfo info) {
		commonDao.insertInfo("EntDetailBillInfo.insertEntDetailBillInfo", info);
	}

	/**
	 * 查询企业详单信息
	 * */
	public List<EntDetailBillInfo> getEntDetailBillInfo(String spid, String appid, String phoneno,
			String virtualno, List<Integer> callTypes, String month, Integer page,
			Integer size, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneno);
		index.put("virtualno", virtualno);
		index.put("calltypes", callTypes);
		index.put("month", month);
		if(page != null && size != null) {
			index.put("minrownum", (page-1)*size+1);
			index.put("maxrownum", page*size);
		}
		index.put("sortorder", sortorder);
        
        return commonDao.selectInfo("EntDetailBillInfo.selectEntDetailBillInfo", index);
	}

	@Override
	public int getEntDetailBillInfoCount(String spid, String appid,
			String phoneNo, String virtualNo, List<Integer> callTypes, 
			String month) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);
		index.put("calltypes", callTypes);
		index.put("month", month);
		
		return commonDao.selectInfoCount("EntDetailBillInfo.selectEntDetailBillInfoCount", index);
	}

	/**
	 * 批量增加
	 * */
	public void addEntDetailBillInfos(List<EntDetailBillInfo> list) {
		commonDao.insertInfos("EntDetailBillInfo.insertEntDetailBillInfo", list, false);
	}
}
