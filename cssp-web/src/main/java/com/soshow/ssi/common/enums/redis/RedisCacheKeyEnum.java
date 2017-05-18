package com.soshow.ssi.common.enums.redis;

public enum RedisCacheKeyEnum {
	PRIMARY_KEY_PREFIX("primary_key_prefix_", "DTO主键key前缀"),
	WEB_SEESION_PREFIX("web_seesion_prefix_","用户会话缓存key前缀"),
	SIGNIN_PASSWORD_RETRY_COUNT_PREFIX("signin_password_retry_count_","用户登录输错密码次数前缀"),
	SIGNIN_USER_ACCOUNT_PREFIX("signin_user_account_prefix_","已注册用户前缀, key=account, value=uid"),
	SEND_SMS_ACCOUNT_COUNT_PREFIX("send_sms_account_count_","用户注册发送短信次数前缀"),
	SIGNUP_SEND_SMS_TOKEN_PREFIX("signup_send_sms_token_","用户注册发送短信token前缀"),
	SIGNIN_SEND_SMS_TOKEN_PREFIX("signin_send_sms_token_","用户登录发送短信token前缀"),
	SIGNIN_SEND_SMS_IP_COUNT_PREFIX("signin_send_sms_ip_count_","用户登录发送短信同ip次数前缀"),
	SIGNIN_SEND_SMS_CODE_PREFIX("signin_send_sms_code_","用户登录发送短信验证码前缀"),
	
	SEND_SMS_CODE_GEE_KEY("send_sms_code_gee_key","gee key"),
	SEND_SMS_CODE_ID_KEY("send_sms_code_id_key","短信验证码id key"),
	SMS_CODE_INFO_CACHE_PREFIX("sms_code_info_cache_","短信验证码缓存信息前缀"),
	SIGNUP_SEND_SMS_IP_COUNT_PREFIX("send_sms_ip_count_","用户注册发送短信同ip次数前缀"),
	SIGNUP_SEND_SMS_CODE_PREFIX("signup_send_sms_code_","用户注册发送短信验证码前缀"),
	FINDPWD_SEND_SMS_IP_COUNT_PREFIX("findpwd_send_sms_ip_count_","找回密码发送短信同ip次数前缀"),
	FINDPWD_SEND_SMS_TOKEN_PREFIX("findpwd_send_sms_token_","找回密码发送短信token前缀"),
	FINDPWD_SEND_SMS_CODE_PREFIX("findpwd_send_sms_code_","找回密码发送短信验证码前缀"),
	FINDPWD_CHECK_TOKEN_PREFIX("findpwd_check_token_","找回密码检验token前缀"),
	UPDATEPWD_CHECK_TOKEN_PREFIX("updatepwd_check_token_","修改密码检验token前缀"),
	QINIU_SENSITIVE_STATUS_UID_PREFIX("qiniu_sensitive_status_uid_","获取用户七牛审核状态前缀"),
	BEHAVIOR_SYSTEM_TAG_PREFIX("behavior_system_tags", "系统行为标签缓存"),
	BEHAVIOR_CUSTOM_TAG_PREFIX("behavior_custom_tags", "系统自定义行为标签缓存"),
	BEHAVIOR_TAG_USE_COUNT_PREFIX("behavior_tag_use_count_", "行为标签计数缓存"),
	TARGET_SYSTEM_TAG_PREFIX("target_system_tags", "系统标签缓存"),
	TARGET_CUSTOM_TAG_PREFIX("target_custom_tags", "自定义标签缓存"),
	TARGET_TAG_USE_COUNT_PREFIX("target_tag_use_count_", "标签计数缓存"),
	COMPLAINT_SAME_TARGET_UID_PREFIX("complaint_same_target_uid", "24小时同一人对被监督人监督前缀"),
	COMPLAINT_IP_COUNT_PREFIX("complaint_ip_count_prefix_", "一段时间内未登录用户创建监督表扬的同ip次数前缀"),
	COMPLAINT_WEB_SESSION_PREFIX("complaint_web_session_prefix_", "一段时间内未登录用户创建监督表扬的token"),
	COMPLAINT_UNAUTH_PREFIX("complaint_unauth_prefix_", "未认证用户创建的complaint主键集, key=uid, value=list of complaint id"),
	COMPLAINT_USER_PREFIX("complaint_user_prefix_", "用户创建的complaint主键集, key=uid, value=list of complaint id"),
	COMPLAINT_OBJECT_PREFIX("complaint_object_prefix_", "创建的complaintbo, key=id, value=bo"),
	SCENCE_CONFIG_PREFIX("scence_config_prefix_", "场景配置表"),

	SYSTEM_GEETEST_SID_INCR("system_login_geetest_sid_incr", "登录gee验证码sid incrKey"),
	SYSTEM_GEETEST_MOBILE_SID_MAPPING_PREFIX("system_geetest_mobile_sid_mapping_", "手机号-sessionId Mapping"),

	GEE_TEST_ERROR_TIMES("system_geetest_error_times","时间段内连续错误次数"),
	GEE_TEST_SUCCESS_TOTAL_COUNT("system_geetest_success_total_count","geetest成功调用总次数"),
	GEE_TEST_FAIL_TOTAL_COUNT("system_geetest_fail_total_count","geetest失败调用总次数"),
	
	KAPTCHA_STORE_PREFIX("kaptcha_store_prefix_", "kaptcha store"),
	SEND_SMS_CODE_KAPTCHA_PREFIX("send_sms_code_kaptcha_prefix_","kaptcha prefix key"),
	
	TEMPLATE_KEY_PREFIX("template_key_prefix_", "站内信模板前缀"),

	;
	private String  key;
	private String desc;
	private  RedisCacheKeyEnum(String  key,String desc){
		this.key = key;
		this.desc = desc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}


}
