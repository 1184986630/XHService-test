package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.CommunicationInfo;

/**
 * 通信service
 * */
public interface CommunicationInfoService {
	public void addCommunicationInfo(CommunicationInfo info);
	public void addCommunicationInfos(List<CommunicationInfo> list);
}
