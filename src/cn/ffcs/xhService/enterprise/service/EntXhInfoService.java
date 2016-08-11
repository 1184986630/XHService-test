package cn.ffcs.xhService.enterprise.service;

import cn.ffcs.xhService.enterprise.model.EntXhInfo;

import java.util.List;


public interface EntXhInfoService {
	public void addEntXhInfo(EntXhInfo info);
	public List<EntXhInfo> getEntXhInfo(String spid, String appid, String phoneNo, String virtualNo,
		String sortorder, Integer pagenumber, Integer pagesize);
	public int getEntXhInfoCount(String spid, String appid, String phoneNo, String virtualNo);
	public void deleteEntXhInfo(EntXhInfo info);
}
