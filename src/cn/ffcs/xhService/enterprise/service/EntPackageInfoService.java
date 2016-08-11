package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.model.EntPackageInfo;

public interface EntPackageInfoService {
	public void addEntPackageInfo(EntPackageInfo info);
	public List<EntPackageInfo> getEntPackageInfo(String pknbr);
	public int getEntPackageInfoCount(String pknbr);
}
