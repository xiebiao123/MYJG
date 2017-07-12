package com.soshow.ssi.common.enums.biz;
/**
 * 系统用户具体操作枚举
 */
public enum SysActionTypeEnum {
	SIGIN("sys_act_sigin","用户登录"),
	SIGUP("sys_act_sigup","用户注册"),
	FIND_PWD("sys_act_findpwd","用户找回密码"),
	UPDATE_PWD("sys_act_updatepwd","用户找回密码"),
	UPLOAD_HEAD_IMG("sys_act_upload_headimg","用户上传头像"),
	
	STUDENT_FIND_PAGE("sys_act_student_find_page","分页查询学生信息"),
	;
	private  String code;
	private String desc;
	private SysActionTypeEnum(String code,String desc){
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
		
		return "code:"+code+" desc:"+desc;
	}
	
	
}
