package cn.ffcs.xhService.sales.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.sales.service.XhProductService;

@Service("XhProductServiceImpl")
public class XhProductServiceImpl implements XhProductService {
	@Resource
    private CommonDao commonDao;

	@Override
	public int getPriceByIds(List<String> ids) {
		Map<String, Object> index = new HashMap<String, Object>();
		index.put("ids", ids);
        return commonDao.selectInfoCount("XhProduct.getPriceByIds", index);
	}
}
