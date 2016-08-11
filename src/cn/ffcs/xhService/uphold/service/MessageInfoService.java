package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.MessageInfo;

public interface MessageInfoService {
	public void addMessageInfo(MessageInfo info);
	public void addMessageInfos(List<MessageInfo> info);
	public void updateMessageStatus(String messageid, String phoneNo, String virtualNo, String isread);
	public List<MessageInfo> getMessageInfo(String phoneNo, String virtualNo, String callNo, Integer isread);
	public void updateMessageInfoPhoneno(String starttime, String endtime);
}
