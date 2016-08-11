package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.model.PhoneHdInfo;
public interface PhoneHdInfoService {
	
	/**
	 * 查询手机号段信息
	 * @param phoneNo 手机号段
	 * @param routingCode 路由码
	 * @param sysdate 时间
	 * @return
	 */
	public List<PhoneHdInfo> getPhoneHdInfo(String phoneNo, String routingCode, String sysdate);
	
}
