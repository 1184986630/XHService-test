package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.model.EntInfo;
import cn.ffcs.xhService.enterprise.model.EntRechargeInfo;





public interface EntInfoService {
	public void addEntInfo(EntInfo info);
	public void updateEntInfo(String spid, String appid, Integer status, Integer pushStatus, Long fee, List<EntDetailBillInfo> detailList);
	public void updateEntChargeInfo(String spid, String appid, Integer status, Long fee, EntRechargeInfo info);
	public List<EntInfo> getEntInfo(String spid, String appid);
}
