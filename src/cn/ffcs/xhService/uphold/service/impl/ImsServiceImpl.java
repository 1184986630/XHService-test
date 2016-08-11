package cn.ffcs.xhService.uphold.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.ffcs.xhService.uphold.entity.Request;
import cn.ffcs.xhService.uphold.entity.Response;
import cn.ffcs.xhService.uphold.entity.SessionidResponse;
import cn.ffcs.xhService.uphold.service.ImsService;
import cn.ffcs.xhService.utils.Constants;
import cn.ffcs.xhService.utils.Function;
import cn.ffcs.xhService.utils.HttpclientUtils;

@Service("ImsServiceImpl")
public class ImsServiceImpl implements ImsService {
	
	private static final Logger logger = LoggerFactory.getLogger(Thread
			.currentThread().getStackTrace()[1].getClassName());

	private HttpclientUtils httpClient = new HttpclientUtils();
	
	/**
	 * 小号业务开通接口
	 * */
	public Response xhBusiOpen(String ims, String key, String telNo,
			String telNoKey, String openType, String payType,
			String closeBegin, String closeEnd, String closeType, String isRecord,
			String leavemsgType, String pointsSpId, Date date) { // ..
		Request sendReq = new Request();
		sendReq.setMethodName("XH_OPEN_SELF"); // 接口名
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);
		sendReq.put("Telno", telNo);
		sendReq.put("TelnoKey", telNoKey);
		sendReq.put("OpenType", openType);
		sendReq.put("PayType", payType);
		sendReq.put("CloseBegin", closeBegin);
		sendReq.put("CloseEnd", closeEnd);
		sendReq.put("CloseType", closeType);
		sendReq.put("IsRecord", isRecord);
		sendReq.put("LeavemsgType", leavemsgType);
		sendReq.put("PointsSpId", pointsSpId );

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		Response response = new Response();
		
