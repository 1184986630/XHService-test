package cn.ffcs.xhService.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.ffcs.xhService.model.EResponse;
import cn.ffcs.xhService.model.Message;
import cn.ffcs.xhService.uphold.model.RevenueSharingResponse;

@Controller
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());

	@Value("${ordersharesinfos}")
	private String ordersharesinfos;
    private JSONObject json;

	/**
	 * 获取订单分账信息
	 * 
	 * @author dawn
	 * */
	@RequestMapping(value = { "/tianyimall/order/revenuesharing" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse getrevenuesharing(
			HttpServletRequest request,
			@RequestParam(value = "orderid", required = false) String orderid,
			@RequestParam(value = "paymentcode", required = false) String paymentcode) {
		EResponse eresp = new EResponse();
		eresp.setCode(0);
		eresp.setDescription("操作成功!");
		eresp.setErrorDescription("操作成功");
		RevenueSharingResponse rsrponse = new RevenueSharingResponse();
		String ordersharesinfo = ordersharesinfos;
	    json = JSONObject.parseObject(ordersharesinfo);

		switch (paymentcode) {
		case "111":
			rsrponse.setMerchantCode(json.getJSONObject("111").getString(
					"merchantCode"));
			break;
		case "114":
			rsrponse.setMerchantCode(json.getJSONObject("114").getString(
					"merchantCode"));
			break;
		case "118":
			rsrponse.setMerchantCode(json.getJSONObject("118").getString(
					"merchantCode"));
			break;
		}
		eresp.setDataObject(rsrponse);
		return eresp;
	}

	/**
	 * 
	 * @param request
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = { "/tianyimall/order/create/uni3" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message uni3(HttpServletRequest request) {
		Message m = new Message();
		m.setResult("0");
		m.setDetail("成功.");
		return m;
	}

	/**
	 * 小号订购
	 * */
	@RequestMapping(value = { "/order/xiaohao/subscribe" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message subscribe(HttpServletRequest request,
			@RequestParam(value = "orderId", required = true) String orderId) {
		System.out.println(orderId);
		Message m = new Message();
		m.setResult("0");
		m.setDetail("成功.");
		return m;
	}

	/**
	 * 小号退订
	 * */
	@RequestMapping(value = { "/order/xiaohao/unsubscribe" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message unsubscribe(HttpServletRequest request,
			@RequestParam(value = "orderId", required = true) String orderId) {
		System.out.println(orderId);
		Message m = new Message();
		m.setResult("0");
		m.setDetail("成功.");
		return m;
	}

	/**
	 * 小号可选包订购
	 * */
	@RequestMapping(value = { "/order/xiaohao/optpackag/subscribe" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message optpackag_subscribe(HttpServletRequest request,
			@RequestParam(value = "orderId", required = true) String orderId) {
		System.out.println(orderId);
		Message m = new Message();
		m.setResult("0");
		m.setDetail("成功.");
		return m;
	}

}
