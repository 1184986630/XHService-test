package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.IvrInfo;

/**
 * 主叫主叫号码推送service
 * */
public interface IvrInfoService {
	public void addIvrInfo(IvrInfo info);
	public void addIvrInfos(List<IvrInfo> list);
}
