package cn.ffcs.xhService.core.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ParamsValid {
	//提供几种常用的正则验证
	RegexType[] regexType() default {RegexType.NONE};

	//自定义正则验证
	String regexExpression() default "";

	String retCode() default "0";

	/**
	 * 参数或者字段描述,这样能够显示友好的异常信息
	 * 用{0}替代字段名，用{1}替代字段值
	 * 例如test字段的值设置为123，"{0}的值不能为{1}",
	 * 最后显示"test的值不能为123"
	 * @return 错误描述
	 */
	String description() default "";
}
