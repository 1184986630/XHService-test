package cn.ffcs.xhService.uphold.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ffcs.xhService.core.dao.CommonDao;
import cn.ffcs.xhService.uphold.model.PhoneHdInfo;
import cn.ffcs.xhService.uphold.service.PhoneHdInfoService;

@Service("PhoneHdInfoServiceImpl")
public class PhoneHdInfoServiceImpl implements PhoneHdInfoService {
	
	@Resource
    private CommonDao commonDao;

	public List<PhoneHdInfo> getPhoneHdInfo(String phoneNo, String routingCode, String sysdate) {
		Map<String, Object> index = new HashMap<String, Object>();
		if(phoneNo != null) {
			index.put("phoneNo", phoneNo);
		}
		if(routingCode != null) {
			index.put("routingCode", routingCode);
		}
		if(sysdate != null) {
			index.put("sysdate", sysdate);
		}
        return commonDao.selectInfo("PhoneHdInfo.selectPhoneHdInfo", index);
	}

}
