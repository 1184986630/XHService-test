package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.HttpStatusInfo;

/**
 * Http状态推送service
 * */
public interface HttpStatusInfoService {
	public void addHttpStatusInfo(HttpStatusInfo info);
	public void addHttpStatusInfos(List<HttpStatusInfo> list);
}
