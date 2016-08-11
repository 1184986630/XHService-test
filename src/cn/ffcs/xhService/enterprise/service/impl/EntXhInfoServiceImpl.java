package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.enterprise.model.EntXhInfo;
import cn.ffcs.xhService.enterprise.service.EntXhInfoService;

@Service("EntXhInfoServiceImpl")
public class EntXhInfoServiceImpl implements EntXhInfoService {

	@Resource
    private CommonDao commonDao;
	
	@Resource
	private RedisDao redisDao;
	
	/**
	 * 增加企业小号信息
	 * */
	public void addEntXhInfo(EntXhInfo info) {
		commonDao.insertInfo("EntXhInfo.insertEntXhInfo", info);
	}

	/**
	 * 查询企业小号信息
	 * */
	public List<EntXhInfo> getEntXhInfo(String spid, String appid, String phoneNo, String virtualNo,
			String sortorder, Integer pagenumber, Integer pagesize) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);
		if(sortorder != null) {
			index.put("sortorder",sortorder);
		}
		if(pagenumber != null && pagesize != null) {
			index.put("minrownum", (pagenumber-1) * pagesize + 1);
			index.put("maxrownum", pagenumber * pagesize);
		}
		
		return commonDao.selectInfo("EntXhInfo.selectEntXhInfo", index);
	}

	public int getEntXhInfoCount(String spid, String appid, String phoneNo, String virtualNo) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("spid", spid);
		index.put("appid", appid);
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);

		return commonDao.selectInfoCount("EntXhInfo.selectEntXhInfoCount", index);
	}

	/**
	 * 删除企业小号信息，同时搬到历史表
	 * */
	public void deleteEntXhInfo(EntXhInfo info) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(info == null) {
			return;
		}
		index.put("spid", info.getSpId());
		index.put("appid", info.getAppId());
		index.put("phoneno", info.getPhoneNo());
		index.put("virtualno", info.getVirtualNo());
		
        commonDao.deleteInfo("EntXhInfo.deleteEntXhInfo", index);
        commonDao.insertInfo("EntHisXhInfo.insertEntHisXhInfo", info);
	}
}
