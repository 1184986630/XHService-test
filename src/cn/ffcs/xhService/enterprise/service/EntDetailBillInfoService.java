package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;

public interface EntDetailBillInfoService {
	public void addEntDetailBillInfo(EntDetailBillInfo info);
	public void addEntDetailBillInfos(List<EntDetailBillInfo> list);
	public List<EntDetailBillInfo> getEntDetailBillInfo(String spid, String appid, String phoneno,
			String virtualno, List<Integer> callTypes, String month, Integer page,
			Integer size, String sortorder);
	public int getEntDetailBillInfoCount(String spid, String appid, String phoneNo, String virtualNo, List<Integer> callTypes, String month);
}
