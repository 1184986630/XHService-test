package cn.ffcs.xhService.enterprise.service;

import java.util.List;

import cn.ffcs.xhService.enterprise.model.EntCommunicationInfo;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;

/**
 * 企业通信service
 * */
public interface EntCommunicationInfoService {
	public void addEntCommunicationInfo(EntCommunicationInfo info, EntDetailBillInfo billInfo);
	public void addEntCommunicationInfos(List<EntCommunicationInfo> list, List<EntDetailBillInfo> billList);
}
