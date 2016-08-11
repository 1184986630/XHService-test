package cn.ffcs.xhService.uphold.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.SmsStatusInfo;
import cn.ffcs.xhService.uphold.service.SmsStatusInfoService;

@Service("SmsStatusInfoServiceImpl")
public class SmsStatusInfoServiceImpl implements SmsStatusInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加HTTP短信发送状态推送信息
	 * */
	public void addSmsStatusInfo(SmsStatusInfo info) {
		commonDao.insertInfo("SmsStatusInfo.insertSmsStatusInfo", info);
	}
	
	/**
	 * 批量增加HTTP短信发送状态推送信息
	 * */
	public void addSmsStatusInfos(List<SmsStatusInfo> list) {
		commonDao.insertInfos("SmsStatusInfo.insertSmsStatusInfo", list, false);
	}
}
