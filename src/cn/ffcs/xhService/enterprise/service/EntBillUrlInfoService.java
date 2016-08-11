package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.model.EntBillUrlInfo;

public interface EntBillUrlInfoService {
	public void addEntBillUrlInfo(EntBillUrlInfo info);
	public void addEntBillUrlInfos(List<EntBillUrlInfo> list);
	public List<EntBillUrlInfo> getEntBillUrlInfo(String url, Integer flag, String sortorder);
}
