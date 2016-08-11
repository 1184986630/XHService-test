package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.RowCount;
import cn.ffcs.xhService.uphold.model.XhSmsInfo;
import cn.ffcs.xhService.uphold.service.XhSmsInfoService;

@Service("XhSmsInfoServiceImpl")
public class XhSmsInfoServiceImpl implements XhSmsInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加短信推送信息
	 * */
	public void addXhSmsInfo(XhSmsInfo info) {
		commonDao.insertInfo("XhSmsInfo.insertXhSmsInfo", info);
	}
	
	/**
	 * 批量增加短信推送信息
	 * */
	public void addXhSmsInfos(List<XhSmsInfo> list) {
		commonDao.insertInfos("XhSmsInfo.insertXhSmsInfo", list, false);
	}

	@Override
	public List<XhSmsInfo> getXhSmsInfo(String virtualNo, String rno,
			String sent_date, String sms_content,String sms_id) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(virtualNo != null) {
			index.put("virtualNo", virtualNo);
		}
		if(rno != null) {
			index.put("rno", rno);
		}
			index.put("unread", 0);
        return commonDao.selectInfo("XhSmsInfo.selectXhSmsInfo", index);
	}
	/**
	 * 设置短信为已读
	 * */
	@Override
	public void updateXhSmsInfo(String virtualNo, String rno, String smsid) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("virtualNo", virtualNo);
		index.put("rno", rno);
		index.put("smsid", smsid);
		index.put("readed", 1);
        commonDao.updateInfo("XhSmsInfo.updateXhSmsInfo", index);
	}

	@Override
	public List<RowCount> getXhSmsGroupInfo(String virtualNo) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(virtualNo != null) {
			index.put("virtualNo", virtualNo);
		}
		index.put("unread", 0);
        return commonDao.selectInfo("XhSmsInfo.selectXhSmsGroupInfo", index);
	}
}
