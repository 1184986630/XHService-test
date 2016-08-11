package cn.ffcs.xhService.utils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.ffcs.xhService.core.dao.RedisDao;
import cn.ffcs.xhService.enterprise.entity.UnitedWarnRequest;
import cn.ffcs.xhService.enterprise.model.EntDetailBillInfo;
import cn.ffcs.xhService.enterprise.model.EntInfo;
import cn.ffcs.xhService.enterprise.model.EntPackageInfo;
import cn.ffcs.xhService.enterprise.model.EntRechargeInfo;
import cn.ffcs.xhService.enterprise.model.EntXhInfo;
import cn.ffcs.xhService.enterprise.service.EntInfoService;
import cn.ffcs.xhService.enterprise.service.EntPackageInfoService;
import cn.ffcs.xhService.enterprise.service.EntXhInfoService;
import cn.ffcs.xhService.uphold.entity.Request;
import cn.ffcs.xhService.uphold.entity.Response;
import cn.ffcs.xhService.uphold.entity.UnitedRequestInfo;
import cn.ffcs.xhService.uphold.service.ImsService;

/**
 * 账户余额管理：扣费/充值，同时判断是否需要告警
 * */
@Component(value="AccountManageComponent")
public class AccountManageComponent {
	private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	
	@Resource
	private RedisDao redisDao;
	
	@Resource(name = "EntInfoServiceImpl")
	private EntInfoService entInfoService;
	
	@Resource(name = "EntXhInfoServiceImpl")
	private EntXhInfoService entXhInfoService;
	
	@Resource(name = "ImsServiceImpl")
	private ImsService imsService;
	
	@Resource(name="RedisOperationUtils")
	private RedisOperationUtils redisOperationUtils;
	
	@Resource(name = "EntPackageInfoServiceImpl")
	private EntPackageInfoService entPackageInfoService;
	
	/**
	 * 账户余额修改
	 * @param fee Long 扣除的费用（充值，请传负数）
	 * @param rechargeType Integer 充值类型，充值时需要
	 * */
	public synchronized Response feeOperation(String spid, String appid, Long fee, Integer rechargeType, List<EntDetailBillInfo> detailInfo) {
		Response resp = new Response();
		
		// 判断企业是否存在
		List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
		if(entInfoList == null || entInfoList.size() == 0) {
			resp.setRetCode("251");
			resp.setRetMsg("企业不存在");
			return resp;
		}
		EntInfo entInfo = entInfoList.get(0);
		
		// 重置账户余额
		entInfo.setAccount(entInfo.getAccount()-fee);
		// 判断是否需要修改企业状态
		long reAccount = entInfo.getAccount() + entInfo.getCredits(); // 可消费余额=余额+信用额度
		boolean needCaas = false; // 是否需要调用caas修改接口
		// 充值后余额+信用额度>0，并且当前状态是欠停，修改状态为正常，同时调用caas企业状态修改接口
		if(fee < 0 && reAccount > 0 && entInfo.getStatus() == Constants.ENT_OPTYPE_STOP) {
			entInfo.setStatus(Constants.ENT_STATUS_NORMAL);
			needCaas = true;
		}
		// 充值需要重置推送标志位
		if(fee < 0) {
			entInfo.setPushStatus(0);
		}
		
		// 修改企业信息表的账户余额
		try {
			if(fee < 0) { // 充值，除了修改企业余额，还需要增加充值记录
				EntRechargeInfo entRechargeInfo = new EntRechargeInfo();
				entRechargeInfo.setSpId(entInfo.getSpId());
				entRechargeInfo.setAppId(entInfo.getAppId());
				entRechargeInfo.setRechargeType(rechargeType);
				entRechargeInfo.setAmount(-fee);
				String createTime = DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS);
				entRechargeInfo.setCreateTime(createTime);
				// 修改企业信息，同时增加充值记录
				entInfoService.updateEntChargeInfo(entInfo.getSpId(), entInfo.getAppId(), entInfo.getStatus(), fee, entRechargeInfo);
			} else { // 修改推送状态和账户余额
				entInfoService.updateEntInfo(entInfo.getSpId(), entInfo.getAppId(), entInfo.getStatus(), entInfo.getPushStatus(), fee, detailInfo);
			}
		} catch (Exception e) {
			logger.error("企业扣费失败", e);
			resp.setRetCode("200");
			resp.setRetMsg("企业扣费失败");
			return resp;
		}
		
