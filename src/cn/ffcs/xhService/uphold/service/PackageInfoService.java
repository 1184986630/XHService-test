package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.PackageInfo;

/**
 * 套餐接口
 * */
public interface PackageInfoService {
	public void deletePackageInfo(String salesid);
	
	public void addPackageInfo(List<PackageInfo> list);
	
	public List<PackageInfo> getPackageInfo(String salesid, String sortorder);
	
	public List<PackageInfo> getPackageInfoByIds(List<String> salesids); 
	
	public void addHisPackageInfos(List<PackageInfo> list);
	
	public void packageSyn(List<PackageInfo> newPackagelist);
}
