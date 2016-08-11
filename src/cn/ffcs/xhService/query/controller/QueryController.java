package cn.ffcs.xhService.query.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.model.EntInfo;
import cn.ffcs.xhService.enterprise.model.EntOrderInfo;
import cn.ffcs.xhService.enterprise.model.EntPackageInfo;
import cn.ffcs.xhService.enterprise.model.EntXhInfo;
import cn.ffcs.xhService.enterprise.service.EntInfoService;
import cn.ffcs.xhService.enterprise.service.EntOrderInfoService;
import cn.ffcs.xhService.enterprise.service.EntXhInfoService;
import cn.ffcs.xhService.model.EResponse;
import cn.ffcs.xhService.uphold.entity.CallRecordsData;
import cn.ffcs.xhService.uphold.entity.CallRecordsRequest;
import cn.ffcs.xhService.uphold.entity.DataResponse;
import cn.ffcs.xhService.uphold.entity.OpenBusinessRequest;
import cn.ffcs.xhService.uphold.entity.PageData;
import cn.ffcs.xhService.uphold.entity.QueryBaseInfoRequest;
import cn.ffcs.xhService.uphold.entity.RequestInfo;
import cn.ffcs.xhService.uphold.entity.Response;
import cn.ffcs.xhService.uphold.entity.UnreadResponse;
import cn.ffcs.xhService.uphold.entity.XhProInfo;
import cn.ffcs.xhService.uphold.model.BillsInfo;
import cn.ffcs.xhService.uphold.model.BillsSettleInfo;
import cn.ffcs.xhService.uphold.model.CallRecordsInfo;
import cn.ffcs.xhService.uphold.model.OrderInfo;
import cn.ffcs.xhService.uphold.model.PackageInfo;
import cn.ffcs.xhService.uphold.model.RowCount;
import cn.ffcs.xhService.uphold.model.XhInfo;
import cn.ffcs.xhService.uphold.model.XhKxbInfo;
import cn.ffcs.xhService.uphold.model.XhSmsGroupInfo;
import cn.ffcs.xhService.uphold.model.XhSmsInfo;
import cn.ffcs.xhService.uphold.model.XhSmsInfoResponse;
import cn.ffcs.xhService.uphold.model.XhTcInfosResponse;
import cn.ffcs.xhService.uphold.model.XhTcMxInfo;
import cn.ffcs.xhService.uphold.service.BillsInfoService;
import cn.ffcs.xhService.uphold.service.BillsSettleInfoService;
import cn.ffcs.xhService.uphold.service.ImsService;
import cn.ffcs.xhService.uphold.service.OrderInfoService;
import cn.ffcs.xhService.uphold.service.XhInfoService;
import cn.ffcs.xhService.uphold.service.XhProInfoService;
import cn.ffcs.xhService.uphold.service.XhSmsInfoService;
import cn.ffcs.xhService.utils.AccountManageComponent;
import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.DateToolkit;
import cn.ffcs.xhService.utils.Function;
import cn.ffcs.xhService.utils.HttpclientUtils;
import cn.ffcs.xhService.utils.RedisOperationUtils;

/**
 * 查询类
 * */
