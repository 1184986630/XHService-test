package cn.ffcs.xhService.func.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.model.EResponse;
import cn.ffcs.xhService.uphold.entity.CallRecordsRequest;
import cn.ffcs.xhService.uphold.entity.DailInfoRequest;
import cn.ffcs.xhService.uphold.entity.DataResponse;
import cn.ffcs.xhService.uphold.entity.RequestInfo;
import cn.ffcs.xhService.uphold.entity.Response;
import cn.ffcs.xhService.uphold.model.BillsInfo;
import cn.ffcs.xhService.uphold.model.XhInfo;
import cn.ffcs.xhService.uphold.service.BillsInfoService;
import cn.ffcs.xhService.uphold.service.ImsService;
import cn.ffcs.xhService.uphold.service.XhInfoService;
import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.DateToolkit;
import cn.ffcs.xhService.utils.Function;
import cn.ffcs.xhService.utils.HttpclientUtils;
import cn.ffcs.xhService.utils.RedisOperationUtils;
import cn.ffcs.xhService.utils.StringEncoding;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());
	
	
	@Resource(name = "XhInfoServiceImpl")
	private XhInfoService xhInfoService;
	@Resource(name = "BillsInfoServiceImpl")
	private BillsInfoService billsInfoService;
	@Resource
	private RedisDao redisDao;
	@Resource(name = "ImsServiceImpl")
	private ImsService imsService;
	@Resource(name = "RedisOperationUtils")
	private RedisOperationUtils redisOperationUtils;

	/**
	 * 小号直拨
	 * 
	 * @author huangjiabo
	 * */
	@RequestMapping(value = { "/spip/api/dial" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response dial_get(HttpServletRequest request) {
		String json;
		Response resp = new Response();
		
		json = HttpclientUtils.getPostBody(request);
		if (json == null) {
			logger.error("JSON IS NULL.");
			resp.setRetCode("1");
			resp.setRetMsg("请求参数异常");
			return resp;
		}
		RequestInfo requestInfo = null;
		DailInfoRequest queryInfo = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			queryInfo = (DailInfoRequest) DailInfoRequest
					.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);

			resp.setRetCode("5");
			resp.setRetMsg("请求报文不符合规范");
			return resp;
		}
		String phoneNo = queryInfo.getPhoneNo(); // 主号号码
		String virtualNo = queryInfo.getVirtualNo(); // 小号号码
		String calledNo = queryInfo.getCalledNo(); // 被叫号码
		String spid = queryInfo.getSpId(); // 企业spid

		// /// 主号号码校验
		if (StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			return resp;
		}
		if (!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			return resp;
		}
		// /// 小号号码校验
		if (StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			return resp;
		}
		if (!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			return resp;
		}
		// /// 被叫号码校验
		if (StringUtils.isNotEmpty(calledNo)
				&& calledNo.startsWith(Constants.SIGN_PLUS)) { // 去除前缀+号
			calledNo = calledNo.substring(Constants.SIGN_PLUS.length());
		}
		if (StringUtils.isNotEmpty(calledNo)
				&& calledNo.startsWith(Constants.DISPLAYNBR_86)) { // 86开头，去除86
			calledNo = calledNo.substring(Constants.DISPLAYNBR_86.length());
		}
		if (StringUtils.isEmpty(calledNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("被叫号码不能为空");
			return resp;
		}
		if (!Function.isNumeric(calledNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("被叫号码不正确");
			return resp;
		}
		// 小号打自己的小号
		if (calledNo.equals(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码与被叫号码不能相同");
			return resp;
		}
		// 小号打自己的主号
		if (calledNo.equals(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码与被叫号码不能相同");
			return resp;
		}

		// 是否拨打不允许的号码
		String[] notAllowedArray = Constants.getNotAllowedDial();
		if (notAllowedArray != null && notAllowedArray.length > 0) {
			String specialNo = Constants.getSpecialNoDial() == null ? ""
					: Constants.getSpecialNoDial(); // 允许拨打的特殊号码开头
			for (String na : notAllowedArray) {
				if (calledNo.startsWith(na) && !calledNo.startsWith(specialNo)) {
					resp.setRetCode("210");
					resp.setRetMsg("特殊号码不允许拨打");
					return resp;
				}
			}
		}

		// 固话判断
		if (Function.landlineVerify(calledNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("拨打固电请输入区号");
			return resp;
		}

		// /// 企业spid校验
		if (StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			return resp;
		}

		String sysdate = DateToolkit.pareseDate(new Date(),
				DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		// 判断小号是否存在
		List<XhInfo> xhList = new ArrayList<XhInfo>();
		try {
			xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号信息查询失败");
			return resp;
		}
		if (xhList == null || xhList.size() == 0) {
			resp.setRetCode("202");
			resp.setRetMsg("小号不存在");
			return resp;
		}

		// 判断小号是否已过期
		XhInfo xhinfo = xhList.get(0);
		if (xhinfo.getRemainDays() < 0) {
			resp.setRetCode("204");
			resp.setRetMsg("小号已过期");
			return resp;
		}

		// 判断小号是否处于关机状态
		if (xhinfo.getCloseStatus().equals(Constants.CLOSE_STATUS_1)) {
			resp.setRetCode("207");
			resp.setRetMsg("小号处于关机状态，请先开机");
			return resp;
		}

		Response res = new Response();
		// /// 调用IMS拨号接口
		res = imsService.xhDial(virtualNo, Constants.VIRTUAL_NO_KEY, calledNo,
				new Date());

		if (res.getRetCode() == null || !res.getRetCode().equals("0")) {
			resp.setRetCode("103");
			resp.setRetMsg(res.getRetMsg());
			logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，被叫="
					+ calledNo + "，调用IMS直拨失败：" + res.getRetMsg());
			return resp;
		}
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		return resp;

	}
	
	/**
	 * 小号发短信
	 * @author huangjiabo
	 * */
	@RequestMapping(value = { "/app/xiaohao/sendsms" }, method = { RequestMethod.POST })
	@ResponseBody
	public EResponse sendSMS_get(HttpServletRequest request,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "calledNo", required = true) String calledNo,
			@RequestParam(value = "content", required = true) String content,
			@RequestParam(value = "spid", required = true) String spid) {
		String json;
		EResponse resp = new EResponse();
		DataResponse dresp = new DataResponse();
		resp.setCode(0);
		resp.setDescription("成功");
		resp.setErrorDescription("成功");
		/*json = HttpclientUtils.getPostBody(request);
		
		if(json == null) {
			logger.error("JSON IS NULL.");
			resp.setRetCode("1");
			resp.setRetMsg("请求参数异常");
			return resp;
		}
		
		RequestInfo requestInfo = null;
		SendSMSInfoRequest queryInfo = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			queryInfo = (SendSMSInfoRequest) SendSMSInfoRequest.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);
			
			resp.setRetCode("5");
			resp.setRetMsg("请求报文不符合规范");
			return resp;
		}*/

		String[] calledNoArray = null; // 被叫号码数组
		
		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			dresp.setRetCode("3");
			dresp.setRetMsg("主号号码不能为空");
			resp.setDataObject(dresp);
			return resp;
		}
		if(!Function.isTelphone(phoneNo)) {
			dresp.setRetCode("4");
			dresp.setRetMsg("主号号码不正确");
			resp.setDataObject(dresp);
			return resp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			dresp.setRetCode("3");
			dresp.setRetMsg("小号号码不能为空");
			resp.setDataObject(dresp);
			return resp;
		}
		if(!Function.isTelphone(virtualNo)) {
			dresp.setRetCode("4");
			dresp.setRetMsg("小号号码不正确");
			resp.setDataObject(dresp);
			return resp;
		}
		/////// 被叫号码校验
		if(StringUtils.isEmpty(calledNo)) {
			dresp.setRetCode("3");
			dresp.setRetMsg("发送号码不能为空");
			resp.setDataObject(dresp);
			return resp;
		}
		calledNoArray = calledNo.split(","); // 被叫号码数组
		// 是否发送不允许的号码
		String[] notAllowedArray = Constants.getNotAllowedDial();
		// 参数校验通过的号码
		List<String> correctArray = new ArrayList<String>();
		// 记录发送失败的号码(参数校验失败、caas发送失败)
		StringBuilder errorBuf = new StringBuilder();
		for(int i = 0; i < calledNoArray.length; i++) {
			String temp = calledNoArray[i];
			if(StringUtils.isNotEmpty(temp) && temp.startsWith(Constants.SIGN_PLUS)) { // +开头，去除+
				temp = temp.substring(Constants.SIGN_PLUS.length());
				calledNoArray[i] = temp; // 重新赋值
			}
			if(StringUtils.isNotEmpty(temp) && temp.startsWith(Constants.DISPLAYNBR_86)) { // 86开头，去除86
				temp = temp.substring(Constants.DISPLAYNBR_86.length());
				calledNoArray[i] = temp; // 重新赋值
			}
			if(Function.isTelphone(temp)) { // 是手机号
				if(notAllowedArray != null && notAllowedArray.length > 0) {
					for(String na : notAllowedArray) { 
						if(temp.startsWith(na)) { // 不允许发送的特殊号码
							errorBuf.append(temp).append(",");
							break;
						}
					}
				}
				correctArray.add(temp);
			} else { // 不是手机号
				errorBuf.append(temp).append(",");
			}
		}
		if(correctArray == null || correctArray.size() == 0) {
			dresp.setRetCode("4");
			dresp.setRetMsg("发送号码不正确");
			resp.setDataObject(dresp);
			return resp;
		}
		
		///////// 短信内容校验
		if(content != null) {
			content = content.trim(); // 去除前后空格
		}
		if(StringUtils.isEmpty(content)) {
			
			dresp.setRetCode("4");
			dresp.setRetMsg("短信内容不能为空");
			resp.setDataObject(dresp);
			return resp;
		}
		
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			
			dresp.setRetCode("3");
			dresp.setRetMsg("企业SPID不能为空");
			resp.setDataObject(dresp);
			return resp;
		}

		String sysdate = DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		// 判断小号是否存在
		List<XhInfo> xhList = new ArrayList<XhInfo>();
		try {
			xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			dresp.setRetCode("200");
			dresp.setRetMsg("小号信息查询失败");
			resp.setDataObject(dresp);
			return resp;
		}
		if(xhList == null || xhList.size() == 0) {
			dresp.setRetCode("202");
			dresp.setRetMsg("小号不存在");
			resp.setDataObject(dresp);
			return resp;
		}
		
		// 判断小号是否已过期
		XhInfo xhinfo = xhList.get(0);
		if(xhinfo.getRemainDays() < 0) {
			dresp.setRetCode("204");
			dresp.setRetMsg("小号已过期");
			resp.setDataObject(dresp);
			return resp;
		}
		
		// 判断小号是否处于关机状态
		if(xhinfo.getCloseStatus().equals(Constants.CLOSE_STATUS_1)) {
			
			dresp.setRetCode("207");
			dresp.setRetMsg("小号处于关机状态，请先开机");
			resp.setDataObject(dresp);
			return resp;
		}
		
		// 短信字节数统计
		int smsCount = StringEncoding.getBytesCount(content);
		// 短信字符数统计
		int smsLen = content.length();
		if(smsLen > Constants.getTotalSmsMaxBytes()) { // 超出短信总共最大字节数
			dresp.setRetCode("212");
			dresp.setRetMsg("短信内容过长，发送失败");
			resp.setDataObject(dresp);
			return resp;
		}
		// 需要发送的条数
		int sendNum = 0;
		// 允许的短信的最大字节数
		int smsMaxBytes = Constants.getSmsMaxBytes();
		if(smsCount <= smsMaxBytes) { // 小等于140，一条计算
			sendNum = 1;
		} else { // 大于140字节,134字节计算
			int multiSmsMaxBytes = Constants.getMultiSmsMaxBytes(); // 多条短信最大字节
			if(smsCount % multiSmsMaxBytes == 0) {
				sendNum = smsCount/multiSmsMaxBytes;
			} else {
				sendNum = smsCount/multiSmsMaxBytes + 1;
			}
		}
		// 短信剩余条数
		int remainSms = xhinfo.getRemainSMS();
		// 判断小号短信剩余量
		if(remainSms <= 0 || remainSms < sendNum * calledNoArray.length) { // 剩余条数不足
			if(calledNoArray.length > 1) { // 群发
				dresp.setRetCode("209");
				dresp.setRetMsg("多人发送，小号短信余量不足");
				resp.setDataObject(dresp);
			} else { // 单人发送
				dresp.setRetCode("205");
				dresp.setRetMsg("小号短信余量不足");
				resp.setDataObject(dresp);
			}
			return resp;
		}
		
		// 部分特殊字符转换
		content = StringEncoding.stringEncode(content);
		// 路由码
		String numberPrefix = redisDao.get(Constants.getRedisPhoneHdInfo() + virtualNo.substring(0, 7));
		if(StringUtils.isEmpty(numberPrefix)) {
			numberPrefix = "";
		}
		
		int successNo = 0; // 发送成功号码数
		List<BillsInfo> smsList =  new ArrayList<BillsInfo>(); // 发送成功短信账单
		for(String temp : correctArray) {
			// 1.ims发短信接口，一次调用即可，无需拆分
			Response res = imsService.sendSms(virtualNo, Constants.VIRTUAL_NO_KEY, numberPrefix + temp, 
					Constants.CDATA_BEGIN + content + Constants.CDATA_END, new Date());

			if(res.getRetCode() != null && res.getRetCode().equals("0")) {
				successNo++;
				// 剩余量扣除
				remainSms = remainSms-sendNum;
				
				// 保存短信账单信息
				BillsInfo billInfo = new BillsInfo();
				billInfo.setPhoneNo(phoneNo); // 主号
				billInfo.setVirtualNo(virtualNo); // 小号
				billInfo.setCalledNo(temp); // 接收者
				// 现在只有发短信，不能发短信，所以全部都是发短信。
				billInfo.setCallType(Constants.CALLTYPE_3); // 发短信
				Date sms_date = new Date();
				String sms_sysdate = DateToolkit.pareseDate(sms_date, DateToolkit.YYYY_MM_DD_HH24_MM_SS); // 短信发送时间
				Integer sms_month = Integer.valueOf(DateToolkit.pareseDate(sms_date, DateToolkit.YYYYMM)); // 发送月份
				billInfo.setMonth(sms_month);
				billInfo.setBeginTime(sms_sysdate);
				billInfo.setEndTime(sms_sysdate);
				billInfo.setUseCount(sendNum);
				smsList.add(billInfo);
				
			} else {
				logger.error(res.getRetMsg());
				errorBuf.append(temp).append(",");
			}
		}
		try {
			xhinfo.setRemainSMS(remainSms);
			// 更新小号信息
			xhInfoService.updateXhInfo(xhinfo.getPhoneNo(), xhinfo.getVirtualNo(), null, 
					null, null, xhinfo.getRemainSMS(), null, null, null, null, null);
		} catch (Exception e) {
			logger.error("小号信息修改失败", e);
			dresp.setRetCode("200");
			dresp.setRetMsg("小号信息修改失败");
			resp.setDataObject(dresp);
			return resp;
		}
		
		try {
			String key = Constants.getRedisXhInfo() + phoneNo;
			// 增加到redis内存中
			redisDao.set(key, xhinfo.toJSONString());
			// 设置过期时间
			Long exp = DateToolkit.calExpireTime(sysdate, DateToolkit.YYYY_MM_DD_HH24_MM_SS, xhinfo.getEffectTime(), DateToolkit.YYYY_MM_DD_HH24_MM_SS); // 过期时长
			redisDao.expireAt(key, exp, TimeUnit.SECONDS); // 设置过期时长
		} catch (Exception e) {
			logger.error("小号发短信，redis修改失败, xhinfo=" + xhinfo.toJSONString(), e);
		}
		
		try {
			// 插入短信账单
			billsInfoService.addBillsInfos(smsList);
		} catch (Exception e) {
			logger.error("短信账单增加失败", e);
			dresp.setRetCode("200");
			dresp.setRetMsg("短信账单增加失败");
			resp.setDataObject(dresp);
			return resp;
		}			

		// 是否有发送失败的号码
		if(calledNoArray.length != correctArray.size() || errorBuf.length() > 0) { // 有发送失败(参数校验失败、caas发送失败)
			if(successNo == 0) { // 全部失败
				dresp.setRetCode("105");
				dresp.setRetMsg("发送失败");
				resp.setDataObject(dresp);
			} else { // 部分失败
				// 去除最后一个逗号
				errorBuf.deleteCharAt(errorBuf.length()-1);
				
				dresp.setRetCode("106");
				dresp.setRetMsg("成功" + successNo + "个,失败" + 
						(calledNoArray.length-successNo) + "个(" + errorBuf.toString() + ")");
				resp.setDataObject(dresp);
			}
		} else { // 全部成功
			dresp.setRetCode("0");
			dresp.setRetMsg("操作成功");
			resp.setDataObject(dresp);
		}
		
		return resp;
	}
	
	/**
	 * 小号通话记录删除
	 * @author huangjiabo
	 * */
	@RequestMapping(value = { "/app/xiaohao/delcallrecords" }, method = { RequestMethod.POST })
	@ResponseBody
	public EResponse updateCallRecords_mod(HttpServletRequest request) {
		String json;
		EResponse ersp = new EResponse();
		DataResponse resp = new DataResponse();
		json = HttpclientUtils.getPostBody(request);
		ersp.setCode(0);
		ersp.setDescription("操作成功！");
		ersp.setErrorDescription("操作成功！");
		if(json == null) {
			logger.error("JSON IS NULL.");
			resp.setRetCode("1");
			resp.setRetMsg("请求参数异常");
			ersp.setDataObject(resp);
			return ersp;
		}
		
		RequestInfo requestInfo = null;
		CallRecordsRequest callRecords = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			callRecords = (CallRecordsRequest) CallRecordsRequest.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);
			resp.setRetCode("5");
			resp.setRetMsg("请求报文不符合规范");
			ersp.setDataObject(resp);
			return ersp;
		}
		String phoneNo = callRecords.getPhoneNo(); // 主号号码
		String virtualNo = callRecords.getVirtualNo(); // 小号号码
		String ids = callRecords.getIds(); // 通话id，多个英文逗号分隔。所有传all
		String spid = callRecords.getSpId(); // 企业spid
		String calltypes = callRecords.getCallType(); // 删除记录类型

		///// 主号号码校验
		if(phoneNo == null) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			ersp.setDataObject(resp);
			return ersp;
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			ersp.setDataObject(resp);
			return ersp;
		}
		///// 小号号码校验
		if(virtualNo == null) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			ersp.setDataObject(resp);
			return ersp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			ersp.setDataObject(resp);
			return ersp;
		}
		//// 通话id校验
		if(StringUtils.isEmpty(ids)) {
			resp.setRetCode("3");
			resp.setRetMsg("通话ID不能为空.");
			ersp.setDataObject(resp);
			return ersp;
		}
		ids = ids.trim();
		List<Long> idList = null;
		if(!ids.equals(Constants.ALL_CALL_RECORDS)) { // 不是删除所有
			String[] idsArray = ids.trim().split(","); // 多个ID，逗号分隔
			if(idsArray.length == 0) { // ","
				resp.setRetCode("3");
				resp.setRetMsg("通话ID不能为空.");
				ersp.setDataObject(resp);
				return ersp;
			}
			List<String> strList = Arrays.asList(idsArray); 
			idList = Function.CollStringToLongList(strList);
			if(idList == null) { // id非数字
				resp.setRetCode("3");
				resp.setRetMsg("通话ID不符合规范.");
				ersp.setDataObject(resp);
				return ersp;
			}
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			ersp.setDataObject(resp);
			return ersp;
		}
		//// 删除的记录类型校验
		List<Integer> calltypeList = null;
		if(StringUtils.isEmpty(calltypes)) { // 默认1,2,5
			calltypes = Constants.CALLTYPE_1 + "," +  Constants.CALLTYPE_2 + "," + Constants.CALLTYPE_5;
		}
		String[] calltypeArray = calltypes.trim().split(","); // 多个记录类型，逗号分隔
		if(calltypeArray.length == 0) { // ","
			resp.setRetCode("3");
			resp.setRetMsg("删除记录类型不能为空");
			ersp.setDataObject(resp);
			return ersp;
		}
		List<String> strList = Arrays.asList(calltypeArray); 
		calltypeList = Function.CollStringToIntegerList(strList);
		if(calltypeList == null) { // calltype非数字
			resp.setRetCode("3");
			resp.setRetMsg("删除记录类型不符合规范");
			ersp.setDataObject(resp);
			return ersp;
		}
		
		//// 小号校验
		String sysdate = DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		// 判断小号是否存在
		List<XhInfo> xhList = new ArrayList<XhInfo>();
		try {
			xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号信息查询失败");
			ersp.setDataObject(resp);
			return ersp;
		}
		if(xhList == null || xhList.size() == 0) {
			resp.setRetCode("202");
			resp.setRetMsg("小号不存在");
			ersp.setDataObject(resp);
			return ersp;
		}
		// 判断小号是否已过期
		XhInfo xhinfo = xhList.get(0);
		if(xhinfo.getRemainDays() < 0) {
		ersp.setCode(204);
			resp.setRetCode("204");
			resp.setRetMsg("小号已过期");
			ersp.setDataObject(resp);
			return ersp;
		}

		// 1.账单更新
		try {
			if(ids.equals(Constants.ALL_CALL_RECORDS)) { // 删除所有
				billsInfoService.updateBillsInfo(phoneNo, virtualNo, null, Constants.BILLS_FLAG_1, calltypeList);
			} else {
				billsInfoService.updateBillsInfo(phoneNo, virtualNo, idList, Constants.BILLS_FLAG_1, calltypeList);
			}
		} catch (Exception e) {
			logger.error("小号通话记录删除失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号通话记录删除失败");
			ersp.setDataObject(resp);
			return ersp;
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		ersp.setDataObject(resp);
		return ersp;

	}
	
}
