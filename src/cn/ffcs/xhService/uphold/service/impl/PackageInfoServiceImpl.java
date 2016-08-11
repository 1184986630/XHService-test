package cn.ffcs.xhService.uphold.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.PackageInfo;
import cn.ffcs.xhService.uphold.service.PackageInfoService;

@Service("PackageInfoServiceImpl")
public class PackageInfoServiceImpl implements PackageInfoService {
	
	@Resource
    private CommonDao commonDao;
	
	/**
	 * 删除套餐
	 * */
	public void deletePackageInfo(String salesid) {
		Map<String, Object> index = new HashMap<String, Object>();
		if (salesid != null) {
			index.put("salesid", salesid);
		}
        
        commonDao.updateInfo("PackageInfo.deletePackageInfo", index);
	}

	/**
	 * 增加套餐
	 * */
	public void addPackageInfo(List<PackageInfo> list) {
		commonDao.insertInfos("PackageInfo.insertPackageInfo", list, false);
	}
	
	/**
	 * 套餐查询
	 * */
	public List<PackageInfo> getPackageInfo(String salesid, String sortorder) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(salesid != null) {
			index.put("salesid", salesid);
		}
		if(sortorder != null) {
			index.put("sortorder", sortorder);
		}
		
        return commonDao.selectInfo("PackageInfo.selectPackageInfo", index);
	}
	
	/**
	 * 根据Ids批量查询套餐信息 
	 * */
	public List<PackageInfo> getPackageInfoByIds(List<String> salesids) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(salesids != null) {
			index.put("salesids", salesids);
		}
        return commonDao.selectInfo("PackageInfo.selectPackageInfo", index);
	}
	
	/**
	 * 增加历史套餐
	 * */
	public void addHisPackageInfos(List<PackageInfo> list) {
		commonDao.insertInfos("HisPackageInfo.insertHisPackageInfo", list, false);
	}
	
	/**
	 * 同步套餐
	 */
	public void packageSyn(List<PackageInfo> newPackagelist) {
		// 1.查询原套餐信息
		List<PackageInfo> packageList = new ArrayList<PackageInfo>();
		packageList = getPackageInfo(null, null);
		// 2.删除套餐表
		deletePackageInfo(null);
		// 3.将新套餐信息插入套餐表
		addPackageInfo(newPackagelist);
		// 4.旧套餐信息插入到历史表
		addHisPackageInfos(packageList);
	}
	
}
