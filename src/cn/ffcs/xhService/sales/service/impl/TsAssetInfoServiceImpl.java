package cn.ffcs.xhService.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.sales.model.TsAssetInfo;
import cn.ffcs.xhService.sales.service.TsAssetInfoService;

@Service("TsAssetInfoServiceImpl")
public class TsAssetInfoServiceImpl implements TsAssetInfoService {
	@Resource
    private CommonDao commonDao;

	public List<TsAssetInfo> getTsAssetInfoByTxm(String ts_asset_txm) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("ts_asset_txm", ts_asset_txm);		
		return commonDao.selectInfo("TsAssetInfo.selectTsAssetInfo", index);
	}
}
