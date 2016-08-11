package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.RowCount;
import cn.ffcs.xhService.uphold.model.XhSmsInfo;

/**
 * 短信推送信息service
 * */
public interface XhSmsInfoService {
	public void addXhSmsInfo(XhSmsInfo info);
	public void addXhSmsInfos(List<XhSmsInfo> list);
	public List<XhSmsInfo> getXhSmsInfo(String virtualNo, String rno,
			String sent_date, String sms_content,String sms_id);
	public void updateXhSmsInfo(String virtualNo, String rno, String smsid);
	public List<RowCount> getXhSmsGroupInfo(String virtualNo);
}
