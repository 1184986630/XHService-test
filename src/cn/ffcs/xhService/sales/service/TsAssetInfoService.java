package cn.ffcs.xhService.sales.service;

import java.util.List;

import cn.ffcs.xhService.sales.model.TsAssetInfo;

public interface TsAssetInfoService {
	/**
	 * 根据申请编号查询店铺信息
	 * @param ts_asset_txm 申请编号
	 * @return
	 */
	public List<TsAssetInfo> getTsAssetInfoByTxm(String ts_asset_txm);
}
