package cn.ffcs.xhService.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.enterprise.model.EntXhInfo;
import cn.ffcs.xhService.enterprise.service.EntInfoService;
import cn.ffcs.xhService.enterprise.service.EntXhInfoService;
import cn.ffcs.xhService.uphold.model.XhInfo;
import cn.ffcs.xhService.uphold.service.XhInfoService;
import cn.ffcs.xhService.uphold.service.XhSmsInfoService;

/**
 * redis相关操作
 * */
@Component(value="RedisOperationUtils")
public class RedisOperationUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	
	@Resource
	private RedisDao redisDao;
	
	@Resource(name = "XhInfoServiceImpl")
	private XhInfoService xhInfoService;
	
	@Resource(name = "EntInfoServiceImpl")
	private EntInfoService entInfoService;
	
	@Resource(name = "EntXhInfoServiceImpl")
	private EntXhInfoService entXhInfoService;
	
	@Resource(name = "XhSmsInfoServiceImpl")
	private XhSmsInfoService xhSmsInfoService;
	
	/**
	 * 获取小号信息
	 * @param phoneno 查询的主号
	 * @param virtualno 查询的小号
	 * @param sysdate 当前系统时间
	 * */
	public List<XhInfo> getXhInfo(String phoneno, String virtualno, String sysdate) {
		if(StringUtils.isEmpty(phoneno) || StringUtils.isEmpty(sysdate)) {
			return null;
		}
		
		List<XhInfo> xhList = new ArrayList<XhInfo>();
		String redisValue = null;
		XhInfo xhInfo = null; // 小号信息
		
		String key = Constants.getRedisXhInfo() + phoneno;
		// 先在redis中查
		try {
			redisValue = redisDao.get(key);
		} catch (Exception e) {
			logger.error("小号信息查询，redis查询失败，key=" + key, e);
		}
		
		if(StringUtils.isEmpty(redisValue)) { // 查不到,查数据库
			List<XhInfo> list = null;
			try {
				list = xhInfoService.getXhInfo(phoneno, virtualno, null, null, null, null, null, sysdate, null, null, null);
			} catch (Exception e) {
				logger.error("小号信息查询失败. phoneno=" + phoneno + ", virtualno=" + virtualno, e);
				return null;
			}
			if(list == null || list.size() == 0) {
				return null;
			}
			xhInfo = list.get(0);
			try {
				// 插入redis
				redisDao.set(key, xhInfo.toJSONString());
				// 设置过期时长
				Long exp = DateToolkit.calExpireTime(sysdate, DateToolkit.YYYY_MM_DD_HH24_MM_SS, xhInfo.getEffectTime(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
				redisDao.expireAt(key, exp, TimeUnit.SECONDS);
			} catch (Exception e) {
				logger.error("小号信息查询，redis增加失败，key=" + key + ", xhInfo=" + xhInfo.toJSONString(), e);
			}
		} else {
			try {
				xhInfo = (XhInfo)XhInfo.fromJSONString(redisValue);
			} catch (Exception e) {
				logger.error("小号信息，redis数据转换失败，redisValue=" + redisValue, e);
				return null;
			}
		}
		
		xhList.add(xhInfo);
		
		return xhList;
	}
	
	/**
     * 获取redis中的企业小号信息
     * */
    public EntXhInfo getRedisEntXhInfo(String spid, String appid, String virtualno) {
    	EntXhInfo info = null;
    	
    	if(StringUtils.isEmpty(virtualno)) {
    		return null;
    	}
    	String key = Constants.getRedisEntXhInfo()+virtualno;
    	// 先在redis中查
    	String redisValue = null;
    	try {
    		redisValue = redisDao.get(key);
		} catch (Exception e) {
			logger.error("企业小号信息查询，redis查询失败，key=" + key, e);
		}
    	if(StringUtils.isEmpty(redisValue)) { // 查不到，查数据库
    		List<EntXhInfo> entXhInfoList = null;
    		try {
    			entXhInfoList = entXhInfoService.getEntXhInfo(spid, appid, null, virtualno, null, null, null);
    		} catch (Exception e) {
				logger.error("企业小号信息查询失败, spid=" + spid + ", appid=" + appid + ", virtualno=" + virtualno, e);
				return null;
			}
    		if(entXhInfoList == null || entXhInfoList.size() == 0) {
    			return null;
    		}
    		info = entXhInfoList.get(0);
    		// 插入redis
    		try {
    			redisDao.set(key, info.toJSONString());
    		} catch (Exception e) {
    			logger.error("企业小号信息查询，redis增加失败，key=" + key + ", entXhInfo=" + info.toJSONString(), e);
    		}
		} else {
			try {
				info = (EntXhInfo) EntXhInfo.fromJSONString(redisValue);
			} catch (Exception e) {
				logger.error("企业小号信息，redis数据转换失败，redisValue=" + redisValue, e);
			}
		}
    	
    	return info;
    }
    
    /**
     * 获取redis中的企业信息
     * */
    /*public EntInfo getRedisEntInfo(String spid, String appid) {
    	EntInfo info = null;
    	
    	if(StringUtils.isEmpty(spid) || StringUtils.isEmpty(appid)) {
    		return null;
    	}
    	String key = Constants.getRedisEntInfo()+spid+Constants.REDISXHINFOKEYSPLIT+appid;
    	String redisValue = null;
    	try {
    		redisValue = redisDao.get(key);
		} catch (Exception e) {
			logger.error("企业信息查询，redis查询失败，key=" + key, e);
		}
    	if(StringUtils.isEmpty(redisValue)) { // 查不到，查数据库
    		List<EntInfo> entInfoList = null;
    		try {
    			entInfoList = entInfoService.getEntInfo(spid, appid);
    		} catch (Exception e) {
				logger.error("企业信息查询失败, spid=" + spid + ", appid=" + appid, e);
				return null;
			}
			if(entInfoList == null || entInfoList.size() == 0) {
				return null;
			}
			info = entInfoList.get(0);
			// 插入redis
			try {
				redisDao.set(key, info.toJSONString());
			} catch (Exception e) {
				logger.error("企业信息查询，redis增加失败，key=" + key + ", entInfo=" + info.toJSONString(), e);
			}
		} else {
			try {
	    		info = (EntInfo) EntInfo.fromJSONString(redisValue);
			} catch (Exception e) {
				logger.error("企业信息，redis数据转换失败，redisValue=" + redisValue, e);
			}
		}
    	
    	return info;
    }*/
    
    /**
     * 是否存在个人主号key
     * */
    public boolean existsPhone(String phoneno) {
    	boolean flag = false;
    	String key = Constants.getRedisPhoneVirtual() + phoneno;
    	try {
    		flag = redisDao.exists(key);
		} catch (Exception e) {
			logger.error("是否存在个人主号key查询，redis查询失败，key=" + key, e);
		}
    	if(!flag) { // 查不到再查数据库
    		List<XhInfo> xhList = null;
    		try {
    			xhList = xhInfoService.getXhInfo(phoneno, null, null, null, null, null, null, null, null, null, null);
    		} catch (Exception e) {
				logger.error("查询小号信息失败, phoneno=" + phoneno, e);
			}
    		if(xhList == null || xhList.size() == 0) {
    			flag = false;
    		} else {
    			flag = true;
    			// 插入redis
    			try {
    				redisDao.set(key, xhList.get(0).getVirtualNo());
    			} catch (Exception e) {
    				logger.error("是否存在个人主号key查询，redis增加失败，key=" + key + ", virtualno=" + xhList.get(0).getVirtualNo(), e);
    			}
    		}
    	}
    	return flag;
    }
    
    /**
     * 获取redis中个人主号对应的小号
     * */
    public String getRedisPhoneVirtual(String phoneno) {
    	String virtualno = "";
    	
    	if(StringUtils.isEmpty(phoneno)) {
    		return "";
    	}
    	String key = Constants.getRedisPhoneVirtual()+phoneno;
    	try {
    		virtualno = redisDao.get(key);
		} catch (Exception e) {
			logger.error("个人主号对应的小号查询，redis查询失败，key=" + key, e);
		}
    	if(StringUtils.isEmpty(virtualno)) { // 查不到，查数据库
    		List<XhInfo> xhInfoList = null;
    		try {
    			xhInfoList = xhInfoService.getXhInfo(phoneno, null, null, null, null, null, null, null, null, null, null);
    		} catch (Exception e) {
				logger.error("小号信息查询失败, phoneno=" + phoneno, e);
				return "";
			}
			if(xhInfoList == null || xhInfoList.size() == 0) {
				return "";
			}
			virtualno = xhInfoList.get(0).getVirtualNo();
			// 插入redis
			try {
				redisDao.set(key, virtualno);
			} catch (Exception e) {
				logger.error("个人主号对应的小号查询，redis增加失败，key=" + key + ", virtualno=" + virtualno, e);
			}
		}
    	
    	return virtualno;
    }
    
    /**
     * 获取redis中个人小号对应的主号
     * */
    public String getRedisVirtualPhone(String virtualno) {
    	String phoneno = "";
    	
    	if(StringUtils.isEmpty(virtualno)) {
    		return null;
    	}
    	String key = Constants.getRedisPhoneVirtual()+virtualno;
    	try {
    		phoneno = redisDao.get(key);
		} catch (Exception e) {
			logger.error("个人小号对应的主号查询，redis查询失败，key=" + key, e);
		}
    	if(StringUtils.isEmpty(phoneno)) { // 查不到，查数据库
    		List<XhInfo> xhInfoList = null;
    		try {
    			xhInfoList = xhInfoService.getXhInfo(null, virtualno, null, null, null, null, null, null, null, null, null);
    		} catch (Exception e) {
				logger.error("小号信息查询失败, phoneno=" + phoneno, e);
				return "";
			}
			if(xhInfoList == null || xhInfoList.size() == 0) {
				return "";
			}
			phoneno = xhInfoList.get(0).getPhoneNo();
			// 插入redis
			try {
				redisDao.set(key, phoneno);
			} catch (Exception e) {
				logger.error("个人小号对应的主号查询，redis增加失败，key=" + key + ", phoneno=" + phoneno, e);
			}
		}
    	
    	return phoneno;
    }
    
}