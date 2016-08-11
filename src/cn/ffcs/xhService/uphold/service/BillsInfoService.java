package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.BillsInfo;
import cn.ffcs.xhService.uphold.model.CallRecordsInfo;

/**
 * 账单明细接口
 * */
public interface BillsInfoService {
	public void addBillsInfo(BillsInfo info);
	public List<BillsInfo> getBillsInfo(String phoneNo, String virtualNo, Integer calltypelimit, String month, 
			Integer callType, Integer page, Integer size, Integer flag, String sortorder);
	public void addBillsInfos(List<BillsInfo> list);
	public int getBillsInfoCount(String phoneNo, String virtualNo, Integer month, Integer calltypelimit, 
			Integer callType, Integer flag);
	public List<CallRecordsInfo> getCallRecordsInfo(String phoneNo, String virtualNo, List<Integer> calltypes, 
			String lastTime, String sortorder, Integer flag, Integer page, Integer size);
	public void updateBillsInfo(String phoneNo, String virtualNo, List<Long> ids, Integer flag, List<Integer> calltypes);
	public int addMissedCallBillsInfo(String callerno, String virtualno, Integer month, String starttime);
}
