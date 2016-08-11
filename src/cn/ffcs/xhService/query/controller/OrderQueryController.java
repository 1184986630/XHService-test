package cn.ffcs.xhService.query.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.xhService.model.Message;

@Controller
public class OrderQueryController {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	
	/**
	 * 小号在途单检查
	 * */
	@RequestMapping(value = { "/app/xiaohao/checkflag" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message checkflag(HttpServletRequest request,
			@RequestParam(value = "phone_number", required = true) String phone_number,
			@RequestParam(value = "channel_id", required = true) String channel_id) {
			Message m = new Message();
			m.setResult("0");
			m.setDetail("成功.");
			return m;
		}
	
	/**
	 * 小号资格检查
	 * */
	@RequestMapping(value = { "/pyxiaohao/database/check_qualification" }, method = { RequestMethod.GET })
	@ResponseBody
	public Message check_qualification(HttpServletRequest request,
			@RequestParam(value = "userid", required = true) String userid) {
			Message m = new Message();
			m.setResult("0");
			m.setDetail("成功.");
			return m;
		}
	/**
	 * 查询小号的归属地
	 * */
	@RequestMapping(value = { "/app/xiaohao/cellowner" }, method = { RequestMethod.GET })
	@ResponseBody
	public void cellowner(HttpServletRequest request,
			@RequestParam(value = "xiaohao", required = true) String virtualNo) {
		}
}