		try {
			logger.debug("小号业务开通sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml);
		} catch (UnsupportedEncodingException e) {
			logger.error("小号业务开通sendXml=" + sendXml);
			logger.error("小号业务开通IMS数据转码异常", e);
			response.setRetCode("101");
			response.setRetMsg("小号业务开通IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
		} catch (Exception e) {
			logger.error("小号业务开通sendXml=" + sendXml);
			logger.error("小号业务开通调用IMS失败", e);
			response.setRetCode("101");
			response.setRetMsg("http请求失败.");
			return response;
		}

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("小号业务开通sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("101");
			response.setRetMsg("http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("小号业务开通sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("101");
			response.setRetMsg("小号业务开通返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("小号业务开通sendXml=" + sendXml);
			response.setRetCode("101");
			response.setRetMsg("小号业务开通返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("小号业务开通sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS业务开通失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg("操作成功");
		}
		
		return response;
	}
	
	/**
	 * 小号直拨接口
	 * */
	public Response xhDial(String ims, String key, String calleeNbr, Date date) { // ..
		Request sendReq = new Request();
		sendReq.setMethodName("XH_DIAL"); // 接口名
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);
		sendReq.put("CalleeNbr", calleeNbr);

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		Response response = new Response();
		
		try {
			logger.debug("小号直拨sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml);
		} catch (UnsupportedEncodingException e) {
			logger.error("小号直拨sendXml=" + sendXml);
			logger.error("小号直拨IMS数据转码异常", e);
			response.setRetCode("103");
			response.setRetMsg("小号直拨IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
		} catch (Exception e) {
			logger.error("小号直拨sendXml=" + sendXml);
			logger.error("小号直拨调用IMS失败", e);
			response.setRetCode("103");
			response.setRetMsg("http请求失败.");
			return response;
		}
		 

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("小号直拨sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("103");
			response.setRetMsg("http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("小号直拨sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("103");
			response.setRetMsg("小号直拨返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("小号直拨sendXml=" + sendXml);
			response.setRetCode("103");
			response.setRetMsg("小号直拨返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("小号直拨sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS直拨失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg(resultDesc);
		}
		
		return response;
	}
	
	/**
	 * 小号业务注销接口
	 * */
	public Response xhClose(String ims, String key, Date date) { // ..
		Request sendReq = new Request();
		sendReq.setMethodName("XH_CLOSE"); // 接口名
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		Response response = new Response();
		
		try {
			logger.debug("小号业务注销sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml);
		} catch (UnsupportedEncodingException e) {
			logger.error("小号业务注销sendXml=" + sendXml);
			logger.error("小号注销IMS数据转码异常", e);
			response.setRetCode("102");
			response.setRetMsg("小号注销IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
		} catch (Exception e) {
			logger.error("小号业务注销sendXml=" + sendXml);
			logger.error("小号业务注销调用IMS异常", e);
			response.setRetCode("102");
			response.setRetMsg("http请求失败.");
			return response;
		}

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("小号业务注销sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("102");
			response.setRetMsg("小号业务注销http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("小号业务注销sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("102");
			response.setRetMsg("小号业务注销返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("小号业务注销sendXml=" + sendXml);
			response.setRetCode("102");
			response.setRetMsg("小号业务注销返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("小号业务注销sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS小号注销失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg("操作成功");
		}
		
		return response;
	}
	
	/**
	 * 发送短信
	 * */
	public SessionidResponse sendSms(String ims, String key, String calleeNbr,
			String content, Date date) { // ..
		Request sendReq = new Request();
		sendReq.setMethodName("SmsSendSelf"); // 接口名
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);
		sendReq.put("CalleeNbr", calleeNbr);
		sendReq.put("SmsContent", content);

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		SessionidResponse response = new SessionidResponse();
		
		try {
			logger.debug("小号发短信sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("小号发短信sendXml=" + sendXml);
			logger.error("小号发送短信IMS数据转码异常", e);
			response.setRetCode("105");
			response.setRetMsg("小号发送短信IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
			logger.debug("returnStr=" + returnStr);
		} catch (Exception e) {
			logger.error("小号发短信sendXml=" + sendXml);
			logger.error("小号发短信调用IMS异常", e);
			response.setRetCode("105");
			response.setRetMsg("http请求失败.");
			return response;
		}

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("小号发短信sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("105");
			response.setRetMsg("小号发送短信http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("小号发短信sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("105");
			response.setRetMsg("小号发送短信返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		String sessionid = Function.getResultValue(returnStr, "Smsid"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("小号发短信sendXml=" + sendXml);
			response.setRetCode("105");
			response.setRetMsg("小号发送短信返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("小号发短信sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS发短信失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg("操作成功");
		}
		response.setSessionid(sessionid);
		
		return response;
	}
	
	/**
	 * 小号业务信息修改
	 * */
	public Response updateXhBusinessinfo(String virtualNo, String key,
			String closeBegin, String closeEnd, String closeType,
			String isRecord, String leavemsgType, Date date) { // ..
		Request sendReq = new Request();
		sendReq.setMethodName("XH_EDIT"); // 接口名
		sendReq.put("Ims", virtualNo);
		sendReq.put("Key", key);
		sendReq.put("CloseBegin", closeBegin);
		sendReq.put("CloseEnd", closeEnd);
		sendReq.put("CloseType", closeType);
		sendReq.put("IsRecord", isRecord);
		sendReq.put("LeavemsgType", leavemsgType);

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		Response response = new Response();
		
		try {
			logger.debug("小号业务信息修改sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("小号业务信息修改sendXml=" + sendXml);
			logger.error("小号业务信息修改IMS数据转码异常", e);
			response.setRetCode("104");
			response.setRetMsg("小号业务信息修改IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
		} catch (Exception e) {
			logger.error("小号业务信息修改sendXml=" + sendXml);
			logger.error("小号业务信息修改调用IMS异常", e);
			response.setRetCode("104");
			response.setRetMsg("http请求失败.");
			return response;
		}

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("小号业务信息修改sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("104");
			response.setRetMsg("http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("小号业务信息修改sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("104");
			response.setRetMsg("小号业务修改返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("小号业务信息修改sendXml=" + sendXml);
			response.setRetCode("104");
			response.setRetMsg("小号业务修改返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("小号业务信息修改sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS业务信息修改失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg("操作成功");
		}
		
		return response;
	}

	/**
	 * 企业小号业务操作
	 * */
	public Response entIntf(String spid, String appid, String pwd, String type, String ims, 
			String key, String telNo, String telNoKey, String openType, String payType,
			String closeBegin, String closeEnd, String closeType,
			String isRecord, String leavemsgType, String pointsSpId, Date date) {
		Request sendReq = new Request();
		sendReq.setMethodName("XH_SELF"); // 接口名
		sendReq.getHeaderInfo().setSpid(spid);
		sendReq.getHeaderInfo().setAppid(appid);
		sendReq.getHeaderInfo().setPasswd(pwd);
		sendReq.put("Type", type);
		sendReq.put("Ims", ims);
		sendReq.put("Key", key);
		sendReq.put("Telno", telNo);
		sendReq.put("TelnoKey", telNoKey);
		sendReq.put("OpenType", openType);
		sendReq.put("PayType", payType);
		sendReq.put("CloseBegin", closeBegin);
		sendReq.put("CloseEnd", closeEnd);
		sendReq.put("CloseType", closeType);
		sendReq.put("IsRecord", isRecord);
		sendReq.put("LeavemsgType", leavemsgType);
		sendReq.put("PointsSpId", pointsSpId );

		StringEntity se;
		String sendXml = sendReq.toXmlString(); // 发送的报文
		Response response = new Response();
		
		try {
			logger.debug("企业小号业务操作sendXml=" + sendXml);
			se = new StringEntity("postData=" + sendXml);
		} catch (UnsupportedEncodingException e) {
			logger.error("企业小号业务操作sendXml=" + sendXml);
			logger.error("企业小号业务操作IMS数据转码异常", e);
			response.setRetCode("101");
			response.setRetMsg("企业小号业务操作IMS数据转码异常");
			return response;
		}
		// 应答报文
		String returnStr = null;
		try {
			returnStr = httpClient.httpPost(
					Constants.IMS_HOST, Integer.valueOf(Constants.IMS_PORT),
					Constants.IMS_URL, se);
		} catch (Exception e) {
			logger.error("企业小号业务操作sendXml=" + sendXml);
			logger.error("企业小号业务操作调用IMS失败", e);
			response.setRetCode("101");
			response.setRetMsg("http请求失败.");
			return response;
		}

		if(StringUtils.isEmpty(returnStr)) {
			logger.error("企业小号业务操作sendXml=" + sendXml);
			logger.error("returnStr is null.");
			response.setRetCode("101");
			response.setRetMsg("http请求失败.");
			return response;
		}
		
		boolean isXML = Function.isXmlValid(returnStr); // 是否xml
		if (!isXML) {
			logger.error("企业小号业务操作sendXml=" + sendXml);
			logger.error("返回的报文=" + returnStr);
			response.setRetCode("101");
			response.setRetMsg("企业小号业务操作返回的报文格式有错.");
			return response;
		}
		String resultCode = Function.getResultValue(returnStr, "Result"); // 获取Result报文内容
		String resultDesc = Function.getResultValue(returnStr, "ResultDesc"); // 获取ResultDesc报文内容
		if(resultCode == null) { // 没找到节点，或者报错
			logger.error("企业小号业务操作sendXml=" + sendXml);
			response.setRetCode("101");
			response.setRetMsg("企业小号业务操作返回的报文节点获取失败.");
			return response;
		}
		if (!resultCode.equals("0")) { // 非0，失败。
			logger.error("企业小号业务操作sendXml=" + sendXml);
			response.setRetCode(resultCode);
			response.setRetMsg(resultDesc);
			logger.error("IMS业务操作失败，返回的resultCode=" + resultCode + "，resultDesc=" + resultDesc);
		} else { // 0，成功
			response.setRetCode("0");
			response.setRetMsg("操作成功");
		}
		
		return response;
	}
}
