package cn.ffcs.xhService.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ffcs.xhService.uphold.entity.Response;
import cn.ffcs.xhService.uphold.entity.UnitedResponse;

/**
 * 消息推送
 * */
public class MessagePushUtil {
	private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	
	/**
	 * 消息推送任务：推送失败，放入队列重推
	 * @param httpClient httpClient
	 * @param host 推送主机/ip
	 * @param port 推送端口
	 * @param addr 推送地址
	 * @param requestInfo 推送消息,json格式
	 * @param taskInfo 任务名称描述
	 * */
    public static Response pushMessageTask(HttpclientUtils httpClient, String host, int port, String addr, String requestInfo, String taskInfo) {
    	logger.debug("=========" + taskInfo +"=========");
    	String returnStr = null; // 应答信息
    	UnitedResponse response = new UnitedResponse();
    	StringEntity se = null;
			
		try {
			se = new StringEntity(requestInfo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("===== 请求参数requestInfo，" + taskInfo + "【转码异常】，requestInfo=====" + requestInfo, e);
			response.setRetCode("2");
			response.setRetMsg("请求参数转码异常");
			return response;
		}
		
		try {
			// 应答报文
			returnStr = httpClient.httpPostJson(host, port, addr, se);
		} catch (Exception e) {
			logger.error("===== " + taskInfo + "【异常】，requestInfo========" + requestInfo, e);
			response.setRetCode("99");
			response.setRetMsg("httpPost失败");
			return response;
		}
		
		logger.debug("===== " + taskInfo + "returnStr=======" + returnStr);
		
		if(StringUtils.isEmpty(returnStr)) {
			logger.info("===== " + taskInfo + "【http请求失败】，returnStr is null ======");
			logger.info("requestInfo=======" + requestInfo);
			response.setRetCode("99");
			response.setRetMsg("httpPost应答为空");
			return response;
		}
		
		try {
			response = (UnitedResponse) UnitedResponse.fromJSONString(returnStr);
			if(response != null) {
				if(response.getRetCode() != null && response.getRetCode().equals("0")) {
					logger.debug("===== " + taskInfo + "【成功】======");
				} else {
					if(response.getData() != null) { // 过期销号推送，有不能销号的，也不重推
						logger.info("===== " + taskInfo + "，retCode='" + 
								response.getRetCode() + "'，retMsg='" + response.getRetMsg() + 
								"'，返回错误列表：" + response.getData());
					} else { // 其他推送，需要重推
						logger.info("===== " + taskInfo + "【失败】，retCode=" + 
								response.getRetCode() + "，retMsg=" + 
								response.getRetMsg() + "，requestInfo=======" + requestInfo + " =======");
						response.setRetCode("99");
						response.setRetMsg("httpPost应答失败");
					}
					
					
					
				}
			} else {
				logger.info("===== " + taskInfo + "【失败】，应答的response is null.");
				logger.info("requestInfo=======" + requestInfo);
				response = new UnitedResponse();
				response.setRetCode("99");
				response.setRetMsg("httpPost应答为空");
			}
		} catch (Exception e) {
			logger.error("===== " + taskInfo + "【异常】，requestInfo=======" + requestInfo, e);
			response.setRetCode("99");
			response.setRetMsg("httpPost异常");
		}
		
		return response;
    }
}
