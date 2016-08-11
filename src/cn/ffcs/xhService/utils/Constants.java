package cn.ffcs.xhService.utils;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    private static final Logger logger = LoggerFactory.getLogger(Thread
            .currentThread().getStackTrace()[1].getClassName());
    
    //系统配置文件
    private static PropertiesConfiguration config;
    private static String curEnv = "";

    static{
        try {
            config = new PropertiesConfiguration();
            config.setEncoding("UTF-8");
            config.setReloadingStrategy(new FileChangedReloadingStrategy());
            config.load(new InputStreamReader(Constants.class.getClassLoader().getResourceAsStream("config.properties")));
            curEnv = getString("curEnv");
        } catch (ConfigurationException e) {
            logger.error("找不到配置文件", e);
        }
    }

    private static String getString(String key) {
        if(config.containsKey(key + curEnv)) {
            return config.getString(key + curEnv);
        }
        return config.getString(key);
    }

    private static String[] getStringArray(String key) {
        if(config.containsKey(key + curEnv)) {
            return config.getStringArray(key + curEnv);
        }
        return config.getStringArray(key);
    }

    private static Integer getInt(String key) {
        if(config.containsKey(key + curEnv)) {
            return config.getInt(key + curEnv);
        }
        return config.getInt(key);
    }

    // 批量提交数
    public final static int BATCH_DEAL_NUM = 2000;
    
    //状态开关
    public static final int STATUS_ON = 1;
    public static final int STATUS_OFF = 0;
    
    
    // 报文头参数设置
    public final static String HEADER_SPID = getString("header.spid");
    public static String HEADER_APPID = getString("header.appid");
    public final static String HEADER_PASSWD = getString("header.passwd");
    
    // xml声明
    public final static String XML_DECLARE = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
    
    // ims发送地址设置
    public final static String IMS_URL = getString("ims.url");
    public final static String IMS_HOST = getString("ims.host"); // 主机
    public final static String IMS_PORT = getString("ims.port"); // 端口
    
    // 小号主表保留天数
    public static int getXhKeepDays() {
    	if(getInt("xh.keep_days") <= 0) {
    		return 60;
    	}
    	return getInt("xh.keep_days");
    }
    // 小号到期提醒天数配置
    public static String[] getExpireNoticeDays() {
    	return getStringArray("xh.expire.notice.days");
    }
    // 小号销号提醒天数配置
    public static String[] getXhCloseNoticeDays() {
    	return getStringArray("xh.close.notice.days");
    }
    // 统一平台发送地址设置
    public final static String UNITED_PLATFORM_HOST = getString("united.platform.host"); // 主机
    public final static String UNITED_PLATFORM_PORT = getString("united.platform.port"); // 通用端口
    public final static String UNITED_PLATFORM_SMS_PORT = getString("united.platform.sms.port"); // 短信端口
    public final static String UNITED_PLATFORM_ADDRESS_EXPIRENOTICE = getString("united.platform.address.expirenotice"); // 过期提醒地址
    public final static String UNITED_PLATFORM_ADDRESS_EXPIRECLOSE = getString("united.platform.address.expireclose"); // 过期销号地址
    public final static String UNITED_PLATFORM_ADDRESS_CLOSENOTICE = getString("united.platform.address.closenotice"); // 销号提醒地址
    public final static String UNITED_PLATFORM_ADDRESS_EXPIREONEDAYNOTICE = getString("united.platform.address.expireonedaynotice"); // 过期一天提醒地址
    public final static String UNITED_PLATFORM_ADDRESS_XHSMSINFO = getString("united.platform.address.xhsmsinfo"); // 小号短信推送
    public final static String UNITED_PLATFORM_ADDRESS_ENTWARNNOTICE = getString("united.platform.address.entwarnnotice"); // 企业提醒
    
    public static Integer getPlatformSendnum() { // 到期提醒每次个数
    	if(StringUtils.isEmpty(getString("united.platform.sendnum"))) {
    		return 100;
    	}
    	return getInt("united.platform.sendnum");
    }
    public final static String UNITED_PLATFORM_SPID = getString("united.platform.spid"); // 推送的spid
    
    
    // 一条短信允许的短信最大最大字节数
    public static Integer getSmsMaxBytes() {
    	if(StringUtils.isEmpty(getString("sms.max.bytes"))) {
    		return 140;
    	}
    	return getInt("sms.max.bytes");
    }
    
    // 多条短信允许的短信最大最大字节数
    public static Integer getMultiSmsMaxBytes() {
    	if(StringUtils.isEmpty(getString("sms.multi.max.bytes"))) {
    		return 134;
    	}
    	return getInt("sms.multi.max.bytes");
    }
    
    // 短信总共最大字节数
    public static Integer getTotalSmsMaxBytes() {
    	if(StringUtils.isEmpty(getString("sms.total.max.bytes"))) {
    		return 896;
    	}
    	return getInt("sms.total.max.bytes");
    }
    
    // 查询最近的月结账单月份数
    public static Integer getBillSettleMonths() {
    	if(StringUtils.isEmpty(getString("bills.settle.months"))) {
    		return 6;
    	}
    	return getInt("bills.settle.months");
    }
    
    // key
    public final static String VIRTUAL_NO_KEY = getString("virtualno.key"); // 小号key
    public final static String PHONE_NO_KEY = getString("phoneno.key"); // 主号key
    
    // 开关机状态
    public static final int CLOSE_STATUS_1 = 1; // 关机
    public static final int CLOSE_STATUS_2 = 2; // 开始
    public static final int CLOSE_STATUS_3 = 3; // 设置免打扰
    public static final int CLOSE_STATUS_4 = 4; // 删除免打扰
    
    // xiaohao.phone_virtual
    public static String getRedisPhoneVirtual() {
    	if(StringUtils.isEmpty(getString("redis.phone_virtual"))) {
    		return "phone_virtual";
    	}
    	return getString("redis.phone_virtual");
    }
    // xiaohao.virtual_phone
    public static String getRedisVirtualPhone() {
    	if(StringUtils.isEmpty(getString("redis.virtual_phone"))) {
    		return "virtual_phone";
    	}
    	return getString("redis.virtual_phone");
    }
    // xiaohao.xhinfo
    public static String getRedisXhInfo() {
    	if(StringUtils.isEmpty(getString("redis.xhinfo"))) {
    		return "xhinfo";
    	}
    	return getString("redis.xhinfo");
    }
    // xiaohao.phonehdinfo
    public static String getRedisPhoneHdInfo() {
    	if(StringUtils.isEmpty(getString("redis.phonehdinfo"))) {
    		return "phonehdinfo";
    	}
    	return getString("redis.phonehdinfo");
    }
    // redis小号信息存储，key（主号号码&小号号码）分隔符
    public static String REDISXHINFOKEYSPLIT = "&";
    // xiaohao.areacode
    public static String getRedisAreaCode() {
    	if(StringUtils.isEmpty(getString("redis.areacode"))) {
    		return "areacode";
    	}
    	return getString("redis.areacode");
    }
    
    // 账单类型
    public static final String BILLTYPE_1 = "1";
    // 话单类型
    public static final String BILLSUBTYPE_1200 = "1200"; // 通话账单
    public static final String BILLSUBTYPE_1201 = "1201"; // 通话账单
    public static final String BILLSUBTYPE_401 = "401"; // 短信账单
    // 小号账单类型
    public static final int CALLTYPE_1 = 1; // 直拨
    public static final int CALLTYPE_2 = 2; // 被叫
    public static final int CALLTYPE_3 = 3; // 发短信
    public static final int CALLTYPE_4 = 4; // 收短信
    public static final int CALLTYPE_5 = 5; // 未接来电
    public static final int CALLTYPE_USEDFEE = 6; // 号码占用费
    
    // 显示号码前缀
    public static final String DISPLAYNBR_86 = "86"; // 86前缀:86xxxxxxxx
    public static final String SIGN_PLUS = "+" ; // 加号前缀:+xxxxxx
    public static final String DISPLAYNBR_0 = "0"; // 0前缀:0xxxxxxxx
    
    // 业务开通套餐ID
    public static final String POINTSSPID = "100352";
    
    // 是否录音：1：录音 2：不录音
    public static String getIsRecord() {
    	if(StringUtils.isEmpty(getString("xh.open.isrecord"))) {
    		return "2";
    	}
    	return getString("xh.open.isrecord");
    }
    // 是否留言：1:留言 2：不留言（在关机后生效）
    public static String getMessageType() {
    	if(StringUtils.isEmpty(getString("xh.open.leavemsgtype"))) {
    		return "2";
    	}
    	return getString("xh.open.leavemsgtype");
    }
    // 开通业务，1：语音，2：短信，3：同时开通语音和短信
    public static String getOpenType() {
    	if(StringUtils.isEmpty(getString("xh.open.opentype"))) {
    		return "3";
    	}
    	return getString("xh.open.opentype");
    }
    // 支付方式，1：互联网支付，2：IT下账
    public static String getPayType() {
    	if(StringUtils.isEmpty(getString("xh.open.paytype"))) {
    		return "1";
    	}
    	return getString("xh.open.paytype");
    }
    // 是否关机：1:关机 2:开机
    public static String getCloseType() {
    	if(StringUtils.isEmpty(getString("xh.open.closetype"))) {
    		return "2";
    	}
    	return getString("xh.open.closetype");
    }
    
    // 操作成功返回的字符串
    public static final String SUCCESS = "success";
    
    // <![CDATA[ xx ]]> 节点包装
    public static final String CDATA_BEGIN = "<![CDATA[";
    public static final String CDATA_END = "]]>";
    
    // 回调报告类型
    public static final String VTYPE_1 = "1"; // HTTP触发类能力呼叫状态回调推送
    public static final String VTYPE_11 = "11"; // 短信接收推送
    public static final String VTYPE_12 = "12"; //  短信状态接收推送
    public static final String VTYPE_14 = "14"; // 录音和留言类型
    
    // 不属于接口统计的范畴
    public static String[] getInterfaceUsageNotCntType() {
    	String[] arr  = getStringArray("interface.usage.notcnttype");
    	if(arr == null || arr.length == 0) {
    		arr = new String[]{"newuser","onlineuser"};
    	} else {
    		arr = getStringArray("interface.usage.notcnttype");
    	}
    	return arr;
    }
    
  //接口名称映射表
    public final static Map<String, String> INTERFACE_NAME = new HashMap<String, String>(){{
        this.put("openNo", "业务开通");	
//		this.put("queryInfo", "小号信息查询");
		this.put("closeNo", "小号注销");
        this.put("renewal", "小号套餐续订");
//        this.put("packageSyn", "小号套餐同步");
        this.put("dial", "小号直拨");
        this.put("sendSMS", "小号发短信");
        this.put("closeSetting", "开关机、免打扰设置");
//        this.put("queryRecord", "小号留言信息查询");
        this.put("monthlyBill", "小号月账单查询");
        this.put("detailBill", "小号详单查询");
        this.put("getCallRecords", "小号通话记录查询");
        this.put("delCallRecords", "小号通话记录删除");
    }};
    
    // 不允许拨打的以此开头的号码
    public static String[] getNotAllowedDial() {
    	return getStringArray("xh.notallowed.dial");
    }
    // 允许拨打的特殊号码开头
    public static String getSpecialNoDial() {
    	return getString("xh.specialno.dial");
    }
    
    // 不允许接收以此开头号码的短信
    public static String[] getNotAllowedRec() {
    	return config.getStringArray("xh.notallowed.rec");
    }
   // 允许接收以此开头号码的短信
    public static String[] getSpecialNoRec() {
    	return config.getStringArray("xh.specialno.rec");
    }
    
    
    // 通话记录所有标识
    public static final String ALL_CALL_RECORDS = "all";
    
    // rabbitmq配置
    public static String MQ_IP_ADDRESS = getString("mq.ip.address"); // mq服务器IP地址
    public static Integer MQ_PORT = getInt("mq.port"); // mq服务器端口
    public static String MQ_USERNAME = getString("mq.username"); // mq服务器用户名
    public static String MQ_PASSWORD = getString("mq.password"); // mq服务器密码
    public static boolean getMqMsgQueueRecOpen() { // 是否启用读取队列
    	return getString("mq.msg.queue.rec.open").equals("true");
    }
    public static boolean getMqMsgQueueSendOpen() { // 是否启用发送队列
    	return getString("mq.msg.queue.send.open").equals("true");
    }
    // 队列名称
    public static String MQ_QUEUE_COMMU_INFO = "queue_commu_info"; // 账单队列
    public static String MQ_QUEUE_LOG_INFO = "queue_log_info"; // 日志队列
    public static String MQ_QUEUE_HTTPSTATUS_INFO = "queue_httpstatus_info"; // http状态信息队列
    // exchange类型
    public static String MQ_EXCHANGE_TYPE = "direct";
    // exchange名称
    public static String MQ_EXCHANGE_NAME = "direct_logs";
    // 重推队列名称
    public static String MQ_QUEUE_REPUSH_1 = "queue_repush_1"; // 1分钟一次队列
    public static String MQ_QUEUE_REPUSH_3 = "queue_repush_3"; // 3分钟一次队列
    public static String MQ_QUEUE_REPUSH_10 = "queue_repush_10"; // 10分钟一次队列
    
    // 账单隐藏的小号
    public static String BILLS_HIDE_VIRTUALNO = "20147655";
    
    // 账单删除标识
    public static final int BILLS_FLAG_0 = 0; // 未删除
    public static final int BILLS_FLAG_1 = 1; // 标记删除
    
    // ivr状态信息
    public static final String IVR_STATE_CLOSED = "2"; // 主叫推送状态信息：关机
    
    // http状态信息
    public static final String HTTP_CALLSTATE_RINGING = "RINGING"; // 响铃
    public static final String HTTP_CALLSTATE_FAILED = "FAILED"; // 呼叫失败
    public static final String HTTP_CALLSTATE_CANCEL = "CANCEL"; // 取消呼叫
    
    public static final String OPENNO_EXPECT = "postData is empty!";
    
    
    
    // ============================== 企业版参数 ===================================
    // 付费类型
    public static final int ENT_PAYTYPE_PREPAID = 0; // 0:预付费
    public static final int ENT_PAYTYPE_POSTPAID = 1; // 1：后付费
    
    // 充值类型
    public static final int ENT_RECHARGETYPE_NORMAL = 0; // 0:正常充值
    public static final int ENT_RECHARGETYPE_DISSPUTE = 1; // 1：争议账单调整
    
    // 操作类型
    public static final int ENT_OPTYPE_STOP = 1; // 停机
    public static final int ENT_OPTYPE_RECOVER = 2; // 复机
    public static final int ENT_OPTYPE_FREEZE = 3; // 冻结
    public static final int ENT_OPTYPE_UNFREEZE = 4; // 解除冻结
    
    // 账单类型
    public static final int ENT_BILLTYPE_DAY = 0; // 日账单
    public static final int ENT_BILLTYPE_MONTH = 1; // 月账单
    
    // 企业状态
    public static final int ENT_STATUS_NORMAL = 0; // 正常
    public static final int ENT_STATUS_LESSSTOP = 1; // 欠费停机
    public static final int ENT_STATUS_FREEZE = 3; // 冻结
    
    // 通知类型
    public static final String ENT_TYPE_OPEN = "1"; // 开通
    public static final String ENT_TYPE_CLOSE = "2"; // 注销
    public static final String ENT_TYPE_LESSSTOP = "3"; // 停机
    public static final String ENT_TYPE_RECOVER = "4"; // 复机
    
    // 企业告警类型
    public static final int ENT_WARNTYPE_YELLOW = 0; // 黄线
    public static final int ENT_WARNTYPE_RED = 1; // 红线
    public static final int ENT_WARNTYPE_BLACK = 2; // 黑线，即欠停

    // 是否企业定时器
    public static boolean ENTTASK = "true".equals(getString("task.isent"));
    
    // XH_SPXH_[小号]
    public static String getRedisEntXhInfo() {
    	return getString("redis.entxhinfo");
    }
    
    // 企业账单文件存放配置
    public static final String ENT_BILLINFO_FILEPATH = getString("ent.billinfo.filepath");
    // 历史文件
    public static final String ENT_BILLINFO_FILEBACKUP = ENT_BILLINFO_FILEPATH + "backup/";
    // 企业账单文件下载状态
    public static final Integer ENT_BILLFILE_FLAG_SUCCESS = 0; // 下载成功
    public static final Integer ENT_BILLFILE_FLAG_FAIL = 1; // 下载失败
    
    // 查询最近的月结账单月份数
    public static Integer getEntBillSettleMonths() {
    	if(StringUtils.isEmpty(getString("entbills.settle.months"))) {
    		return 6;
    	}
    	return Integer.valueOf(getString("entbills.settle.months"));
    }
    
    // 企业账单查询类型
    public static final Integer ENT_BILL_TYPE_MONTH = 1; // 按月查询
    public static final Integer ENT_BILL_TYPE_YEAR = 2; // 按年查询
    
    
    public final static String BLLWEB_HOST = config.getString("bllweb.host"); // bllweb主机
    public final static String BLLWEB_PORT = config.getString("bllweb.port"); // bllweb端口
    public final static String BLLWEB_SMZ_VER_URL = config.getString("bllweb.smz_ver_url"); // 查询实名制接口
    public final static String BLLWEB_NUMBER_ATTRAREA_ADDRESS = config.getString("bllweb.number_attrarea_address"); // 查询归属地接口
    //支付反馈地址
    public final static String [] addresses = getStringArray("address");
}
