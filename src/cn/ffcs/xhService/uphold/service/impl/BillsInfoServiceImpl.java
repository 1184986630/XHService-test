package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.BillsInfo;
import cn.ffcs.xhService.uphold.model.CallRecordsInfo;
import cn.ffcs.xhService.uphold.service.BillsInfoService;

@Service("BillsInfoServiceImpl")
public class BillsInfoServiceImpl implements BillsInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加账单明细
	 * */
	public void addBillsInfo(BillsInfo info) {
		commonDao.insertInfo("BillsInfo.insertBillsInfo", info);
	}

	/**
	 * 账单明细查询
	 * */
	public List<BillsInfo> getBillsInfo(String phoneNo, String virtualNo, Integer calltypelimit,
			String month, Integer calltype, Integer page, Integer size, Integer flag, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);
		index.put("month", month);
		if (page != null && size != null) {
			index.put("minrownum", (page-1)*size + 1);
			index.put("maxrownum", page*size);
		}
		index.put("sortorder", sortorder);
		index.put("calltypelimit", calltypelimit);
		index.put("calltype", calltype);
		index.put("flag", flag);
		
		return commonDao.selectInfo("BillsInfo.selectBillsInfo", index);
	}

	/**
	 * 批量增加账单明细
	 * */
	public void addBillsInfos(List<BillsInfo> list) {
		commonDao.insertInfos("BillsInfo.insertBillsInfo", list, false);
	}

	/**
	 * 统计满足条件的账单明细信息
	 * */
	public int getBillsInfoCount(String phoneNo, String virtualNo, Integer month, Integer calltypelimit, 
			Integer calltype, Integer flag) {
		Map<String, Object> index = new HashMap<String, Object>();
        index.put("phoneno", phoneNo);
        index.put("virtualno", virtualNo);
        index.put("month", month);
        index.put("calltypelimit", calltypelimit);
        index.put("calltype", calltype);
        index.put("flag", flag);

        return commonDao.selectInfoCount("BillsInfo.selectBillsInfoCount", index);
	}
	
	/**
	 * 通话记录查询
	 * */
	public List<CallRecordsInfo> getCallRecordsInfo(String phoneNo, String virtualNo,
			List<Integer> calltypes, String lastTime, String sortorder, Integer flag,
			Integer page, Integer size) {
		Map<String, Object> index = new HashMap<String, Object>();
		if (phoneNo != null) {
			index.put("phoneno", phoneNo);
		}
		if (virtualNo != null) {
			index.put("virtualno", virtualNo);
		}
		if (calltypes != null) {
			index.put("calltypes", calltypes);
		}
		if (lastTime != null) {
			index.put("lasttime", lastTime);
		}
		index.put("flag", flag);
		index.put("sortorder", sortorder);
		if(page != null && size != null) {
			index.put("minrownum", (page-1)*size + 1);
			index.put("maxrownum", page*size);
		}
		
		return commonDao.selectInfo("BillsInfo.selectCallRecordsInfo", index);
	}

	/**
	 * 更新
	 * */
	public void updateBillsInfo(String phoneNo, String virtualNo, List<Long> ids, Integer flag, List<Integer> calltypes) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);
		index.put("ids", ids);
		index.put("flag", flag);
		index.put("calltypes", calltypes);
		
		commonDao.selectInfo("BillsInfo.updateBillsInfo", index);
	}

	/**
	 * 增加未接来电账单
	 * */
	public int addMissedCallBillsInfo(String callerno, String virtualno,
			Integer month, String starttime) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("callerno", callerno);
		index.put("virtualno", virtualno);
		index.put("month", month);
		index.put("starttime", starttime);
		
		return commonDao.insertInfo("BillsInfo.insertMissedCallBillsInfo", index);
	}
}
