package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.entity.EntSettleBillInfo;
import cn.ffcs.xhService.enterprise.model.EntBillInfo;

public interface EntBillInfoService {
	public void addEntBillInfo(EntBillInfo info);
	public int getEntBillInfoCount(String spid, String appid, String phoneno, String virtualno,
			List<Integer> callTypes, Integer billtype, String curday, String endday,
			String startmonth, String endmonth);
	public List<EntSettleBillInfo> getEntBillInfo(String spid, String appid, String phoneno, String virtualno,
			List<Integer> callTypes, Integer billtype, String curday, String endday,
			String startmonth, String endmonth);
}
