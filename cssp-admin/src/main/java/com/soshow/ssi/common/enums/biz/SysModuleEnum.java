package com.soshow.ssi.common.enums.biz;
/**
 * 系统模块枚举
 * code 格式为 sys_model_xxx,xxx为具体模块名称
 *
 */
public enum SysModuleEnum {
	USER("sys_model_user","系统用户"),
	STUDENT("sys_model_student","学生信息")
	;

	private String  code;
	private String desc;
	private SysModuleEnum(String code ,String desc){
		this.code = code;
		this.desc = desc;		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return code + ": " + desc;
	}
	
}
