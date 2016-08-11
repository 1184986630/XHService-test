package cn.ffcs.xhService.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.xhService.model.EResponse;
import cn.ffcs.xhService.order.model.XhOrderInfo;
import cn.ffcs.xhService.order.service.XhOrderInfoService;
import cn.ffcs.xhService.sales.model.XhSales;
import cn.ffcs.xhService.sales.service.XhSalesService;
import cn.ffcs.xhService.uphold.entity.PayResponse;
import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.GetConfig;

import com.alibaba.fastjson.JSONArray;

@Controller
public class PayController {
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	
	@Value("${PayMethodInfo}")
	private String PayMethodInfo;
	
	@Resource(name = "XhOrderInfoServiceImpl")
	private XhOrderInfoService xhOrderInfoService;
	
	@Resource(name = "XhSalesServiceImpl")
	private XhSalesService xhSalesService;

	private static final JSONArray payInfo = JSONArray
			.parseArray(GetConfig.PAY_METHOD_iNFO);
	/**
	 * 获取支付反馈地址
	 * */
	@RequestMapping(value = { "/tianyimall/payresaddress" }, method = { RequestMethod.GET })
	@ResponseBody 
	public EResponse getpayresaddress(HttpServletRequest request,
			@RequestParam(value = "orderid", required = false) String orderid,
			@RequestParam(value = "platformId", required = false) String platformId) {
		EResponse eresp = new EResponse();
		eresp.setCode(0);
		eresp.setDescription("获取成功!");
		eresp.setErrorDescription("获取成功!");
		String [] addresses = Constants.addresses;
		String address = "";
		switch(platformId){
		case "0":
			address = addresses[0];
			break;
		case "111":
			address = addresses[1];
			break;
		case "114":
			address = addresses[2];
			break;
		case "118":
			address = addresses[4];
			break;
		}
		eresp.setDataObject(address);
		return eresp;
	}
	/**
	 * 获取销售品支付方式
	 * */
	@RequestMapping(value = { "/tianyimall/basedata/paymethod" }, method = { RequestMethod.GET })
	@ResponseBody 
	public PayResponse getpaymethod(HttpServletRequest request,
			@RequestParam(value = "salesproductid", required = false) String salesproductid,
			@RequestParam(value = "orderid", required = false) String orderid,
			@RequestParam(value = "deliveryid", required = false) String deliveryid,
			@RequestParam(value = "userid", required = false) String userid) {
		PayResponse resp = new PayResponse();
		resp.setCode(0);
		resp.setErrorDescription("操作成功!");
		JSONArray pay = JSONArray.parseArray(PayMethodInfo);
		resp.setDataObject(pay);
		return resp;
	}
	/**
	 * 将订单状态设置为"支付中"状态
	 * */
	@RequestMapping(value = { "/tianyimall/order/payingstatus" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse setPayingStatus(
			HttpServletRequest request,
			@RequestParam(value = "orderid", required = false) String orderid,
			@RequestParam(value = "payplatformid", required = false) String payplatformid) {
		EResponse resp = new EResponse();
		if (orderid == null || orderid == "0" || orderid == "") {
			resp.setCode(115);
			resp.setErrorDescription("订单不存在");
			return resp;
		}
		// 更新订单状态为"支付中"
		try {
			// TODO:支付平台id未使用
			//TODO:是否需要判断已在"支付中"的订单，返回什么？
			xhOrderInfoService.updateOrderPayStatus(orderid);
		} catch (Exception e) {
			logger.error("更新订单状态失败",e);
			resp.setCode(305);
			resp.setErrorDescription("订单状态支付中设置失败!");
			return resp;
		}
		resp.setCode(0);
		resp.setErrorDescription("操作成功!");
		return resp;
	}

	/**
	 * 获取订单超时信息
	 * */
	@RequestMapping(value = { "/tianyimall/order/timeoutinfo" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse getTimeOutInfo(HttpServletRequest request,
			@RequestParam(value = "orderid", required = false) String orderid) {
		EResponse resp = new EResponse();
		if (orderid == null || orderid == "0" || orderid == "") {
			resp.setCode(115);
			resp.setErrorDescription("订单不存在");
			return resp;
		}
		List<XhOrderInfo> list = new ArrayList<XhOrderInfo>();
		List<XhSales> list2 = new ArrayList<XhSales>();
		Map<String,Object> map = new HashMap<String,Object>();
		String saleId = null;
		String timeOutMinute = null;
		// 获取订单超时信息
		try {
			list = xhOrderInfoService.getXhOrderInfo(orderid);
			if(list != null && list.size() > 0){
				saleId = list.get(0).getSaleId();
			}
			list2 = xhSalesService.getXhSalesById(saleId);
			if(list2 != null && list2.size() > 0){
				timeOutMinute = list2.get(0).getOrder_effect_time();
			}
		} catch (Exception e) {
			logger.error("获取订单信息失败", e);
		}
		map.put("timeOutMinutes", timeOutMinute);
		resp.setCode(0);
		resp.setErrorDescription("操作成功!");
		resp.setDataObject(map);
		return resp;
	}
	
}
