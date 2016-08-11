package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.MessageInfo;
import cn.ffcs.xhService.uphold.service.MessageInfoService;

@Service("MessageInfoServiceImpl")
public class MessageInfoServiceImpl implements MessageInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加留言信息
	 * */
	public void addMessageInfo(MessageInfo info) {
		commonDao.insertInfo("MessageInfo.insertMessageInfo", info);
	}

	/**
	 * 修改留言信息
	 * */
	public void updateMessageStatus(String messageid, String phoneNo, String virtualNo, String isread) {
		Map<String, Object> index = new HashMap<String, Object>();
		if (messageid != null) {
			index.put("messageid", messageid);
		}
		if (phoneNo != null) {
			index.put("phoneno", phoneNo);
		}
		if (virtualNo != null) {
			index.put("virtualno", virtualNo);
		}
		if (isread != null) {
			index.put("isread", isread);
		}
        
        commonDao.updateInfo("MessageInfo.updateMessageInfo", index);
	}

	/**
	 * 留言信息查询
	 * */
	public List<MessageInfo> getMessageInfo(String phoneNo, String virtualNo, String callNo, Integer isread) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(phoneNo != null) {
			index.put("phoneno", phoneNo);
		}
		if(virtualNo != null) {
			index.put("virtualno", virtualNo);
		}
		if(callNo != null) {
			index.put("callno", callNo);
		}
		if(isread != null) {
			index.put("isread", isread);
		}

        return commonDao.selectInfo("MessageInfo.selectMessageInfo", index);
	}

	/**
	 * 批量增加留言
	 * */
	public void addMessageInfos(List<MessageInfo> info) {
		commonDao.insertInfos("MessageInfo.insertMessageInfo", info, false);
	}
	
	/**
	 * 更新留言表的主号号码
	 * */
	public void updateMessageInfoPhoneno(String starttime, String endtime) {
		Map<String, Object> index = new HashMap<String, Object>();
		if (starttime != null) {
			index.put("starttime", starttime);
		}
		if (endtime != null) {
			index.put("endtime", endtime);
		}
        
        commonDao.updateInfo("MessageInfo.updateMessageInfoPhoneno", index);
	}
}