@Controller
public class QueryController {

	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());

	@Resource(name = "RedisOperationUtils")
	private RedisOperationUtils redisOperationUtils;

	@Resource(name = "ImsServiceImpl")
	private ImsService imsService;

	@Resource(name = "BillsSettleInfoServiceImpl")
	private BillsSettleInfoService billsSettleInfoService;

	@Resource(name = "XhInfoServiceImpl")
	private XhInfoService xhInfoService;

	@Resource(name = "EntInfoServiceImpl")
	private EntInfoService entInfoService;

	@Resource(name = "EntXhInfoServiceImpl")
	private EntXhInfoService entXhInfoService;
	
	@Resource(name = "BillsInfoServiceImpl")
	private BillsInfoService billsInfoService;

	@Resource(name = "OrderInfoServiceImpl")
	private OrderInfoService orderInfoService;
	
	@Resource(name = "EntOrderInfoServiceImpl")
	private EntOrderInfoService entOrderInfoService;
	
	@Resource(name = "XhSmsInfoServiceImpl")
	private XhSmsInfoService xhSmsInfoService;
	
	@Resource(name="AccountManageComponent")
	private AccountManageComponent accountManageComponent;
	
	@Resource(name = "XhProInfoServiceImpl")
	private XhProInfoService xhProInfoService;

	@Resource
	private RedisDao redisDao;
	
	@Value("${tcmxname}")
	private String tcmxname;
	
	// 加载套餐信息
		private static List<PackageInfo> PACKAGES_LIST = new ArrayList<PackageInfo>();
		// 加载企业套餐信息
		private static List<EntPackageInfo> ENTPACKAGE_LIST = new ArrayList<EntPackageInfo>();

	/**
	 * 小号信息查询
	 * */
	@RequestMapping(value = { "/app/xiaohao/queryinfo" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse queryinfo(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo) {

		EResponse finalResp = new EResponse();
		finalResp.setDescription("查询成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("查询成功！");
		DataResponse resp = new DataResponse();
		// /// 主号号码校验
		if (StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// /// 小号号码校验
		if (StringUtils.isNotEmpty(virtualNo)
				&& !Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (StringUtils.isEmpty(virtualNo)) { // 空全部统一为null，以免后面有空
			virtualNo = null;
		}
		// /// 企业spid校验
		if (StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		List<XhInfo> list = new ArrayList<XhInfo>();
		try {
			String sysdate = DateToolkit.pareseDate(new Date(),
					DateToolkit.YYYY_MM_DD_HH24_MM_SS);
			list = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号信息查询失败");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (list != null && list.size() > 0) {
			resp.setData(list.get(0));
		}
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		finalResp.setDataObject(resp);
		return finalResp;
	}

	/**
	 * 查询小号近期(半年)的使用记录
	 * */
	@RequestMapping(value = { "/app/xiaohao/userrecordmonth" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse userrecordmonth(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo) {
		EResponse finalResp = new EResponse();
		finalResp.setCode(0);
		finalResp.setDescription("查询成功!");
		finalResp.setErrorDescription("查询成功！");
		DataResponse resp = new DataResponse();

		// /// 主号号码校验
		if (StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// /// 小号号码校验
		if (StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// /// 企业spid校验
		if (StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// 1.查询账单表
		List<BillsSettleInfo> list = new ArrayList<BillsSettleInfo>(); // 返回的数据
		try {
			int lastMonths = Constants.getBillSettleMonths(); // 查询的最近月份数
			Date sysdate = new Date();
			String cuMonth = DateToolkit
					.pareseDate(sysdate, DateToolkit.YYYYMM); // 当前月份
			Integer startMonth = Integer.valueOf(DateToolkit.timeAddMonths(
					cuMonth, DateToolkit.YYYYMM, 1 - lastMonths));
			Integer endMonth = Integer.valueOf(DateToolkit.timeAddMonths(
					cuMonth, DateToolkit.YYYYMM, -1));
			Integer currMonth = Integer.valueOf(cuMonth);
			list = billsSettleInfoService.getBillsSettleInfo(phoneNo,
					virtualNo, Constants.CALLTYPE_3, currMonth, startMonth,
					endMonth, "MONTH DESC");
		} catch (Exception e) {
			logger.error("小号半年账单查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号半年账单查询失败");
			finalResp.setDataObject(resp);
			return finalResp;
		}

		if (list != null && list.size() > 0) {
			resp.setData(list);
		}
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		finalResp.setDataObject(resp);
		return finalResp;
	}
	
	/**
	 * 业务开通
	 * */
	@RequestMapping(value = { "/app/xiaohao/opencell" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse opencell(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "orderid", required = true) String orderId,
			@RequestParam(value = "salesid", required = true) String salesId){
		String appid = null;
		EResponse finalResp = new EResponse();
		finalResp.setCode(0);
		finalResp.setDescription("查询成功!");
		finalResp.setErrorDescription("查询成功！");
		DataResponse resp = new DataResponse();
		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空.");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确.");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空.");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确.");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		
		Date sysdate = new Date();
		String nowDate = DateToolkit.Date2String(sysdate, DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		if(spid.equals(Constants.UNITED_PLATFORM_SPID)) { // spid=SP00001
			///// 订单号校验
			if(StringUtils.isEmpty(orderId)) {
				resp.setRetCode("3");
				resp.setRetMsg("订单号不能为空.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			///// 销售品ID校验
			if(StringUtils.isEmpty(salesId)) {
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			String[] salesIdArray = salesId.trim().split(","); // 多个销售品ID，逗号分隔
			if(salesIdArray.length == 0) { // ","
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 判断主号是否已开通小号
			if(redisDao.exists(Constants.getRedisPhoneVirtual() + phoneNo)) {
				resp.setRetCode("206");
				resp.setRetMsg("该号码已开通小号业务.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 查询套餐信息，是否存在
			boolean hasYyb = false; // 是否有语音包
			int usedays = 0; // 使用天数
			int smsamount = 0; // 短信数
			
			// 根据销售IDs批量查询套餐信息
			List<String> salesIds = Arrays.asList(salesIdArray); 
			List<PackageInfo> packageInfos = new ArrayList<PackageInfo>();
			try {
				
				packageInfos = getPackageInfosByIds(salesIds);

			} catch (Exception e) {
				logger.error("小号套餐查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号套餐查询失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			// 判断销售ID是否存在
			for (String saleId : salesIdArray) {
				boolean isHas = false;
				for (PackageInfo info : packageInfos) {
					if (saleId.equals(info.getSalesId())) {
						isHas = true;
						break;
					}
				}
				if (!isHas) {
					resp.setRetCode("201");
					resp.setRetMsg("销售品ID["+ saleId + "]对应的套餐不存在");
					finalResp.setDataObject(resp);
					return finalResp;
				}
			}
			// 累加使用天数和短信数
			for(PackageInfo pack : packageInfos) {
				int salestype = pack.getSalesType(); // 套餐类型
				if(salestype == 1) { // 语音包
					hasYyb = true;
					if(pack.getUseDays() >= 0) {
						usedays += pack.getUseDays(); // 使用天数累加
					}
				}
				if(pack.getSMSAmount() >= 0) {
					smsamount += pack.getSMSAmount(); // 短信数累加
				}
			}
			
			if(!hasYyb) {
				resp.setRetCode("4");
				resp.setRetMsg("业务开通请选择语音包");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			Response res = new Response();
			////////////// 1、调用ims小号业务开通接口
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言 2：不留言（在关机后生效）
			res = imsService.xhBusiOpen(virtualNo, Constants.VIRTUAL_NO_KEY, 
					phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, 
					null, closeType, isRecord, leavemsgType, Constants.POINTSSPID,
					new Date());
			if(res == null || !res.getRetCode().equals("0")) {
				if(res.getRetMsg() != null && res.getRetMsg().contains(Constants.OPENNO_EXPECT)) { // 需要重调一次
					res = imsService.xhBusiOpen(virtualNo, Constants.VIRTUAL_NO_KEY, 
							phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, 
							null, closeType, isRecord, leavemsgType, Constants.POINTSSPID,
							new Date());
					if(res == null || !res.getRetCode().equals("0")) {
						resp.setRetCode("101");
						resp.setRetMsg(res.getRetMsg());
						logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims小号业务开通接口失败：" + res.getRetMsg());
						finalResp.setDataObject(resp);
						return finalResp;
					}
				} else {
					resp.setRetCode("101");
					resp.setRetMsg(res.getRetMsg());
					logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims小号业务开通接口失败：" + res.getRetMsg());
					finalResp.setDataObject(resp);
					return finalResp;
				}
			}
			
			// 3.插入小号信息表,同时设置注销时间
			try {
				XhInfo xhInfo = new XhInfo();
				xhInfo.setAppid(appid); // 应用ID
				xhInfo.setPhoneNo(phoneNo); // 主号号码
				xhInfo.setVirtualNo(virtualNo); // 小号号码
				String nowDateEnd = DateToolkit.getTodayEndTime(nowDate); // 过期时间延长到当天的23:59:59
				xhInfo.setCreateTime(nowDate); // 创建时间
				xhInfo.setEffectTime(nowDate); // 生效时间
				xhInfo.setLogoffTime(DateToolkit.timeAddDays(nowDateEnd, DateToolkit.YYYY_MM_DD_HH24_MM_SS, usedays + Constants.getXhKeepDays())); // 注销时间：生效时间+有效期+保留期
				xhInfo.setEndTime(DateToolkit.timeAddDays(nowDateEnd, DateToolkit.YYYY_MM_DD_HH24_MM_SS, usedays)); // 失效时间:生效时间+有效期
				xhInfo.setRemainSMS(smsamount);
				xhInfo.setCloseStatus(2);
				xhInfo.setUseDays(0);
				xhInfo.setStatusUpdateTime(nowDate); // 为了保证不为空，设置状态更新时间为默认当前时间
				xhInfoService.addXhInfo(xhInfo);
				
				try {
					// 增加到redis内存中
					redisDao.set(Constants.getRedisPhoneVirtual()+xhInfo.getPhoneNo(), xhInfo.getVirtualNo()); // 主号小号对应关系
					redisDao.set(Constants.getRedisVirtualPhone()+xhInfo.getVirtualNo(), xhInfo.getPhoneNo()); // 小号主号对应关系
				} catch (Exception e) {
					logger.error("小号开通，redis增加失败, phoneno=" + xhInfo.getPhoneNo() + ", virtualno=" + xhInfo.getVirtualNo(), e);
				}
			} catch (Exception e) {
				logger.error("小号信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息增加失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			
			try {
				// 4.插入订单信息表
				List<OrderInfo> orderList = new ArrayList<OrderInfo>();
				for(String temp : salesIdArray) {
					temp = temp.trim();
					if(!StringUtils.isEmpty(temp)) {
						OrderInfo orderInfo = new OrderInfo();
						orderInfo.setOrderid(orderId); // 订单号
						orderInfo.setSalesid(temp); // 销售品id
						orderInfo.setPhoneno(phoneNo); // 主号号码
						orderInfo.setVirtualno(virtualNo); // 小号号码
						orderList.add(orderInfo);
					}
				}
				orderInfoService.addOrderInfos(orderList);
			} catch (Exception e) {
				logger.error("订单信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("订单信息增加失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
		} else { // 企业
			if(StringUtils.isEmpty(appid)) {
				resp.setRetCode("3");
				resp.setRetMsg("应用ID不能为空.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 判断套餐是否存在
			if(StringUtils.isEmpty(salesId)) {
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			EntPackageInfo entPackageInfo = null;
			if(ENTPACKAGE_LIST != null && ENTPACKAGE_LIST.size() > 0) {
				for (EntPackageInfo info : ENTPACKAGE_LIST) {
					if (salesId.equals(info.getPkNBR())) {
						entPackageInfo = info;
						break;
					}
				}
			}
			if(entPackageInfo == null) {
				resp.setRetCode("253");
				resp.setRetMsg("销售品ID[" + salesId +"]不存在");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 判断企业是否存在
			List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
			if(entInfoList == null || entInfoList.size() == 0) {
				resp.setRetCode("251");
				resp.setRetMsg("企业不存在");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			EntInfo entInfo = entInfoList.get(0);
			if(entInfo.getStatus() == null || entInfo.getStatus() != Constants.ENT_STATUS_NORMAL) {
				resp.setRetCode("254");
				resp.setRetMsg("企业处于非正常状态");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 主号是否在该企业开通过小号
			List<EntXhInfo> entXhInfoList = null;
			try {
				entXhInfoList = entXhInfoService.getEntXhInfo(spid, appid, phoneNo, null, null, null, null);
			} catch (Exception e) {
				logger.error("企业小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("企业小号信息查询失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if(entXhInfoList != null && entXhInfoList.size() > 0) {
				resp.setRetCode("255");
				resp.setRetMsg("该号码已开通小号业务.");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			Response res = new Response();
			////////////// 1、调用ims企业小号业务开通接口
			String type = Constants.ENT_TYPE_OPEN; // 操作类型
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言 2：不留言（在关机后生效）
			res = imsService.entIntf(spid, appid, entInfo.getPwd(), type, virtualNo, Constants.VIRTUAL_NO_KEY, 
					phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, null, closeType,
					isRecord, leavemsgType, null, new Date());
			if(res == null || !res.getRetCode().equals("0")) {
				if(res.getRetMsg() != null && res.getRetMsg().contains(Constants.OPENNO_EXPECT)) { // 重调一次
					res = imsService.entIntf(spid, appid, entInfo.getPwd(), type, virtualNo, Constants.VIRTUAL_NO_KEY, 
							phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, null, closeType,
							isRecord, leavemsgType, null, new Date());
					if(res == null || !res.getRetCode().equals("0")) {
						resp.setRetCode("151");
						resp.setRetMsg(res.getRetMsg());
						logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims企业小号业务开通接口失败：" + res.getRetMsg());
						finalResp.setDataObject(resp);
						return finalResp;
					}
				} else {
					resp.setRetCode("151");
					resp.setRetMsg(res.getRetMsg());
					logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims企业小号业务开通接口失败：" + res.getRetMsg());
					finalResp.setDataObject(resp);
					return finalResp;
				}
			}
			
			// 扣除号码占用费，并判断是否需要告警
			long usedfee = Math.round(1.0 * entPackageInfo.getUsedFee() * entInfo.getUsedFeeDiscount() / 100);
			// 号码占用费账单
			List<EntDetailBillInfo> detailList = new ArrayList<EntDetailBillInfo>();
			EntDetailBillInfo detailInfo = new EntDetailBillInfo();
			detailInfo.setSpId(spid);
			detailInfo.setAppId(appid);
			detailInfo.setPhoneNo(phoneNo);
			detailInfo.setVirtualNo(virtualNo);
			detailInfo.setCallType(Constants.CALLTYPE_USEDFEE); // 号码占用费
			String nowTime = DateToolkit.Date2String(sysdate, DateToolkit.YYYYMMDDHH24MMSS);
			detailInfo.setBeginTime(nowTime);
			detailInfo.setEndTime(nowTime);
			detailInfo.setUseCount(0);
			detailInfo.setFee(usedfee);
			detailList.add(detailInfo);
			res = accountManageComponent.feeOperation(spid, appid, usedfee, null, detailList);
			if(!res.getRetCode().equals("0")) {
				resp.setRetCode(res.getRetCode());
				resp.setRetMsg(res.getRetMsg());
				logger.error("企业ID=" + spid + ", 应用ID=" + appid + ", 主号=" + phoneNo +
						",小号=" + virtualNo + "计费操作失败。retMsg=" + res.getRetMsg());
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			// 3.插入企业小号信息表
			try {
				EntXhInfo entXhInfo = new EntXhInfo();
				entXhInfo.setSpId(spid);
				entXhInfo.setAppId(appid);
				entXhInfo.setPhoneNo(phoneNo);
				entXhInfo.setVirtualNo(virtualNo);
				entXhInfo.setCreateTime(nowDate);
				entXhInfo.setPkNBR(salesId);
				entXhInfoService.addEntXhInfo(entXhInfo);
				
				try {
					// 增加到redis内存中
					redisDao.set(Constants.getRedisEntXhInfo()+entXhInfo.getVirtualNo(), entXhInfo.toJSONString()); // 企业小号信息
				} catch (Exception e) {
					logger.error("企业小号开通，redis增加失败, entXhInfo=" + entXhInfo.toJSONString(), e);
				}
			} catch (Exception e) {
				logger.error("企业小号信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("企业小号信息增加失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			try {
				// 4.插入订单信息表
				EntOrderInfo orderInfo = new EntOrderInfo();
				orderInfo.setSpId(spid);
				orderInfo.setAppId(appid);
				orderInfo.setOrderid(orderId);
				orderInfo.setPhoneno(phoneNo);
				orderInfo.setVirtualno(virtualNo);
				entOrderInfoService.addEntOrderInfo(orderInfo);
			} catch (Exception e) {
				logger.error("订单信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("订单信息增加失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");

		finalResp.setDataObject(resp);
		return finalResp;
	}
	
	/**
	 * 小号注销
	 * */
	@RequestMapping(value = { "/app/xiaohao/unregxiaohao" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse unregxiaohao(HttpServletRequest request,
			@RequestParam(value = "spid", required = false) String spid,
			@RequestParam(value = "m", required = false) String phoneNo,
			@RequestParam(value = "xiaohao", required = false) String virtualNo) {
		EResponse finalResp = new EResponse();
		DataResponse resp = new DataResponse();
		finalResp.setCode(0);
		finalResp.setDescription("查询成功!");
		finalResp.setErrorDescription("查询成功！");
		// appid是request传来的值，暂时写为空
		String appid = null;
		// /// 主号号码校验
		if (StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// /// 小号号码校验
		if (StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if (!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// // 企业spid校验
		if (StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}

		if (spid.equals(Constants.UNITED_PLATFORM_SPID)) { // spid=SP00001
			/*
			String redisvirtualno = redisOperationUtils
					.getRedisPhoneVirtual(phoneNo);
			// 判断小号是否存在内存中
			if (StringUtils.isEmpty(redisvirtualno)
					|| !virtualNo.equals(redisvirtualno)) {
				resp.setRetCode("202");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			Response res = new Response();
			//	1、调用IMS注销小号
			res = imsService.xhClose(virtualNo, Constants.VIRTUAL_NO_KEY,
					new Date());
			if (res.getRetCode() == null || !res.getRetCode().equals("0")) {
				resp.setRetCode("102");
				resp.setRetMsg(res.getRetMsg());
				finalResp.setDataObject(resp);
				logger.error("主号=" + phoneNo + "，小号=" + virtualNo
						+ "，调用IMS注销小号失败：" + res.getRetMsg());
				return finalResp;
			}
			*/
			// 2、调用ims注销成功后
			String sysdate = DateToolkit.pareseDate(new Date(),
					DateToolkit.YYYY_MM_DD_HH24_MM_SS);
			// 2.1查询小号
			List<XhInfo> xhList = new ArrayList<XhInfo>();
			try {
				xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo,
						sysdate);
			} catch (Exception e) {
				logger.error("小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息查询失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if (xhList != null && xhList.size() > 0) {
				XhInfo xhInfo = xhList.get(0);
				Integer useDays = xhInfo.getUseDays();
				if (useDays == null) {
					useDays = 0;
				}
				xhInfo.setUseDays(useDays);

				try {
					// 从小号表中删除，同事搬到小号历史表，同时从redis中删除，将小号信息插入redis
					xhInfoService.deleteXhInfo(xhInfo);
				} catch (Exception e) {
					logger.error("小号信息修改失败", e);
					resp.setRetCode("200");
					resp.setRetMsg("小号信息修改失败");
					finalResp.setDataObject(resp);
					return finalResp;
				}

				try {
					// 从redis中删除
					redisDao.delete(Constants.getRedisPhoneVirtual()
							+ xhInfo.getPhoneNo());
					redisDao.delete(Constants.getRedisVirtualPhone()
							+ xhInfo.getVirtualNo());
				} catch (Exception e) {
					logger.error(
							"小号注销，redis删除失败, phoneno=" + xhInfo.getPhoneNo()
									+ ", virtualno=" + xhInfo.getVirtualNo(), e);
				}
			} else {
				resp.setRetCode("202");
				resp.setRetMsg("小号不存在");
				logger.error("注销小号，小号不存在。主号号码=" + phoneNo + "；小号号码="
						+ virtualNo);
				finalResp.setDataObject(resp);
				return finalResp;
			}

		} else { // 企业
			if (StringUtils.isEmpty(appid)) {
				resp.setRetCode("3");
				resp.setRetMsg("应用ID不能为空");
				finalResp.setDataObject(resp);
				return finalResp;
			}

			// 企业信息
			List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
			if (entInfoList == null || entInfoList.size() == 0) {
				resp.setRetCode("251");
				resp.setRetMsg("企业不存在");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			EntInfo entInfo = entInfoList.get(0);

			// 查询小号
			List<EntXhInfo> xhList = new ArrayList<EntXhInfo>();
			try {
				xhList = entXhInfoService.getEntXhInfo(spid, appid, phoneNo,
						virtualNo, null, null, null);
			} catch (Exception e) {
				logger.error("小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息查询失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if (xhList == null || xhList.size() == 0) {
				resp.setRetCode("256");
				resp.setRetMsg("小号不存在");
				logger.error("注销小号，小号不存在。企业ID=" + spid + "，应用ID=" + appid
						+ "，主号号码=" + phoneNo + "，小号号码=" + virtualNo);
				finalResp.setDataObject(resp);
				return finalResp;
			}

			Response res = new Response();
			// ////// 1、调用IMS注销企业小号
			String type = Constants.ENT_TYPE_CLOSE; // 操作类型
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言
																// 2：不留言（在关机后生效）
			res = imsService.entIntf(spid, appid, entInfo.getPwd(), type,
					virtualNo, Constants.VIRTUAL_NO_KEY, phoneNo,
					Constants.PHONE_NO_KEY, openType, payType, null, null,
					closeType, isRecord, leavemsgType, null, new Date());
			if (res.getRetCode() == null || !res.getRetCode().equals("0")) {
				resp.setRetCode("152");
				resp.setRetMsg(res.getRetMsg());
				logger.error("主号=" + phoneNo + "，小号=" + virtualNo
						+ "，调用ims小号业务注销接口失败：" + res.getRetMsg());
				finalResp.setDataObject(resp);
				return finalResp;
			}

			// ////// 2、调用ims注销成功后
			EntXhInfo xhInfo = xhList.get(0);
			try {
				// 从企业小号表中删除，同时搬到企业小号历史表
				entXhInfoService.deleteEntXhInfo(xhInfo);
			} catch (Exception e) {
				logger.error("小号信息修改失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息修改失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}

			try {
				// 从redis中删除
				redisDao.delete(Constants.getRedisEntXhInfo()
						+ xhInfo.getVirtualNo());
			} catch (Exception e) {
				logger.error("企业小号注销，redis删除失败. spid=" + spid + ", appid="
						+ appid + ", phoneno=" + phoneNo + ", virtualno="
						+ virtualNo, e);
			}
		}
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		finalResp.setDataObject(resp);
		return finalResp;
	}
	
	/**
	 * 小号开关机、免打扰设置
	 * */
	@RequestMapping(value = { "/app/xiaohao/cellsetting" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse cellsetting(HttpServletRequest request,
			@RequestParam(value = "spid", required = false) String spid,
			@RequestParam(value = "m", required = false) String phoneNo,
			@RequestParam(value = "xiaohao", required = false) String virtualNo,
			@RequestParam(value = "status", required = false) String closeStatus,
			@RequestParam(value = "weekday", required = false) String weekday,
			@RequestParam(value = "closeBegin", required = false) String closeBegin,
			@RequestParam(value = "closeEnd", required = false) String closeEnd) {
		EResponse finalResp = new EResponse();
		DataResponse resp = new DataResponse();
		finalResp.setCode(0);
		finalResp.setDescription("查询成功!");
		finalResp.setErrorDescription("查询成功！");
		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 开关机状态
		if(StringUtils.isEmpty(closeStatus)) {
			resp.setRetCode("3");
			resp.setRetMsg("开关机状态不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isNumeric(closeStatus)) {
			resp.setRetCode("4");
			resp.setRetMsg("开关机状态不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 星期设置
		if(closeStatus.equals(Constants.CLOSE_STATUS_3 + "")) { // 免打扰设置
			if(StringUtils.isEmpty(weekday)) { // 星期不能为空
				resp.setRetCode("3");
				resp.setRetMsg("免打扰设置星期不能为空");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if(!Function.isNumeric(weekday) || weekday.length() != 7) {
				resp.setRetCode("4");
				resp.setRetMsg("免打扰星期设置不符合规范");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			///////// 关机开始时间
			if(StringUtils.isEmpty(closeBegin)) {
				resp.setRetCode("3");
				resp.setRetMsg("关机开始时间不能为空");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if(!Function.timeValidate(closeBegin, DateToolkit.HH_MM)) {
				resp.setRetCode("4");
				resp.setRetMsg("关机开始时间不符合规范");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			closeBegin = DateToolkit.pareseDate(DateToolkit.parseDateFromStr(closeBegin, DateToolkit.HH_MM),DateToolkit.HH_MM);
			///////// 关机结束时间
			if(StringUtils.isEmpty(closeEnd)) {
				resp.setRetCode("3");
				resp.setRetMsg("关机结束时间不能为空");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			if(!Function.timeValidate(closeEnd, DateToolkit.HH_MM)) {
				resp.setRetCode("4");
				resp.setRetMsg("关机结束时间不符合规范");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			closeEnd = DateToolkit.pareseDate(DateToolkit.parseDateFromStr(closeEnd, DateToolkit.HH_MM),DateToolkit.HH_MM);
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		
		String sysdate = DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		// 判断小号是否存在
		List<XhInfo> xhList = new ArrayList<XhInfo>();
		try {
			xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号信息查询失败");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(xhList == null || xhList.size() == 0) {
			resp.setRetCode("202");
			resp.setRetMsg("小号不存在");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		
		// 判断小号是否已过期
		XhInfo xhinfo = xhList.get(0);
		if(xhinfo.getRemainDays() < 0) {
			resp.setRetCode("204");
			resp.setRetMsg("小号已过期");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		/*
		String isRecord = Constants.getIsRecord();
		String leaveMsg = Constants.getMessageType();
		
		Response res = new Response();
		
		//通知Ims
		// 开关机状态 1:关机，2:开机，3：设置免打扰，4：删除免打扰设置
		if(closeStatus.equals(Constants.CLOSE_STATUS_1 + "") || closeStatus.equals(Constants.CLOSE_STATUS_2 + "")) { // 关机/开机，只改状态
			res = imsService.updateXhBusinessinfo(virtualNo, Constants.VIRTUAL_NO_KEY, xhinfo.getCloseBegin(), 
					xhinfo.getCloseEnd(), closeStatus, isRecord, leaveMsg, new Date());
		} else if(closeStatus.equals(Constants.CLOSE_STATUS_3 + "")) { // 设置免打扰
			Calendar cal = Calendar.getInstance();
    		int today = cal.get(Calendar.DAY_OF_WEEK); // 今天
    		char c = weekday.charAt((today+5)%7); // 今天是否免打扰
    		
			if(c == '0') { // 今天没有开启免打扰，清空开关机时间，状态为开机
				res = imsService.updateXhBusinessinfo(virtualNo, Constants.VIRTUAL_NO_KEY, "", 
						"", xhinfo.getCloseStatus()+"", isRecord, leaveMsg, new Date()); // 没有免打扰相当于都是开机
			} else if(c == '1') { // 今天有开启免打扰，设置开关机时间，状态为开机
				res = imsService.updateXhBusinessinfo(virtualNo, Constants.VIRTUAL_NO_KEY, closeBegin, 
						closeEnd, xhinfo.getCloseStatus()+"", isRecord, leaveMsg, new Date()); // 今天有开启免打扰
			}
		} else if(closeStatus.equals(Constants.CLOSE_STATUS_4 + "")) { // 删除免打扰，相当于设置开机，置空开关机时间
			res = imsService.updateXhBusinessinfo(virtualNo, Constants.VIRTUAL_NO_KEY, "", 
					"", xhinfo.getCloseStatus()+"", isRecord, leaveMsg, new Date()); // 没有免打扰相当于都是开机
		} else {
			resp.setRetCode("4");
			resp.setRetMsg("开关机状态不符合规范");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(res.getRetCode() != null && res.getRetCode().equals("0")) { // ims处理成功，更新小号平台记录
		*/	
			// 2.更新小号信息开关机
			try {
				if(closeStatus.equals(Constants.CLOSE_STATUS_1 + "") || closeStatus.equals(Constants.CLOSE_STATUS_2 + "")) { // 关机、开机，只修改状态
					xhInfoService.updateXhInfo(phoneNo, virtualNo, null, null, null, null, Integer.parseInt(closeStatus), null, null, null, sysdate);
					xhinfo.setCloseStatus(Integer.parseInt(closeStatus));
				} else if(closeStatus.equals(Constants.CLOSE_STATUS_3 + "")) { // 设置免打扰，设置开关机时间
					xhInfoService.updateXhInfo(phoneNo, virtualNo, null, null, null, null, null, closeBegin, closeEnd, weekday, sysdate);
					xhinfo.setWeekday(weekday);
					xhinfo.setCloseBegin(closeBegin);
					xhinfo.setCloseEnd(closeEnd);
				} else if(closeStatus.equals(Constants.CLOSE_STATUS_4 + "")) { // 删除免打扰，置空开关机时间
					xhInfoService.updateXhInfo(phoneNo, virtualNo, null, null, null, null, null, "", "", "", sysdate);
					xhinfo.setWeekday("");
					xhinfo.setCloseBegin("");
					xhinfo.setCloseEnd("");
				}
				
				try {
					xhinfo.setStatusUpdateTime(sysdate);
					String key = Constants.getRedisXhInfo() + phoneNo;
					// 增加到redis内存中
					redisDao.set(key, xhinfo.toJSONString());
					// 设置过期时间
					Long exp = DateToolkit.calExpireTime(sysdate, DateToolkit.YYYY_MM_DD_HH24_MM_SS, xhinfo.getEffectTime(), DateToolkit.YYYY_MM_DD_HH24_MM_SS); // 过期时长
					redisDao.expireAt(key, exp, TimeUnit.SECONDS); // 设置过期时长
				} catch (Exception e) {
					logger.error("小号开关机/免打扰设置，redis修改失败, xhinfo=" + xhinfo.toJSONString(), e);
				}
			} catch (Exception e) {
				logger.error("小号开关机修改失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号开关机修改失败");
				finalResp.setDataObject(resp);
				return finalResp;
			}
			
			resp.setRetCode("0");
			resp.setRetMsg("操作成功");
//		} else {
//			resp.setRetCode("104");
//			resp.setRetMsg(res.getRetMsg());
//			logger.error(res.getRetMsg());
//			finalResp.setDataObject(resp);
//			return finalResp;
//		}
		finalResp.setDataObject(resp);
		return finalResp;
	}
	
	
	/**
	 * 小号详单查询
	 * */
	@RequestMapping(value = { "/app/xiaohao/userrecorddetail" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse userrecorddetail(HttpServletRequest request,
			@RequestParam(value = "spid", required = false) String spid,
			@RequestParam(value = "m", required = false) String phoneNo,
			@RequestParam(value = "xiaohao", required = false) String virtualNo,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "pagination", required = false) Integer page,
			@RequestParam(value = "eachpage", required = false) Integer size,
			@RequestParam(value = "calltype", required = false) Integer callType) {
		EResponse finalResp = new EResponse();
		DataResponse resp = new DataResponse();
		finalResp.setCode(0);
		finalResp.setDescription("查询成功!");
		finalResp.setErrorDescription("查询成功！");
		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			finalResp.setDataObject(resp);
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		////// 月份校验
		if(StringUtils.isEmpty(month)) {
			resp.setRetCode("3");
			resp.setRetMsg("月份不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.timeValidate(month, DateToolkit.YYYY_MM)) {
			resp.setRetCode("4");
			resp.setRetMsg("月份不符合规范");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		////// 当前页数校验
		if(page == null) {
			resp.setRetCode("3");
			resp.setRetMsg("当前页数不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isNumeric(page.toString()) || page <= 0) {
			resp.setRetCode("4");
			resp.setRetMsg("当前页数不符合规范");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		////// 每页条数校验
		if(size == null) {
			resp.setRetCode("3");
			resp.setRetMsg("每页条数不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		if(!Function.isNumeric(size.toString()) || size <= 0) {
			resp.setRetCode("4");
			resp.setRetMsg("每页条数不符合规范");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		// 账单类型
		if(callType != null && (callType < 1 || callType > 3)) {
			resp.setRetCode("4");
			resp.setRetMsg("账单类型不符合规范");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			finalResp.setDataObject(resp);
			return finalResp;
		}

		// 1.查询账单明细表
		List<BillsInfo> list = null;
		int total = 0;
		try {
			// yyyy-mm转为yyyymm
			month = DateToolkit.pareseDate(DateToolkit.parseDateFromStr(month, DateToolkit.YYYY_MM), DateToolkit.YYYYMM);
			list = billsInfoService.getBillsInfo(phoneNo, virtualNo, Constants.CALLTYPE_3, month, callType, page, size, Constants.BILLS_FLAG_0, "BEGINTIME DESC");
			total = billsInfoService.getBillsInfoCount(phoneNo, virtualNo, Integer.valueOf(month), Constants.CALLTYPE_3, callType, Constants.BILLS_FLAG_0);
		} catch (Exception e) {
			logger.error("小号账单明细查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号账单明细查询失败");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		
		// 2.查询月账单
		List<BillsSettleInfo> billsSettles = null;
		try {
			Integer startMonth = 0;
			Integer endMonth = 0;
			Integer curMonth = 0;
			if (month.equals(DateToolkit.Date2String(new Date(), DateToolkit.YYYYMM))) {
				// 当前月，从账单明细表查
				curMonth = Integer.parseInt(month);
			} else {
				// 非当前月，从月账单表查
				startMonth = Integer.parseInt(month);
				endMonth = Integer.parseInt(month);
			}
			billsSettles = billsSettleInfoService.getBillsSettleInfo(phoneNo, virtualNo, Constants.CALLTYPE_3, curMonth, startMonth, endMonth, "MONTH DESC");
		} catch (Exception e) {
			logger.error("小号账单明细查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号账单明细查询失败");
			finalResp.setDataObject(resp);
			return finalResp;
		}
		
		// 页面数据
		PageData pageData = new PageData();
		pageData.setPage(page);
		pageData.setTotal(total);
		if(list != null && list.size() > 0) {
			pageData.setList(list);
		}
		if (billsSettles != null && billsSettles.size() > 0) {
			BillsSettleInfo info = billsSettles.get(0);
			pageData.setDailTotal(info.getDailTotal());
			pageData.setDialTimes(info.getDialTimes());
			pageData.setAnswerTotal(info.getAnswerTotal());
			pageData.setAnswerTimes(info.getAnswerTimes());
			pageData.setSMSTotal(info.getSMSTotal());			
		} else {
			pageData.setDailTotal(0L);
			pageData.setDialTimes(0L);
			pageData.setAnswerTotal(0L);
			pageData.setAnswerTimes(0L);
			pageData.setSMSTotal(0L);
		}
		resp.setData(pageData);
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");

		finalResp.setDataObject(resp);
		return finalResp;
	}
	/**
	 * 小号通话记录查询
	 * @author huangjiabo
	 * */
	@RequestMapping(value = { "/app/xiaohao/getcallrecords" }, method = { RequestMethod.POST })
	@ResponseBody
	public EResponse getCallRecords_get(HttpServletRequest request) {
		String json;
		DataResponse resp = new DataResponse();
		EResponse ersp = new EResponse();
		json = HttpclientUtils.getPostBody(request);
		ersp.setCode(0);
		ersp.setDescription("查询成功");
		ersp.setErrorDescription("查询成功");
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
		String lastTime = callRecords.getLastTime(); // 更新时间
		Integer page = callRecords.getPage(); // 页数
		Integer size = callRecords.getSize(); // 每页条数
		String spid = callRecords.getSpId(); // 企业spid

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
		///// 最后更新时间校验
		if(StringUtils.isEmpty(lastTime)) {
			lastTime = null;
		}
		if(StringUtils.isNotEmpty(lastTime) && !Function.timeValidate(lastTime, DateToolkit.YYYY_MM_DD_HH24_MM_SS)) {
			resp.setRetCode("4");
			resp.setRetMsg("最后更新时间不符合规范");
			ersp.setDataObject(resp);
			return ersp;
		}
		//// 客户端类型校验
		Integer[] calltypes = {Constants.CALLTYPE_2, Constants.CALLTYPE_5};
		
		//// 页码
		if(page == null || page.equals("")) {
			page = 1;
		}
		if(!Function.isNumeric(page.toString()) || page <= 0) {
			resp.setRetCode("4");
			resp.setRetMsg("当前页数不符合规范");
			ersp.setDataObject(resp);
			return ersp;
		}
		//// 每页记录数
		if(size == null || size.equals("")) {
			size = 100;
		}
		if(!Function.isNumeric(size.toString()) || size <= 0) {
			resp.setRetCode("4");
			resp.setRetMsg("每页条数不符合规范");
			ersp.setDataObject(resp);
			return ersp;
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
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
			resp.setRetCode("204");
			resp.setRetMsg("小号已过期");
			ersp.setDataObject(resp);
			return ersp;
		}

		// 1.查询账单明细表
		List<CallRecordsInfo> list = null;
		try {
			list = billsInfoService.getCallRecordsInfo(phoneNo, virtualNo, Arrays.asList(calltypes), lastTime, "BEGINTIME DESC", 0, page, size);
		} catch (Exception e) {
			logger.error("小号通话记录查询失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("小号通话记录查询失败");
			ersp.setDataObject(resp);
			return ersp;
		}
		
		// 返回的数据
		CallRecordsData callData = new CallRecordsData();
		callData.setPhoneNo(phoneNo);
		callData.setVirtualNo(virtualNo);
		callData.setPage(page);
		if(list != null && list.size() > 0) {
			callData.setList(list); 
			callData.setTotal(list.size());
		}
		resp.setData(callData);
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		ersp.setDataObject(resp);
		return ersp;
	}
	
	/**
	 * 返回满足条件的套餐
	 * @param salesIds 需要返回的salesIds。
	 * */
	private List<PackageInfo> getPackageInfosByIds(List<String> salesIds) {
		if(salesIds == null || salesIds.size() == 0) {
			return null;
		}
		
		List<PackageInfo> retList = new ArrayList<PackageInfo>();
		for(String sid : salesIds) {
			for(PackageInfo pack : PACKAGES_LIST) {
				if(sid.trim().equals(pack.getSalesId())) {
					retList.add(pack);
					break;
				}
			}
		}
		return retList;
	}
	
	/**
	 * 业务开通(原有接口)
	 * */
	@RequestMapping(value = { "/spip/api/openNo" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response openNo(HttpServletRequest request) {
		String json;
		Response resp = new Response();
		
		json = HttpclientUtils.getPostBody(request);
		
		if(json == null) {
			logger.error("JSON IS NULL.");
			
			resp.setRetCode("1");
			resp.setRetMsg("请求参数异常");
			return resp;
		}
		
		RequestInfo requestInfo = null;
		OpenBusinessRequest businessRequest = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			businessRequest = (OpenBusinessRequest) OpenBusinessRequest.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);
			
			resp.setRetCode("5");
			resp.setRetMsg("请求报文不符合规范");
			return resp;
		}
		String phoneNo = businessRequest.getPhoneNo(); // 主号号码
		String virtualNo = businessRequest.getVirtualNo(); // 小号号码
		String orderId = businessRequest.getOrderId(); // 订单号
		String salesId = businessRequest.getSalesId(); // 销售品ID
		String spid = businessRequest.getSpId(); // 企业spid
		String appid = businessRequest.getAppVersion();

		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空.");
			return resp;
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确.");
			return resp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空.");
			return resp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确.");
			return resp;
		}
		///// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			return resp;
		}
		
		Date sysdate = new Date();
		String nowDate = DateToolkit.Date2String(sysdate, DateToolkit.YYYY_MM_DD_HH24_MM_SS);
		if(spid.equals(Constants.UNITED_PLATFORM_SPID)) { // spid=SP00001
			///// 订单号校验
			if(StringUtils.isEmpty(orderId)) {
				resp.setRetCode("3");
				resp.setRetMsg("订单号不能为空.");
				return resp;
			}
			///// 销售品ID校验
			if(StringUtils.isEmpty(salesId)) {
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空.");
				return resp;
			}
			String[] salesIdArray = salesId.trim().split(","); // 多个销售品ID，逗号分隔
			if(salesIdArray.length == 0) { // ","
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空.");
				return resp;
			}
			
			// 判断主号是否已开通小号
			if(redisDao.exists(Constants.getRedisPhoneVirtual() + phoneNo)) {
				resp.setRetCode("206");
				resp.setRetMsg("该号码已开通小号业务.");
				return resp;
			}
			
			// 查询套餐信息，是否存在
			boolean hasYyb = false; // 是否有语音包
			int usedays = 0; // 使用天数
			int smsamount = 0; // 短信数
			
			// 根据销售IDs批量查询套餐信息
			List<String> salesIds = Arrays.asList(salesIdArray); 
			List<PackageInfo> packageInfos = new ArrayList<PackageInfo>();
			try {
				
				packageInfos = getPackageInfosByIds(salesIds);

			} catch (Exception e) {
				logger.error("小号套餐查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号套餐查询失败");
				return resp;
			}
			// 判断销售ID是否存在
			for (String saleId : salesIdArray) {
				boolean isHas = false;
				for (PackageInfo info : packageInfos) {
					if (saleId.equals(info.getSalesId())) {
						isHas = true;
						break;
					}
				}
				if (!isHas) {
					resp.setRetCode("201");
					resp.setRetMsg("销售品ID["+ saleId + "]对应的套餐不存在");
					return resp;
				}
			}
			// 累加使用天数和短信数
			for(PackageInfo pack : packageInfos) {
				int salestype = pack.getSalesType(); // 套餐类型
				if(salestype == 1) { // 语音包
					hasYyb = true;
					if(pack.getUseDays() >= 0) {
						usedays += pack.getUseDays(); // 使用天数累加
					}
				}
				if(pack.getSMSAmount() >= 0) {
					smsamount += pack.getSMSAmount(); // 短信数累加
				}
			}
			
			if(!hasYyb) {
				resp.setRetCode("4");
				resp.setRetMsg("业务开通请选择语音包");
				return resp;
			}
			
			Response res = new Response();
			////////////// 1、调用ims小号业务开通接口
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言 2：不留言（在关机后生效）
			res = imsService.xhBusiOpen(virtualNo, Constants.VIRTUAL_NO_KEY, 
					phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, 
					null, closeType, isRecord, leavemsgType, Constants.POINTSSPID,
					new Date());
			if(res == null || !res.getRetCode().equals("0")) {
				if(res.getRetMsg() != null && res.getRetMsg().contains(Constants.OPENNO_EXPECT)) { // 需要重调一次
					res = imsService.xhBusiOpen(virtualNo, Constants.VIRTUAL_NO_KEY, 
							phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, 
							null, closeType, isRecord, leavemsgType, Constants.POINTSSPID,
							new Date());
					if(res == null || !res.getRetCode().equals("0")) {
						resp.setRetCode("101");
						resp.setRetMsg(res.getRetMsg());
						logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims小号业务开通接口失败：" + res.getRetMsg());
						return resp;
					}
				} else {
					resp.setRetCode("101");
					resp.setRetMsg(res.getRetMsg());
					logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims小号业务开通接口失败：" + res.getRetMsg());
					return resp;
				}
			}
			
			// 3.插入小号信息表,同时设置注销时间
			try {
				XhInfo xhInfo = new XhInfo();
				xhInfo.setAppid(appid); // 应用ID
				xhInfo.setPhoneNo(phoneNo); // 主号号码
				xhInfo.setVirtualNo(virtualNo); // 小号号码
				String nowDateEnd = DateToolkit.getTodayEndTime(nowDate); // 过期时间延长到当天的23:59:59
				xhInfo.setCreateTime(nowDate); // 创建时间
				xhInfo.setEffectTime(nowDate); // 生效时间
				xhInfo.setLogoffTime(DateToolkit.timeAddDays(nowDateEnd, DateToolkit.YYYY_MM_DD_HH24_MM_SS, usedays + Constants.getXhKeepDays())); // 注销时间：生效时间+有效期+保留期
				xhInfo.setEndTime(DateToolkit.timeAddDays(nowDateEnd, DateToolkit.YYYY_MM_DD_HH24_MM_SS, usedays)); // 失效时间:生效时间+有效期
				xhInfo.setRemainSMS(smsamount);
				xhInfo.setCloseStatus(2);
				xhInfo.setUseDays(0);
				xhInfo.setStatusUpdateTime(nowDate); // 为了保证不为空，设置状态更新时间为默认当前时间
				xhInfoService.addXhInfo(xhInfo);
				
				try {
					// 增加到redis内存中
					redisDao.set(Constants.getRedisPhoneVirtual()+xhInfo.getPhoneNo(), xhInfo.getVirtualNo()); // 主号小号对应关系
					redisDao.set(Constants.getRedisVirtualPhone()+xhInfo.getVirtualNo(), xhInfo.getPhoneNo()); // 小号主号对应关系
				} catch (Exception e) {
					logger.error("小号开通，redis增加失败, phoneno=" + xhInfo.getPhoneNo() + ", virtualno=" + xhInfo.getVirtualNo(), e);
				}
			} catch (Exception e) {
				logger.error("小号信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息增加失败");
				return resp;
			}
			
			
			try {
				// 4.插入订单信息表
				List<OrderInfo> orderList = new ArrayList<OrderInfo>();
				for(String temp : salesIdArray) {
					temp = temp.trim();
					if(!StringUtils.isEmpty(temp)) {
						OrderInfo orderInfo = new OrderInfo();
						orderInfo.setOrderid(orderId); // 订单号
						orderInfo.setSalesid(temp); // 销售品id
						orderInfo.setPhoneno(phoneNo); // 主号号码
						orderInfo.setVirtualno(virtualNo); // 小号号码
						orderList.add(orderInfo);
					}
				}
				orderInfoService.addOrderInfos(orderList);
			} catch (Exception e) {
				logger.error("订单信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("订单信息增加失败");
				return resp;
			}
		} else { // 企业
			if(StringUtils.isEmpty(appid)) {
				resp.setRetCode("3");
				resp.setRetMsg("应用ID不能为空.");
				return resp;
			}
			
			// 判断套餐是否存在
			if(StringUtils.isEmpty(salesId)) {
				resp.setRetCode("3");
				resp.setRetMsg("销售品ID不能为空");
				return resp;
			}
			EntPackageInfo entPackageInfo = null;
			if(ENTPACKAGE_LIST != null && ENTPACKAGE_LIST.size() > 0) {
				for (EntPackageInfo info : ENTPACKAGE_LIST) {
					if (salesId.equals(info.getPkNBR())) {
						entPackageInfo = info;
						break;
					}
				}
			}
			if(entPackageInfo == null) {
				resp.setRetCode("253");
				resp.setRetMsg("销售品ID[" + salesId +"]不存在");
				return resp;
			}
			
			// 判断企业是否存在
			List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
			if(entInfoList == null || entInfoList.size() == 0) {
				resp.setRetCode("251");
				resp.setRetMsg("企业不存在");
				return resp;
			}
			EntInfo entInfo = entInfoList.get(0);
			if(entInfo.getStatus() == null || entInfo.getStatus() != Constants.ENT_STATUS_NORMAL) {
				resp.setRetCode("254");
				resp.setRetMsg("企业处于非正常状态");
				return resp;
			}
			
			// 主号是否在该企业开通过小号
			List<EntXhInfo> entXhInfoList = null;
			try {
				entXhInfoList = entXhInfoService.getEntXhInfo(spid, appid, phoneNo, null, null, null, null);
			} catch (Exception e) {
				logger.error("企业小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("企业小号信息查询失败");
				return resp;
			}
			if(entXhInfoList != null && entXhInfoList.size() > 0) {
				resp.setRetCode("255");
				resp.setRetMsg("该号码已开通小号业务.");
				return resp;
			}
			
			Response res = new Response();
			////////////// 1、调用ims企业小号业务开通接口
			String type = Constants.ENT_TYPE_OPEN; // 操作类型
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言 2：不留言（在关机后生效）
			res = imsService.entIntf(spid, appid, entInfo.getPwd(), type, virtualNo, Constants.VIRTUAL_NO_KEY, 
					phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, null, closeType,
					isRecord, leavemsgType, null, new Date());
			if(res == null || !res.getRetCode().equals("0")) {
				if(res.getRetMsg() != null && res.getRetMsg().contains(Constants.OPENNO_EXPECT)) { // 重调一次
					res = imsService.entIntf(spid, appid, entInfo.getPwd(), type, virtualNo, Constants.VIRTUAL_NO_KEY, 
							phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, null, closeType,
							isRecord, leavemsgType, null, new Date());
					if(res == null || !res.getRetCode().equals("0")) {
						resp.setRetCode("151");
						resp.setRetMsg(res.getRetMsg());
						logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims企业小号业务开通接口失败：" + res.getRetMsg());
						return resp;
					}
				} else {
					resp.setRetCode("151");
					resp.setRetMsg(res.getRetMsg());
					logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims企业小号业务开通接口失败：" + res.getRetMsg());
					return resp;
				}
			}
			
			// 扣除号码占用费，并判断是否需要告警
			long usedfee = Math.round(1.0 * entPackageInfo.getUsedFee() * entInfo.getUsedFeeDiscount() / 100);
			// 号码占用费账单
			List<EntDetailBillInfo> detailList = new ArrayList<EntDetailBillInfo>();
			EntDetailBillInfo detailInfo = new EntDetailBillInfo();
			detailInfo.setSpId(spid);
			detailInfo.setAppId(appid);
			detailInfo.setPhoneNo(phoneNo);
			detailInfo.setVirtualNo(virtualNo);
			detailInfo.setCallType(Constants.CALLTYPE_USEDFEE); // 号码占用费
			String nowTime = DateToolkit.Date2String(sysdate, DateToolkit.YYYYMMDDHH24MMSS);
			detailInfo.setBeginTime(nowTime);
			detailInfo.setEndTime(nowTime);
			detailInfo.setUseCount(0);
			detailInfo.setFee(usedfee);
			detailList.add(detailInfo);
			res = accountManageComponent.feeOperation(spid, appid, usedfee, null, detailList);
			if(!res.getRetCode().equals("0")) {
				resp.setRetCode(res.getRetCode());
				resp.setRetMsg(res.getRetMsg());
				logger.error("企业ID=" + spid + ", 应用ID=" + appid + ", 主号=" + phoneNo +
						",小号=" + virtualNo + "计费操作失败。retMsg=" + res.getRetMsg());
				return resp;
			}
			
			// 3.插入企业小号信息表
			try {
				EntXhInfo entXhInfo = new EntXhInfo();
				entXhInfo.setSpId(spid);
				entXhInfo.setAppId(appid);
				entXhInfo.setPhoneNo(phoneNo);
				entXhInfo.setVirtualNo(virtualNo);
				entXhInfo.setCreateTime(nowDate);
				entXhInfo.setPkNBR(salesId);
				entXhInfoService.addEntXhInfo(entXhInfo);
				
				try {
					// 增加到redis内存中
					redisDao.set(Constants.getRedisEntXhInfo()+entXhInfo.getVirtualNo(), entXhInfo.toJSONString()); // 企业小号信息
				} catch (Exception e) {
					logger.error("企业小号开通，redis增加失败, entXhInfo=" + entXhInfo.toJSONString(), e);
				}
			} catch (Exception e) {
				logger.error("企业小号信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("企业小号信息增加失败");
				return resp;
			}
			
			try {
				// 4.插入订单信息表
				EntOrderInfo orderInfo = new EntOrderInfo();
				orderInfo.setSpId(spid);
				orderInfo.setAppId(appid);
				orderInfo.setOrderid(orderId);
				orderInfo.setPhoneno(phoneNo);
				orderInfo.setVirtualno(virtualNo);
				entOrderInfoService.addEntOrderInfo(orderInfo);
			} catch (Exception e) {
				logger.error("订单信息增加失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("订单信息增加失败");
				return resp;
			}
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");

		return resp;
	}
	
	/**
	 * 小号注销（原有接口）
	 * */
	@RequestMapping(value = { "/spip/api/closeNo" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response closeNo(HttpServletRequest request) {
		String json;
		Response resp = new Response();
		
		json = HttpclientUtils.getPostBody(request);
		
		if(json == null) {
			logger.error("JSON IS NULL.");
			
			resp.setRetCode("1");
			resp.setRetMsg("请求参数异常");
			return resp;
		}
		
		RequestInfo requestInfo = null;
		QueryBaseInfoRequest queryInfo = null;
		try {
			requestInfo = (RequestInfo) RequestInfo.fromJSONString(json);
			queryInfo = (QueryBaseInfoRequest) QueryBaseInfoRequest.fromJSONString(requestInfo.getRequest());
		} catch (Exception e) {
			logger.error("json=" + json);
			logger.error("请求转换失败", e);
			
			resp.setRetCode("5");
			resp.setRetMsg("请求报文不符合规范");
			return resp;
		}
		String phoneNo = queryInfo.getPhoneNo(); // 主号号码
		String virtualNo = queryInfo.getVirtualNo(); // 小号号码
		String spid = queryInfo.getSpId(); // 企业spip
		String appid = queryInfo.getAppVersion(); // 应用ID

		///// 主号号码校验
		if(StringUtils.isEmpty(phoneNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("主号号码不能为空");
			return resp;
		}
		if(!Function.isTelphone(phoneNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("主号号码不正确");
			return resp;
		}
		///// 小号号码校验
		if(StringUtils.isEmpty(virtualNo)) {
			resp.setRetCode("3");
			resp.setRetMsg("小号号码不能为空");
			return resp;
		}
		if(!Function.isTelphone(virtualNo)) {
			resp.setRetCode("4");
			resp.setRetMsg("小号号码不正确");
			return resp;
		}
		//// 企业spid校验
		if(StringUtils.isEmpty(spid)) {
			resp.setRetCode("3");
			resp.setRetMsg("企业SPID不能为空");
			return resp;
		}

		if(spid.equals(Constants.UNITED_PLATFORM_SPID)) { // spid=SP00001
			String redisvirtualno = redisOperationUtils.getRedisPhoneVirtual(phoneNo);
			// 判断小号是否存在内存中
			if(StringUtils.isEmpty(redisvirtualno) || !virtualNo.equals(redisvirtualno)) {
				resp.setRetCode("202");
				resp.setRetMsg("小号不存在");
				return resp;
			}
			
			Response res = new Response();
			//////// 1、调用IMS注销小号
			res = imsService.xhClose(virtualNo, Constants.VIRTUAL_NO_KEY, new Date());
			
			if(res.getRetCode() == null || !res.getRetCode().equals("0")) {
				resp.setRetCode("102");
				resp.setRetMsg(res.getRetMsg());
				logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用IMS注销小号失败：" + res.getRetMsg());
				return resp;
			}
			
			//////// 2、调用ims注销成功后
			String sysdate = DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
			// 2.1查询小号
			List<XhInfo> xhList = new ArrayList<XhInfo>();
			try {
				xhList = redisOperationUtils.getXhInfo(phoneNo, virtualNo, sysdate);
			} catch (Exception e) {
				logger.error("小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息查询失败");
				return resp;
			}
			if(xhList != null && xhList.size() > 0) {
				XhInfo xhInfo = xhList.get(0);
				Integer useDays = xhInfo.getUseDays();
				if(useDays == null) {
					useDays = 0;
				}
				xhInfo.setUseDays(useDays);
				
				try {
					// 从小号表中删除，同事搬到小号历史表，同时从redis中删除，将小号信息插入redis
					xhInfoService.deleteXhInfo(xhInfo);
				} catch (Exception e) {
					logger.error("小号信息修改失败", e);
					resp.setRetCode("200");
					resp.setRetMsg("小号信息修改失败");
					return resp;
				}
				
				try {
					// 从redis中删除
					redisDao.delete(Constants.getRedisPhoneVirtual() + xhInfo.getPhoneNo());
					redisDao.delete(Constants.getRedisVirtualPhone() + xhInfo.getVirtualNo());
				} catch (Exception e) {
					logger.error("小号注销，redis删除失败, phoneno=" + xhInfo.getPhoneNo() + ", virtualno=" + xhInfo.getVirtualNo(), e);
				}
			} else {
				resp.setRetCode("202");
				resp.setRetMsg("小号不存在");
				logger.error("注销小号，小号不存在。主号号码=" + phoneNo + "；小号号码=" + virtualNo);
				return resp;
			}
			
		} else { // 企业
			if(StringUtils.isEmpty(appid)) {
				resp.setRetCode("3");
				resp.setRetMsg("应用ID不能为空");
				return resp;
			}
			
			// 企业信息
			List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
			if(entInfoList == null || entInfoList.size() == 0) {
				resp.setRetCode("251");
				resp.setRetMsg("企业不存在");
				return resp;
			}
			EntInfo entInfo = entInfoList.get(0);
			
			// 查询小号
			List<EntXhInfo> xhList = new ArrayList<EntXhInfo>();
			try {
				xhList = entXhInfoService.getEntXhInfo(spid, appid, phoneNo, virtualNo, null, null, null);
			} catch (Exception e) {
				logger.error("小号信息查询失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息查询失败");
				return resp;
			}
			if(xhList == null || xhList.size() == 0) {
				resp.setRetCode("256");
				resp.setRetMsg("小号不存在");
				logger.error("注销小号，小号不存在。企业ID=" + spid + "，应用ID=" + appid + 
						"，主号号码=" + phoneNo + "，小号号码=" + virtualNo);
				return resp;
			}
			
			Response res = new Response();
			//////// 1、调用IMS注销企业小号
			String type = Constants.ENT_TYPE_CLOSE; // 操作类型
			String openType = Constants.getOpenType(); // 开通业务，1：语音，2：短信，3：同时开通语音和短信
			String payType = Constants.getPayType(); // 支付方式，1：互联网支付，2：IT下账
			String closeType = Constants.getCloseType(); // 是否关机：1:关机 2:开机
			String isRecord = Constants.getIsRecord(); // 是否录音：1：录音 2：不录音
			String leavemsgType = Constants.getMessageType(); // 是否留言：1:留言 2：不留言（在关机后生效）
			res = imsService.entIntf(spid, appid, entInfo.getPwd(), type, virtualNo, Constants.VIRTUAL_NO_KEY, 
					phoneNo, Constants.PHONE_NO_KEY, openType, payType, null, null, closeType,
					isRecord, leavemsgType, null, new Date());
			if(res.getRetCode() == null || !res.getRetCode().equals("0")) {
				resp.setRetCode("152");
				resp.setRetMsg(res.getRetMsg());
				logger.error("主号=" + phoneNo + "，小号=" + virtualNo + "，调用ims小号业务注销接口失败：" + res.getRetMsg());
				return resp;
			}
			
			//////// 2、调用ims注销成功后
			EntXhInfo xhInfo = xhList.get(0);
			try {
				// 从企业小号表中删除，同时搬到企业小号历史表
				entXhInfoService.deleteEntXhInfo(xhInfo);
			} catch (Exception e) {
				logger.error("小号信息修改失败", e);
				resp.setRetCode("200");
				resp.setRetMsg("小号信息修改失败");
				return resp;
			}
			
			try {
				// 从redis中删除
				redisDao.delete(Constants.getRedisEntXhInfo() + xhInfo.getVirtualNo());
			} catch (Exception e) {
				logger.error("企业小号注销，redis删除失败. spid=" + spid + ", appid=" + appid + 
						", phoneno=" + phoneNo + ", virtualno=" + virtualNo, e);
			}
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		return resp;
	}
	
	/**
	 * 未读短信明细查询
	 * */
	@RequestMapping(value = { "/app/xiaohao/unreadsmsdetail" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse unreadsmsdetail(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "rno", required = true) String rno) {
		EResponse finalResp = new EResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		UnreadResponse uresp = new UnreadResponse();
		XhSmsInfoResponse xresp = null;
		List<XhSmsInfo> list = new ArrayList<XhSmsInfo>();
		List<XhSmsInfoResponse> list2 = new ArrayList<XhSmsInfoResponse>();
		try {
			//未读短信明细
			list = xhSmsInfoService.getXhSmsInfo(virtualNo, rno, null, null,null);

		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			finalResp.setDataObject(uresp);
			return finalResp;
		}
		if (list != null && list.size() > 0) {
			for(XhSmsInfo xi:list){
				xresp = new XhSmsInfoResponse();
				xresp.setSent_date(xi.getCreatetime());
				xresp.setSent_number(xi.getVcaller());
				xresp.setSms_content(xi.getVcontent());
				xresp.setSms_receive_xh(xi.getVcallee());
				xresp.setSms_receive_no(phoneNo);
				xresp.setSent_timestamp(DateToolkit.String2Date(xi.getCreatetime(), DateToolkit.YYYY_MM_DD_HH_MM_SS).getTime());
				xresp.setSmsid(DateToolkit.Date2String(new Date(),DateToolkit.YYYYMMDDHH24MMSS)+xi.getSmsid());
				list2.add(xresp);
			}
		}
		uresp.setUnreadlist(list2);
		finalResp.setDataObject(uresp);
		return finalResp;
	}
/**
	 * 未读小号列表
	 * */
	@RequestMapping(value = { "/app/xiaohao/unreadsms" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse unreadsms(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "rno", required = true) String rno) {
		EResponse finalResp = new EResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		UnreadResponse uresp = new UnreadResponse();
		XhSmsInfoResponse xresp = null;
		List<XhSmsInfo> list = new ArrayList<XhSmsInfo>();
		List<XhSmsInfoResponse> list2 = new ArrayList<XhSmsInfoResponse>();
		try {
			//查询未读小号列表
			list = xhSmsInfoService.getXhSmsInfo(virtualNo, rno, null, null,null);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			finalResp.setDataObject(uresp);
			return finalResp;
		}
		if (list != null && list.size() > 0) {
			for(XhSmsInfo xi:list){
				xresp = new XhSmsInfoResponse();
				xresp.setSent_date(xi.getCreatetime());
				xresp.setSent_number(xi.getVcaller());
				xresp.setSms_content(xi.getVcontent());
				xresp.setSmsid(xi.getSmsid());
				xresp.setSent_timestamp(DateToolkit.String2Date(xi.getCreatetime(), DateToolkit.YYYY_MM_DD_HH_MM_SS).getTime());
				list2.add(xresp);
			}
			uresp.setUnreadlist(list2);
		}
		finalResp.setDataObject(uresp);
		return finalResp;
	}
	
	/**
	 * 未读短信设为已读
	 * */
	@RequestMapping(value = { "/app/xiaohao/readedsms" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse readedsms(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "lasttime", required = true) String lasttime,
			@RequestParam(value = "rno", required = true) String rno,
			@RequestParam(value = "smsid", required = true) String smsid){
		EResponse finalResp = new EResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		UnreadResponse uresp = new UnreadResponse();
		try {
			//更新小号列表
			xhSmsInfoService.updateXhSmsInfo(virtualNo, rno,smsid);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			finalResp.setDataObject(uresp);
			return finalResp;
		}
		return finalResp;
	}
	
	/**
	 * 未读短信分组列表查询
	 * */
	@RequestMapping(value = { "/app/xiaohao/unreadgroupsms" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse unreadgroupsms(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo) {
		EResponse finalResp = new EResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		UnreadResponse uresp = new UnreadResponse();
		XhSmsGroupInfo xresp = null;
		List<RowCount> list = new ArrayList<RowCount>();
		List<XhSmsGroupInfo> list2 = new ArrayList<XhSmsGroupInfo>();
		try {
			//查询未读短信分组列表
			list = xhSmsInfoService.getXhSmsGroupInfo(virtualNo);
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			finalResp.setDataObject(uresp);
			return finalResp;
		}
		if (list != null && list.size() > 0) {
			for(RowCount rc:list){
				xresp = new XhSmsGroupInfo();
				xresp.setLast_sent_number(rc.getVcaller());
				xresp.setLast_sent_time(rc.getCreatetime());
				xresp.setLast_sms_content(rc.getVcontent());
				xresp.setLast_time(DateToolkit.String2Date(rc.getCreatetime(), DateToolkit.YYYY_MM_DD_HH_MM_SS).getTime());
				xresp.setRow_count(rc.getRow_count());
				list2.add(xresp);
			}
			uresp.setUnreadlist(list2);
		}
		finalResp.setDataObject(uresp);
		return finalResp;
	}
	
	/**
	 * 小号续订
	 * */
	@RequestMapping(value = { "/app/xiaohao/renewal" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse renewal(HttpServletRequest request,
			@RequestParam(value = "spid", required = true) String spid,
			@RequestParam(value = "m", required = true) String phoneNo,
			@RequestParam(value = "xiaohao", required = true) String virtualNo,
			@RequestParam(value = "orderId", required = true) String orderId,
			@RequestParam(value = "salesId", required = true) String salesId) {
		EResponse finalResp = new EResponse();
		finalResp.setDescription("操作成功!");
		finalResp.setCode(0);
		finalResp.setErrorDescription("操作成功！");
		try {
			//
		} catch (Exception e) {
			logger.error("小号信息查询失败", e);
			return finalResp;
		}
		return finalResp;
	}

	/**
	 * 套餐明细列表
	 * */
	@RequestMapping(value = { "/pyxiaohao/database/xiaohao/tcinfos" }, method = { RequestMethod.GET })
	@ResponseBody
	public EResponse renewal(HttpServletRequest request,
			@RequestParam(value = "xspid", required = true) String xspid) {
		EResponse finalResp = new EResponse();
		finalResp.setDescription("OK");
		finalResp.setCode(0);
		finalResp.setErrorDescription("OK");
		XhTcInfosResponse tcinfos ;
		XhTcMxInfo mxinfos;
		XhKxbInfo kxbinfos;
		List<XhKxbInfo> kxblist;
		List<XhTcMxInfo> mxlist;
		List<XhTcInfosResponse> infolist = new ArrayList<XhTcInfosResponse>();
		List<XhProInfo> proinfolist = null;
		try {
			proinfolist = xhProInfoService.getXhProInfo(xspid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("套餐明细列表查询失败", e);
			finalResp.setDataObject("查询失败！");
			e.printStackTrace();
			return finalResp;
		}
		
		if(proinfolist == null){
			logger.info("没有找到对应的套餐明细列表！");
			finalResp.setDataObject("没有找到对应的套餐明细列表！");
			return finalResp;
		}
		List<String> ids = new ArrayList<String>();
		for(int i=0;i<proinfolist.size();i++){
			mxlist = new ArrayList<XhTcMxInfo>();
			tcinfos = new XhTcInfosResponse();
			mxinfos = new XhTcMxInfo();
			ids.add(proinfolist.get(i).getProduct_id());
			tcinfos.setTcTypeId(proinfolist.get(i).getProduct_id());
			tcinfos.setTcTypeName(proinfolist.get(i).getPro_name());
			mxinfos.setProdCode(proinfolist.get(i).getXh_nbr());
			mxinfos.setTcMxId(tcinfos.getTcTypeId());
			mxinfos.setTcMxName(proinfolist.get(i).getPro_desc());
			mxinfos.setTcMxPrice(proinfolist.get(i).getPro_fee());
			mxinfos.setTcmxname(tcmxname);
			mxlist.add(mxinfos);
		    tcinfos.setTcmxs(mxlist);
			infolist.add(tcinfos);
		}
		
		try {
			proinfolist = xhProInfoService.getXhKxbProInfo(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("套餐明细列表查询失败", e);
			finalResp.setDataObject("查询失败！");
			e.printStackTrace();
			return finalResp;
		}
		
		if(proinfolist.size()>0){
		  for(XhTcInfosResponse tcinfo:infolist){
			  kxblist = new ArrayList<XhKxbInfo>();
			  for(XhProInfo proinfo:proinfolist){
				  if(tcinfo.getTcTypeId().equals(proinfo.getId())){
					  kxbinfos = new XhKxbInfo();
					  kxbinfos.setDescription(proinfo.getPro_desc());
					  kxbinfos.setName(proinfo.getPro_name());
					  kxbinfos.setId(proinfo.getProduct_id());
					  kxbinfos.setType(proinfo.getPro_type());
					  kxblist.add(kxbinfos);
				  }
			  }
			  if(kxblist.size()>0){
				  tcinfo.getTcmxs().get(0).setKxbs(kxblist);
			  }
		  }
		}
		
		
		finalResp.setDataObject(infolist);
		return finalResp;
	}
	
	
}
