package cn.ffcs.xhService.uphold.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.HttpStatusInfo;
import cn.ffcs.xhService.uphold.service.HttpStatusInfoService;

@Service("HttpStatusInfoServiceImpl")
public class HttpStatusInfoServiceImpl implements HttpStatusInfoService {
	
	@Resource
    private CommonDao commonDao;

	/**
	 * 增加Http状态推送信息
	 * */
	public void addHttpStatusInfo(HttpStatusInfo info) {
		commonDao.insertInfo("HttpStatusInfo.insertHttpStatusInfo", info);
	}
	
	/**
	 * 增加Http状态推送信息
	 * */
	public void addHttpStatusInfos(List<HttpStatusInfo> list) {
		commonDao.insertInfos("HttpStatusInfo.insertHttpStatusInfo", list, false);
	}
}
