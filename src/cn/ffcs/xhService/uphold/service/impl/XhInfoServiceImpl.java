package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.uphold.model.XhInfo;
import cn.ffcs.xhService.uphold.service.XhInfoService;

@Service("XhInfoServiceImpl")
public class XhInfoServiceImpl implements XhInfoService {
	
	@Resource
    private CommonDao commonDao;
	
	@Resource
	private RedisDao redisDao;
	
	/**
	 * 修改小号信息
	 * */
	public void updateXhInfo(String phoneNo, String virtualNo, String effectTime, String endTime, String logoffTime,
			Integer SMSAmount, Integer closeStatus, String closeBegin, String closeEnd,
			String weekday, String statusUpdateTime) {
		Map<String, Object> index = new HashMap<String, Object>();
		if (phoneNo != null) {
			index.put("phoneno", phoneNo);
		}
		if (virtualNo != null) {
			index.put("virtualno", virtualNo);
		}
		if (effectTime != null) {
			index.put("effecttime", effectTime);
		}
		if (endTime != null) {
			index.put("endtime", endTime);
		}
		if (logoffTime != null) {
			index.put("logofftime", logoffTime);
		}
		if (SMSAmount != null) {
			index.put("remainsms", SMSAmount);
		}
		if (closeStatus != null) {
			index.put("closestatus", closeStatus);
		}
		if (closeBegin != null) {
			index.put("closebegin", closeBegin);
		}
		if (closeEnd != null) {
			index.put("closeend", closeEnd);
		}
		if (weekday != null) {
			index.put("weekday", weekday);
		}
		if (statusUpdateTime != null) {
			index.put("statusupdatetime", statusUpdateTime);
		}
        
        commonDao.updateInfo("XhInfo.updateXhInfo", index);
	}
	
	/**
	 * 增加小号信息
	 * */
	public void addXhInfo(XhInfo info) {
		commonDao.insertInfo("XhInfo.insertXhInfo", info);
	}

	/**
	 * 查询小号信息
	 * (minDateTime,maxDateTime]时间范围
	 * */
	public List<XhInfo> getXhInfo(String phoneNo, String virtualNo, String logoffTime, 
			String minDateTime, String maxDateTime, String closeBegin, String closeEnd,
			String sysdate, String sortorder, Integer pagenumber, Integer pagesize) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(phoneNo != null) {
			index.put("phoneno", phoneNo);
		}
		if(virtualNo != null) {
			index.put("virtualno", virtualNo);
		}
		if(logoffTime != null) {
			index.put("logofftime", logoffTime);
		}
		if(minDateTime != null) {
			index.put("mindatetime",minDateTime);
		}
		if(maxDateTime != null) {
			index.put("maxdatetime", maxDateTime);
		}
		if(closeBegin != null) {
			index.put("closebegin", closeBegin);
		}
		if(closeEnd != null) {
			index.put("closeend", closeEnd);
		}
		if(sysdate != null) {
			index.put("sysdate", sysdate);
		}
		if(sortorder != null) {
			index.put("sortorder", sortorder);
		}
		if(pagenumber != null && pagesize != null) {
			index.put("minrownum", (pagenumber-1) * pagesize + 1);
			index.put("maxrownum", pagenumber * pagesize);
		}

