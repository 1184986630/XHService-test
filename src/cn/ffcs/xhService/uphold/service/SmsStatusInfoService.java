package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.SmsStatusInfo;

/**
 * HTTP短信发送状态推送service
 * */
public interface SmsStatusInfoService {
	public void addSmsStatusInfo(SmsStatusInfo info);
	public void addSmsStatusInfos(List<SmsStatusInfo> list);
}
