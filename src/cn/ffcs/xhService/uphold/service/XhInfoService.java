package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.XhInfo;

public interface XhInfoService {
	public void updateXhInfo(String phoneNo, String virtualNo, String effectTime, String endTime, String logoffTime,
			Integer SMSAmount, Integer closeStatus, String closeBegin, String closeEnd,
			String weekday, String statusUpdateTime);
	public void addXhInfo(XhInfo info);
	public List<XhInfo> getXhInfo(String phoneNo, String virtualNo, String logoffTime, String minDateTime, String maxDateTime,
			String closeBegin, String closeend, String sysdate, String sortorder, Integer pagenumber, Integer pagesize);
	public void deleteXhInfo(XhInfo xhinfo);
	public List<XhInfo> getCloseSettingInfo(String minDateTime, String closeStatus, String updateStatusDate);
	
	public void addHisXhInfo(XhInfo info);
	public List<XhInfo> getHisXhInfo(String phoneNo, String virtualNo, String sortorder, Integer pagenumber, Integer pagesize);
	
	public int getXhInfoCount(String phoneNo, String virtualNo);
	public int getHisXhInfoCount(String phoneNo, String virtualNo);
	
	public List<XhInfo> getCloseNoticeInfo(String sysdate, String beginTime, String endTime);
	public List<XhInfo> getXhExpireInfo(String nowdate, Integer closestatus);
	public void updateExpireCloseStatus(Integer closestatus, String statusupdatetime, String nowdate);
	public List<XhInfo> getExpireOneDayInfo(String nowdate);
}
