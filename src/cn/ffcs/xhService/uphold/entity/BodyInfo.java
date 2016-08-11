package cn.ffcs.xhService.uphold.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 报文内容
 * */
public class BodyInfo {

	private Map<String, Object> fieldData;	// 数据字段
	
	public BodyInfo() {
		fieldData = new HashMap<String, Object>();
	}
	
	public Map<String, Object> getFieldData() {
		return fieldData;
	}
	public void setFieldData(Map<String, Object> fieldData) {
		this.fieldData = fieldData;
	}

	public void put(String key, Object value) {
		fieldData.put(key, value);
	}
	
	private String MaptoXmlString() {
		StringBuilder xml = new StringBuilder();
		Iterator<Entry<String, Object>> iter = fieldData.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			if(val == null) {
				// val = "";
				continue;
			}
			xml.append("<").append(key).append(">");
			if(val instanceof String) {
				xml.append(val);
			} else if(val instanceof List<?>) {
				List<?> v = (List<?>)val;
				for(Object o : v){
					xml.append(o.toString());
				}
			} else {
				xml.append(val.toString());
			}
			xml.append("</").append(key).append(">");
		}
		return xml.toString();
	}

	public String toXmlString() {
		StringBuilder xml = new StringBuilder();
		String className = "Body";
		xml.append("<").append(className).append(">");
		xml.append(MaptoXmlString());
		xml.append("</").append(className).append(">");
		return xml.toString();
	}
}
