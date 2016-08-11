package cn.ffcs.xhService.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.sales.model.XhSales;
import cn.ffcs.xhService.sales.service.XhSalesService;

@Service("XhSalesServiceImpl")
public class XhSalesServiceImpl implements XhSalesService {
	@Resource
    private CommonDao commonDao;

	public List<XhSales> getXhSalesById(String sale_id) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("sale_id", sale_id);		
		return commonDao.selectInfo("XhSales.selectXhSales", index);
	}
}
