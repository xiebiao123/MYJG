package com.soshow.ssi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.soshow.ssi.common.enums.biz.SysActionTypeEnum;
import com.soshow.ssi.common.enums.biz.SysModuleEnum;
/**
 * 系统操作日记注解
 * @author wuzhq
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysActionLog {
	//系统模块
	SysModuleEnum module();
	//用户具体操作
	SysActionTypeEnum actType();
	String desc() default "";
	String el() default "";
}