		// 如果是扣费操作需要告警判断
		if(fee >= 0) {
			warnOperation(entInfo, fee);
		}
		// 需要调用caas通知复机
		if(needCaas) {
			resp = caasOperation(entInfo, Constants.ENT_TYPE_RECOVER);
			if(!resp.getRetCode().equals("0")) {
				return resp;
			}
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		return resp;
	}
	
	/**
	 * 告警操作
	 * */
	public void warnOperation(EntInfo entInfo, Long fee) {
		// 判断是否需要告警
		long reAccount = entInfo.getAccount() + entInfo.getCredits(); // 可消费余额=余额+信用额度
		// 阀值
		double yWarn = 0; // 黄线
		double rWarn = 0; // 红线
		double bWarn = 0; // 黑线
		// 付费类型
		if(entInfo.getPayType() == Constants.ENT_PAYTYPE_PREPAID) { // 预付费:数值
			yWarn = entInfo.getYellowThreshold();
			rWarn = entInfo.getRedThreshold();
		} else { // 后付费:百分比
			yWarn = 1.0 * entInfo.getCredits() * entInfo.getYellowThreshold() / 100;
			rWarn = 1.0 * entInfo.getCredits() * entInfo.getRedThreshold() / 100;
		}
		
		boolean needSave = false; // 是否需要保存到数据库和redis
		int bPushFlag = 0; // 推送标志：0，未推送；1，已推送。
		int pushStatus = entInfo.getStatus(); // 推送状态
		int position = 0; // 位置
		
		// 推送地址信息
		HttpclientUtils httpClient = new HttpclientUtils();
		String host = Constants.UNITED_PLATFORM_HOST;
		int port = Integer.valueOf(Constants.UNITED_PLATFORM_PORT);
		String addr = Constants.UNITED_PLATFORM_ADDRESS_ENTWARNNOTICE;
		// 电渠提醒信息
		UnitedRequestInfo requestInfo = new UnitedRequestInfo();
		UnitedWarnRequest warnRequest = new UnitedWarnRequest();
		warnRequest.setSpId(entInfo.getSpId());
		warnRequest.setAppId(entInfo.getAppId());
		warnRequest.setSpName(entInfo.getSpName());
		warnRequest.setBusinessLinkNo(entInfo.getBusinessLinkno());
		warnRequest.setEntLinkNo(entInfo.getEntLinkno());
		warnRequest.setWarningTime(DateToolkit.pareseDate(new Date(), DateToolkit.YYYY_MM_DD_HH24_MM_SS));
		warnRequest.setAccount(entInfo.getAccount());
		warnRequest.setCredits(entInfo.getCredits());
		// 通知类型
		String opentype = "";
		
		// 判断是否需要告警
		if(reAccount <= bWarn) { // 欠停告警
			position = 3;
			bPushFlag = getBit(pushStatus, position);
			if(bPushFlag == 0) { // 未推送
				String taskInfo = "企业黑线告警提醒";
				logger.debug("======== "+taskInfo+" ========");
				warnRequest.setWarningType(Constants.ENT_WARNTYPE_BLACK);
				requestInfo.setRequest(warnRequest);
				
				// 通知caas
				opentype = Constants.ENT_TYPE_LESSSTOP; // 欠停
				Response res = caasOperation(entInfo, opentype);
				if(res != null && res.getRetCode().equals("0")) { // caas通知成功后，通知电渠
					// 通知电渠
					res = MessagePushUtil.pushMessageTask(httpClient, host, port, addr, requestInfo.toJSONString(), taskInfo);
					if(res != null && res.getRetCode().equals("0")) { // 通知电渠成功
						// 通知成功后修改标志位
						int flag = setBit(pushStatus, position, 1);
						entInfo.setPushStatus(flag);
						entInfo.setStatus(Constants.ENT_STATUS_LESSSTOP); // 设置欠停
						needSave = true;
					} else {
						logger.error("===== spid=" + entInfo.getSpId() + ", appid=" + entInfo.getAppId() + 
								", " + taskInfo + "，通知电渠失败 =====");
						logger.error("warnRequest=" + requestInfo.toJSONString() + ", retCode=" + res.getRetCode() + ", retMsg=" + res.getRetMsg());
					}
				} else {
					logger.error("===== spid=" + entInfo.getSpId() + ", appid=" + entInfo.getAppId() + 
							", " + taskInfo + "，通知caas失败 =====");
					logger.error("warnRequest=" + requestInfo.toJSONString() + ", retCode=" + res.getRetCode() + ", retMsg=" + res.getRetMsg());
				}
			}
		} else if(reAccount <= rWarn) { // 红线告警
			position = 2;
			bPushFlag = getBit(pushStatus, position);
			if(bPushFlag == 0) { // 位推送
				String taskInfo = "企业红线告警提醒";
				logger.debug("======== "+taskInfo+" ========");
				warnRequest.setWarningType(Constants.ENT_WARNTYPE_RED);
				requestInfo.setRequest(warnRequest);
				
				// 通知电渠
				Response res = MessagePushUtil.pushMessageTask(httpClient, host, port, addr, requestInfo.toJSONString(), taskInfo);
				if(res != null && res.getRetCode().equals("0")) { // 通知成功
					// 通知成功后修改标志位
					int flag = setBit(pushStatus, position, 1);
					entInfo.setPushStatus(flag);
					needSave = true;
				} else {
					logger.error("===== spid=" + entInfo.getSpId() + ", appid=" + entInfo.getAppId() + 
							", " + taskInfo + "，通知caas失败 =====");
					logger.error("warnRequest=" + requestInfo.toJSONString() + ", retCode=" + res.getRetCode() + ", retMsg=" + res.getRetMsg());
				}
			}
		} else if(reAccount <= yWarn) { // 黄线告警
			position = 1;
			bPushFlag = getBit(pushStatus, position);
			if(bPushFlag == 0) { // 未推送
				String taskInfo = "企业黄线告警提醒";
				logger.debug("======== "+taskInfo+" ========");
				warnRequest.setWarningType(Constants.ENT_WARNTYPE_YELLOW);
				requestInfo.setRequest(warnRequest);
				
				// 通知电渠
				Response res = MessagePushUtil.pushMessageTask(httpClient, host, port, addr, requestInfo.toJSONString(), taskInfo);
				if(res != null && res.getRetCode().equals("0")) { // 通知成功
					// 通知成功后修改标志位
					int flag = setBit(pushStatus, position, 1);
					entInfo.setPushStatus(flag);
					needSave = true;
				} else {
					logger.error("===== spid=" + entInfo.getSpId() + ", appid=" + entInfo.getAppId() + 
							", " + taskInfo + "，通知电渠失败 =====");
					logger.error("warnRequest=" + requestInfo.toJSONString() + ", retCode=" + res.getRetCode() + ", retMsg=" + res.getRetMsg());
				}
			}
		}
		
		if(needSave) { // 保存到数据库
			try {
				entInfoService.updateEntInfo(entInfo.getSpId(), entInfo.getAppId(), entInfo.getStatus(), entInfo.getPushStatus(), fee, null);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}
	
	/**
	 * caas接口调用
	 * */
	public Response caasOperation(EntInfo entInfo, String opentype) {
		Response resp = new Response();
		// ims接口调用
		Response res = imsService.entIntf(entInfo.getSpId(), entInfo.getAppId(), entInfo.getPwd(), opentype,
				null, null, null, null, null, null, null, null, null,
				null, null, null, new Date()); // 复机
		
		if(res.getRetCode() == null || !res.getRetCode().equals("0")) {
			resp.setRetCode("150");
			resp.setRetMsg(res.getRetMsg());
			logger.error("企业ID=" + entInfo.getSpId() + "，应用ID=" + entInfo.getAppId() + "，调用IMS企业小号通知失败：" + res.getRetMsg());
			return resp;
		}
		
		resp.setRetCode("0");
		resp.setRetMsg("操作成功");
		return resp;
	}
	
	/**
	 * 设置位
	 * @param flag 源
	 * @param position 位置
	 * @param value 设置值
	 * */
	public static int setBit(int flag, int position, int value) {
		return (flag & ~(1<<(position -1))) | (value << (position - 1));
	}
	
	/**
	 * 获取位
	 * @param flag 源
	 * @param postion 位置
	 * @return
	 * */
	public static int getBit(int flag, int position) {
		return flag >> (position - 1) & 1;
	}
	
	/**
	 * 获得ims请求字符串
	 * */
	public String getImsRequest(String spid, String appid, String ims, String key, String telNo,
			String telNoKey, String opentype) {
		Request sendReq = new Request();
		// 重置SPID，APPID，密码
		sendReq.getHeaderInfo().setSpid(spid);
		sendReq.getHeaderInfo().setAppid(appid);
		sendReq.getHeaderInfo().setPasswd(""); // 密码传空
		sendReq.setMethodName("XH_INTF"); // 接口名
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);
		sendReq.put("Telno", telNo);
		sendReq.put("TelnoKey", telNoKey);
		sendReq.put("AbilityType", opentype);
		
		return sendReq.toXmlString();
	}
	
	/**
     * 费用计算
     * @param spid 企业ID
     * @param appid 应用ID
     * @param duration 使用时长/点数
     * @param calltype 通话类型
     * @return long
     * */
    public long calcuFee(String spid, String appid, String virtualno, int duration, int calltype) {
    	long fee = 0;
    	
    	if(StringUtils.isEmpty(spid) || StringUtils.isEmpty(appid) || StringUtils.isEmpty(virtualno) || duration <= 0) {
    		return fee;
    	}
    	if(calltype == Constants.CALLTYPE_1 || calltype == Constants.CALLTYPE_2) { // 拨打或接听，使用时长不足60s计1分钟。
    		if(duration%60 == 0) {
    			duration = duration/60;
        	} else {
        		duration = duration/60+1;
        	}
    	}
    	
    	// 企业信息
    	List<EntInfo> entInfoList = entInfoService.getEntInfo(spid, appid);
    	if(entInfoList == null || entInfoList.size() == 0) {
    		logger.info("企业信息不存在, spid=" + spid + ", appid=" + appid);
			return fee;
    	}
    	EntInfo entInfo = entInfoList.get(0);
    	
    	// 企业小号信息
    	EntXhInfo entXhInfo = redisOperationUtils.getRedisEntXhInfo(entInfo.getSpId(), entInfo.getAppId(), virtualno);
    	if(entXhInfo == null) {
    		logger.info("企业小号信息不存在, spid=" + spid + ", appid=" + appid + ", virtualno=" + virtualno);
			return fee;
    	}
    	
    	// 企业套餐信息
    	List<EntPackageInfo> entPkInfoList = entPackageInfoService.getEntPackageInfo(entXhInfo.getPkNBR());
    	if(entPkInfoList == null || entPkInfoList.size() == 0) {
    		logger.info("企业小号套餐信息不存在, spid=" + spid + ", appid=" + appid + ", virtualno=" + virtualno + ", pknbr=" + entXhInfo.getPkNBR());
			return fee;
    	}
    	EntPackageInfo entPkInfo = entPkInfoList.get(0);
    	
    	if(calltype == Constants.CALLTYPE_1) { // 拨打
    		fee = Math.round(1.0 * duration * entPkInfo.getCallerFee() * entInfo.getDialDiscount() / 100);
    	} else if(calltype == Constants.CALLTYPE_2) { // 接听
    		fee = Math.round(1.0 * duration * entPkInfo.getCalledFee() * entInfo.getAnswerDiscount() / 100);
    	} else if(calltype == Constants.CALLTYPE_3) { // 短信
    		fee = Math.round(1.0 * duration * entPkInfo.getSmsFee() * entInfo.getSmsDiscount() / 100);
    	}
		
		return fee;
    }
    public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
