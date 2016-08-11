package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.BillsSettleInfo;
import cn.ffcs.xhService.uphold.service.BillsSettleInfoService;

@Service("BillsSettleInfoServiceImpl")
public class BillsSettleInfoServiceImpl implements BillsSettleInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加月结账单
	 * */
	public void addBillsSettleInfo(BillsSettleInfo info) {
		commonDao.insertInfo("BillsInfo.insertBillsInfo", info);
	}

	public List<BillsSettleInfo> getBillsSettleInfo(String phoneNo, String virtualNo, Integer calltypelimit,
			Integer curMonth, Integer startMonth, Integer endMonth, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("phoneno", phoneNo);
		index.put("virtualno", virtualNo);
		index.put("curmonth", curMonth);
		index.put("startmonth", startMonth);
		index.put("endmonth", endMonth);
		index.put("sortorder", sortorder);
		index.put("calltypelimit", calltypelimit);
		
        
		return commonDao.selectInfo("BillsSettleInfo.selectBillsSettleInfo", index);
	}

	/**
	 * 执行存储过程统计月账单
	 * */
	public void updateBillsSettleInfo2Pro() {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("v_month", "");
		
		commonDao.selectInfo("BillsSettleInfo.updateProBillsSettleInfo", index);
	}
	
	
}
