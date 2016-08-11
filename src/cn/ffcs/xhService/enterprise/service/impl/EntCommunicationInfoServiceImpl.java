package cn.ffcs.xhService.enterprise.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.enterprise.model.EntCommunicationInfo;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.service.EntCommunicationInfoService;

@Service("EntCommunicationInfoServiceImpl")
public class EntCommunicationInfoServiceImpl implements EntCommunicationInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加通信账单
	 * */
	public void addEntCommunicationInfo(EntCommunicationInfo info, EntDetailBillInfo billInfo) {
		commonDao.insertInfo("EntCommunicationInfo.insertEntCommunicationInfo", info);
		commonDao.insertInfo("EntDetailBillInfo.insertEntDetailBillInfo", billInfo);
	}

	/**
	 * 批量增加通信账单
	 * */
	public void addEntCommunicationInfos(List<EntCommunicationInfo> list, List<EntDetailBillInfo> billList) {
		commonDao.insertInfos("EntCommunicationInfo.insertEntCommunicationInfo", list, false);
		commonDao.insertInfos("EntDetailBillInfo.insertEntDetailBillInfo", billList, false);
	}
}
