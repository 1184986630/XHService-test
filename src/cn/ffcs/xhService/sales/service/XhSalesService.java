package cn.ffcs.xhService.sales.service;

import java.util.List;

import cn.ffcs.xhService.sales.model.XhSales;

public interface XhSalesService {
	/**
	 * 根据Id查询销售品
	 * @param sale_id
	 * @return
	 */
	public List<XhSales> getXhSalesById(String sale_id);
}
