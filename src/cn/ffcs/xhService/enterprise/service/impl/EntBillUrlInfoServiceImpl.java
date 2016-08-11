package cn.ffcs.xhService.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntBillUrlInfo;
import cn.ffcs.xhService.enterprise.service.EntBillUrlInfoService;

@Service("EntBillUrlInfoServiceImpl")
public class EntBillUrlInfoServiceImpl implements EntBillUrlInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加账单文件信息
	 * */
	public void addEntBillUrlInfo(EntBillUrlInfo info) {
		commonDao.insertInfo("EntBillUrlInfo.insertEntBillUrlInfo", info);
	}

	/**
	 * 批量增加账单文件信息
	 * */
	public void addEntBillUrlInfos(List<EntBillUrlInfo> list) {
		commonDao.insertInfos("EntBillUrlInfo.insertEntBillUrlInfo", list, false);
	}

	/**
	 * 查询
	 * */
	public List<EntBillUrlInfo> getEntBillUrlInfo(String url, Integer flag, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("url", url);
		index.put("flag", flag);
		index.put("sortorder", sortorder);
		
        return commonDao.selectInfo("EntBillUrlInfo.selectEntBillUrlInfo", index);
	}
}
