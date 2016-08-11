package cn.ffcs.xhService.order.model;

import com.alibaba.fastjson.JSON;

/**
 * 小号办理状态表
 */
public class DealOrderXhFlag {

	private String mainPhone;			// 主号
	private String smallPhone;			// 小号
	private String channelId;			// 渠道号
	private Integer flag;				// 标志位
	private String updatedAt;			// 标志位更新时间
	private Integer xhStatus;			// 小号状态
	private String xhStatusUpdatedAt;	// 小号状态更新时间
	
	public String getMainPhone() {
		return mainPhone;
	}
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}
	public String getSmallPhone() {
		return smallPhone;
	}
	public void setSmallPhone(String smallPhone) {
		this.smallPhone = smallPhone;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getXhStatus() {
		return xhStatus;
	}
	public void setXhStatus(Integer xhStatus) {
		this.xhStatus = xhStatus;
	}
	public String getXhStatusUpdatedAt() {
		return xhStatusUpdatedAt;
	}
	public void setXhStatusUpdatedAt(String xhStatusUpdatedAt) {
		this.xhStatusUpdatedAt = xhStatusUpdatedAt;
	}
	 
	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, DealOrderXhFlag.class);
    }
}
