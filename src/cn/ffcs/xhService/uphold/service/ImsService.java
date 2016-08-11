package cn.ffcs.xhService.uphold.service;

import java.util.Date;

import cn.ffcs.xhService.uphold.entity.Response;

public interface ImsService {
	public Response xhBusiOpen(String ims, String key, String telNo,
			String telNoKey, String openType, String payType,
			String closeBegin, String closeEnd, String closeType,
			String isRecord, String leavemsgType, String pointsSpId,
			Date date);

	public Response xhDial(String ims, String key, String calleeNbr, Date date);

	public Response xhClose(String ims, String key, Date date);
	
	public Response updateXhBusinessinfo(String virtualNo, String key, String closeBegin, String closeEnd,
			String closeType, String isRecord, String leavemsgType, Date date);

	public Response sendSms(String ims, String key, String calleeNbr, String content, Date date);
	
	public Response entIntf(String spid, String appid, String pwd, String type, String ims, 
			String key, String telNo, String telNoKey, String openType, 
			String payType, String closeBegin, String closeEnd, String closeType,
			String isRecord, String leavemsgType, String pointsSpId, Date date);
}
