package cn.ffcs.xhService.uphold.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.IvrInfo;
import cn.ffcs.xhService.uphold.service.IvrInfoService;
import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.DateToolkit;

@Service("IvrInfoServiceImpl")
public class IvrInfoServiceImpl implements IvrInfoService {
	
	@Resource
    private CommonDao commonDao;
	
	/**
	 * 增加主叫号码推送信息
	 * */
	public void addIvrInfo(IvrInfo info) {
		commonDao.insertInfo("IvrInfo.insertIvrInfo", info);
		if(info.getState() != null && info.getState().equals(Constants.IVR_STATE_CLOSED)) { // 小号已关机，增加未接来电
			Map<String, Object> index = new HashMap<String, Object>();
			index.put("callerno", info.getCallerno());
			index.put("virtualno", info.getCalledno());
			Date createtime = DateToolkit.parseDateFromStr(info.getCreatetime(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
			Integer month = Integer.valueOf(DateToolkit.pareseDate(createtime, DateToolkit.YYYYMM));
			index.put("month", month);
			index.put("starttime", info.getCreatetime());
			
			commonDao.insertInfo("BillsInfo.insertMissedCallBillsInfo", index);
		}
	}
	
	/**
	 * 增加主叫号码推送信息
	 * */
	public void addIvrInfos(List<IvrInfo> list) {
		commonDao.insertInfos("IvrInfo.insertIvrInfo", list, false);
	}
}
