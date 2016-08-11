package cn.ffcs.xhService.uphold.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 页面数据
 * */
public class PageData {
	private Object list; // 返回的数据
	private Integer page; // 当前页码
	private Integer total; // 总记录数
	private Long dailTotal; // 月累计呼出时长，单位：秒
	private Long dialTimes; // 月累计呼出次数          
	private Long answerTotal; // 月累计接听时长，单位：秒
	private Long answerTimes; // 月累计接听次数          
	private Long SMSTotal; // 月累计短信数   
	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String toJSONString() {
    	return JSON.toJSONString(this);
    }
    
    public static Object fromJSONString(String jsonString) {
		return JSON.parseObject(jsonString, PageData.class);
    }

	public Long getDailTotal() {
		return dailTotal;
	}

	public void setDailTotal(Long dailTotal) {
		this.dailTotal = dailTotal;
	}

	public Long getDialTimes() {
		return dialTimes;
	}

	public void setDialTimes(Long dialTimes) {
		this.dialTimes = dialTimes;
	}

	public Long getAnswerTotal() {
		return answerTotal;
	}

	public void setAnswerTotal(Long answerTotal) {
		this.answerTotal = answerTotal;
	}

	public Long getAnswerTimes() {
		return answerTimes;
	}

	public void setAnswerTimes(Long answerTimes) {
		this.answerTimes = answerTimes;
	}

	@JSONField(name="SMSTotal")
	public Long getSMSTotal() {
		return SMSTotal;
	}

	public void setSMSTotal(Long SMSTotal) {
		this.SMSTotal = SMSTotal;
	}

}
