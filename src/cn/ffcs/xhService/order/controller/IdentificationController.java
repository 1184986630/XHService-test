package cn.ffcs.xhService.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.xhService.model.EResponse;
import cn.ffcs.xhService.uphold.entity.RequestInfo;
import cn.ffcs.xhService.uphold.model.IdentificationResponse;
import cn.ffcs.xhService.uphold.model.UpNameRequest;
import cn.ffcs.xhService.utils.HttpclientUtils;

@Controller
public class IdentificationController {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	/**
	 * OCR识别身份证信息（天翼小白）
	 * */
	@RequestMapping(value = { "/tianyimall/basedata/analyse/p1" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse analyse(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "imgtype", required = true) String imgtype,
			@RequestParam(value = "orderid", required = true) String orderid) {
		EResponse finalResp = new EResponse();
		IdentificationResponse idResponse = new IdentificationResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		finalResp.setDataObject(idResponse);
		return finalResp;
	}
	
	/**
	 *修改OCR识别的身份证姓名
	 * */
	@RequestMapping(value = { "/tianyimall/basedata/upName" }, method = { RequestMethod.POST })
	@ResponseBody
	public void upName(HttpServletRequest request) {
		String json;
		json = HttpclientUtils.getPostBody(request);
		if(json == null) {
			logger.error("JSON IS NULL.");
		}
		
		RequestInfo requestInfo = null;
		UpNameRequest upname = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			upname = (UpNameRequest) UpNameRequest.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);
		}
		String id = upname.getId(); 
		String name = upname.getName();
		String orderid = upname.getOrderid(); 
	}
	
	
}