        return commonDao.selectInfo("XhInfo.selectXhInfo", index);
	}

	/**
	 * 删除小号信息，同时搬到历史表
	 * */
	public void deleteXhInfo(XhInfo info) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(info == null) {
			return;
		}
		index.put("phoneno", info.getPhoneNo());
		index.put("virtualno", info.getVirtualNo());
        
        commonDao.deleteInfo("XhInfo.deleteXhInfo", index);
        commonDao.insertInfo("HisXhInfo.insertHisXhInfo", info);
	}

	/**
	 * 查询需要设置开关机的小号：未过期&&有设置开关机&&开机状态&&今天没有手动修改过开关机状态
	 * */
	public List<XhInfo> getCloseSettingInfo(String minDateTime, String closeStatus, String updateStatusDate) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(minDateTime != null) {
			index.put("mindatetime",minDateTime);
		}
		if(closeStatus != null) {
			index.put("closestatus",closeStatus);
		}
		if(updateStatusDate != null) {
			index.put("updatestatusdate",updateStatusDate);
		}

        return commonDao.selectInfo("XhInfo.selectCloseSettingInfo", index);
	}
	
	/**
	 * 增加小号历史信息
	 * */
	public void addHisXhInfo(XhInfo info) {
		commonDao.insertInfo("HisXhInfo.insertHisXhInfo", info);
	}

	
	/**
	 * 查询注销小号业务的用户信息
	 * */
	public List<XhInfo> getHisXhInfo(String phoneNo,
			String virtualNo, String sortorder, Integer pagenumber, Integer pagesize) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(phoneNo != null) {
			index.put("phoneno",phoneNo);
		}
		if(virtualNo != null) {
			index.put("virtualno",virtualNo);
		}
		if(sortorder != null) {
			index.put("sortorder",sortorder);
		}
		if(pagenumber != null && pagesize != null) {
			index.put("minrownum", (pagenumber-1) * pagesize + 1);
			index.put("maxrownum", pagenumber * pagesize);
		}

        return commonDao.selectInfo("HisXhInfo.selectHisXhInfo", index);
	}

	/**
	 * 统计满足条件的小号信息
	 * */
	public int getXhInfoCount(String phoneNo, String virtualNo) {
		Map<String, Object> index = new HashMap<String, Object>();
        index.put("phoneno", phoneNo);
        index.put("virtualno", virtualNo);

        return commonDao.selectInfoCount("XhInfo.selectXhInfoCount", index);
	}

	/**
	 * 统计满足条件的小号历史信息
	 * */
	public int getHisXhInfoCount(String phoneNo, String virtualNo) {
		Map<String, Object> index = new HashMap<String, Object>();
        index.put("phoneno", phoneNo);
        index.put("virtualno", virtualNo);

        return commonDao.selectInfoCount("HisXhInfo.selectHisXhInfoCount", index);
	}

	/**
	 * 查询需要销号提醒的小号信息
	 * */
	public List<XhInfo> getCloseNoticeInfo(String sysdate, String beginTime,
			String endTime) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(sysdate != null) {
			index.put("sysdate",sysdate);
		}
		if(beginTime != null) {
			index.put("begintime",beginTime);
		}
		if(endTime != null) {
			index.put("endtime",endTime);
		}

        return commonDao.selectInfo("XhInfo.selectXhCloseNoticeInfo", index);
	}

	/**
	 * 查询过期的小号信息
	 * */
	public List<XhInfo> getXhExpireInfo(String nowdate, Integer closestatus) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(nowdate != null) {
			index.put("nowdate",nowdate);
		}
		if(closestatus != null) {
			index.put("closestatus",closestatus);
		}

        return commonDao.selectInfo("XhInfo.selectXhExpireInfo", index);
	}

	/**
	 * 更新过期小号的状态
	 * */
	public void updateExpireCloseStatus(Integer closestatus, String statusupdatetime, String nowdate) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(closestatus != null) {
			index.put("closestatus",closestatus);
		}
		if(statusupdatetime != null) {
			index.put("statusupdatetime",statusupdatetime);
		}
		if(nowdate != null) {
			index.put("nowdate",nowdate);
		}
		commonDao.updateInfo("XhInfo.updateExpireCloseStatus", index);
	}

	/**
	 * 查询过期一天小号信息
	 * */
	public List<XhInfo> getExpireOneDayInfo(String nowdate) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("nowdate", nowdate);
		return commonDao.selectInfo("XhInfo.selectXhExpireOneDayInfo", index);
	}

}
