package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.BillsSettleInfo;

/**
 * 账单月结账单接口
 * */
public interface BillsSettleInfoService {
	public void addBillsSettleInfo(BillsSettleInfo info);
	public List<BillsSettleInfo> getBillsSettleInfo(String phoneNo, String virtualNo, 
			Integer calltypelimit, Integer curMonth, Integer startMonth, Integer endMonth, 
			String sortorder);
	public void updateBillsSettleInfo2Pro();
	
}
