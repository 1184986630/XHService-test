package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.entity.XhProInfo;
import cn.ffcs.xhService.uphold.service.XhProInfoService;

@Service("XhProInfoServiceImpl")
public class XhProInfoServiceImpl implements XhProInfoService {

	@Resource
    private CommonDao commonDao;
	
	/**
	 * 查询产品信息
	 * */
	public List<XhProInfo> getXhProInfo(String xspid) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("xspid", xspid);
		return commonDao.selectInfo("XhProInfo.selectXhProInfo", index);
	}

	public List<XhProInfo> getXhKxbProInfo(List<String> ids) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("ids", ids);
		return commonDao.selectInfo("XhProInfo.selectXhKxbProInfo", index);
	}
}
