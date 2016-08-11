package cn.ffcs.xhService.uphold.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.CommunicationInfo;
import cn.ffcs.xhService.uphold.service.CommunicationInfoService;

@Service("CommunicationInfoServiceImpl")
public class CommunicationInfoServiceImpl implements CommunicationInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加通信账单
	 * */
	public void addCommunicationInfo(CommunicationInfo info) {
		commonDao.insertInfo("CommunicationInfo.insertCommunicationInfo", info);
	}

	/**
	 * 批量增加通信账单
	 * */
	public void addCommunicationInfos(List<CommunicationInfo> list) {
		commonDao.insertInfos("CommunicationInfo.insertCommunicationInfo", list, false);
	}
}
