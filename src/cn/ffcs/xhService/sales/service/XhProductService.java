package cn.ffcs.xhService.sales.service;

import java.util.List;

public interface XhProductService {
	/**
	 * 获取总价
	 * @param ids 产品id列表
	 * @return
	 */
	public int getPriceByIds(List<String> ids);
}
