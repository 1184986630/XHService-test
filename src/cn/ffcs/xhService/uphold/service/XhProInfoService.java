package cn.ffcs.xhService.uphold.service;

import java.util.List;

import cn.ffcs.xhService.uphold.entity.XhProInfo;

/**
 * 产品信息接口
 * */
public interface XhProInfoService {
	// 根据销售品id获取产品信息列表
	public List<XhProInfo> getXhProInfo(String xspid);

	// 根据产品id获取可选包信息列表
	public List<XhProInfo> getXhKxbProInfo(List<String> ids);
}
