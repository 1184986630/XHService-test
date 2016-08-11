package cn.ffcs.xhService.frame.service;

import cn.ffcs.xhService.core.model.ParamsValid;
import cn.ffcs.xhService.core.model.RegexType;
import cn.ffcs.xhService.model.Message;

import java.lang.reflect.Field;

public class ValidateService {
	private static ParamsValid pv;

	public static Message validate(Object object) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields=clazz.getDeclaredFields();
		//遍历属性
		for(Field field:fields){
			//对于private私有化的成员变量，通过setAccessible来修改器访问权限
			field.setAccessible(true);
			Message m = validate(field, object);
			//重新设置会私有权限
			field.setAccessible(false);
			if(m != null && !m.getResult().equals("0")) {
				return m;
			}
		}
		return new Message("0", "ok");
	}

	public static Message validate(Field field, Object object) {
		pv = field.getAnnotation(ParamsValid.class);
		if(pv == null) {
			return null;
		}
		Message m = new Message("0", "ok");
		String desc = pv.description().equals("") ? field.getName() : pv.description();
		Object value = null;
		try {
			value = field.get(object);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(desc.contains("{0}")) {
			desc = desc.replaceAll("\\{0\\}", field.getName());
		} else if(desc.contains("{1}")) {
			desc = desc.replaceAll("\\{1\\}", value.toString());
		}
		try {
			for (RegexType rt : pv.regexType()) {
				switch (rt) {
					case NONE:
						break;
					case NOT_ENMPTY: {
						if (value == null) {
							m.setResult(pv.retCode());
							m.setDetail(desc);
						} else if (value instanceof String) {
							if (((String) value).length() == 0) {
								m.setResult(pv.retCode());
								m.setDetail(desc);
							}
						}
						break;
					}
					case PHONENO: {
						if (value == null) {
							m.setResult(pv.retCode());
							m.setDetail(desc);
						} else if(!value.toString().matches("1\\d{10}")) {
							m.setResult(pv.retCode());
							m.setDetail(desc);
						}
					}
				}
				if(!m.getResult().equals("0")) {
					return m;
				}
			}
			if(!pv.regexExpression().equals("")) {
				if(!value.toString().matches(pv.regexExpression())) {
					m.setResult(pv.retCode());
					m.setDetail(desc);
					return m;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			m.setResult(pv.retCode());
			m.setDetail(desc);
		}
		return m;
	}
}
