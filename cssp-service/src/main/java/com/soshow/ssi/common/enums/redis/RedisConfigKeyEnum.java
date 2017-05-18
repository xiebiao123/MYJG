package com.soshow.ssi.common.enums.redis;


public enum RedisConfigKeyEnum {
	//系统相关配置参数
	SYSTEM_USER_LOGIN_SESSION_ACTIVITY_TIME("system:user_login_session_activity_time","user session有效时间，单位秒"),
	SYSTEM_LOGIN_ERROR_PASSWORD_MAXCOUNT("system:login_error_password_maxcount","密码错误最大次数"),
	SYSTEM_LOGIN_ERROR_PASSWORD_LOCKTIME("system:login_error_password_locktime","密码输入错误次数超过上限，账号锁定时间，单位秒"),
	SYSTEM_SEND_SMS_MAXCOUNT("system:send_sms_maxcount","同一手机号一天之内发送短信的最大次数"),
	SYSTEM_SEND_SMS_CODE_ACTIVITY_TIME("system:verify.sms.code.activity.time","短信验证码的有效时间，单位秒"),
	SYSTEM_SIGNUP_SEND_SMS_IP_MAXCOUNT("system:login_send_sms_ip_maxcount","注册获取发送短信token的最大次数"),
	SYSTEM_FINDPWD_SEND_SMS_IP_MAXCOUNT("system:findpwd_send_sms_ip_maxcount","找回密码获取发送短信token的最大次数"),
	SYSTEM_SIGNIN_SEND_SMS_IP_MAXCOUNT("system:signin_send_sms_ip_maxcount","登录获取发送短信token的最大次数"),
	SYSTEM_VERIFY_SMS_CODE_LENGTH("system:verify_sms_code_length","短信验证码长度"),
	SYSTEM_VERIFY_SMS_CODE_SWITCH("system:verify_sms_code_switch","测试短信验证码开关"),
	SYSTEM_ONS_SENDSMS_CONSUMER_MESSAGE_ACTIVITY_TIME("system:ons_sendsms_consumer_message_activity_time","短信验证码消息在队列里面的最大有效时间，单位秒"),
	SYSTEM_IDCARD_AUTH_DAY_LIMIT("system:idcard_auth_day_limit","公安实名认证限制次数/天"),
	SYSTEM_IDCARD_AUTH_TIMEOUT_SECONDS("system:idcard_auth_timeout_seconds","身份认证超时时间 单位秒"),
	SYSTEM_QINIU_KEY_PREFIX("system:qiniu_key_prefix","七牛key前缀，生产 dev,测试 test,生产 pro"),
	SYSTEM_QINIU_LOGO_KEY_PREFIX("system:qiniu_logo_key_prefix","七牛上传头像key前缀"),
	SYSTEM_IDVERIFY_TEST_SWITCH("system:idverify_test_switch","身份认证测试开关 测试：on 真实接口:off"),
	SYSTEM_IDVERIFY_TEST_MAX_TIME("system:idverify_test_max_time","身份认证测试时，mock返回最大耗时"),
	SYSTEM_QINIU_SERVICE_SWITCH("system:qiniu_service_switch","七牛鉴黄新老服务开关 0 旧版服务 1 新版服务"),
	SYSTEM_VERIFY_SERVICE_SWITCH("system:verify_service_switch","审核开关,0 全部通过  1 人工审核  2 七牛审核  3 休眠状态"),
	SYSTEM_SLEEP_TIME_RANGE("system:sleep_time_range","休眠时间范围，单位毫秒"),
	SYSTEM_QINIU_RESPON_TIMES("system:qiniu_respon_times","七牛请求鉴黄接口次数"),
	SYSTEM_QINIU_PICTURE_MANUAL("system:qiniu_picture_manual","七牛图片涉黄阀值"),
	SYSTEM_QINIU_PICTURE_INVOLVE("system:qiniu_picture_involve","七牛图片性感阀值"),
	SYSTEM_QINIU_PICTURE_NORMAL("system:qiniu_picture_normal","七牛图片正常阀值"),
	SYSTEM_QINIU_PICTURE_ROTE("system:qiniu_picture_rote","七牛图片类型概率值"),
	SYSTEM_QINIU_POLITIC_MANUAL("system:qiniu_politic_manual","敏感词涉政阀值"),
	SYSTEM_QINIU_REACTION_MANUAL("system:qiniu_reaction_manual","敏感词涉黄阀值"),
	SYSTEM_QINIU_PORN_MANUAL("system:qiniu_porn_manual","敏感词反动阀值"),
	SYSTEM_IOS_VERIFY_TOKEN("system:ios_verify_token","苹果审核环境token"),
	SYSTEM_QINIU_UPLOAD_URL("system:qiniu_upload_url","七牛上传地址"),
	SYSTEM_QINIU_VERIFY_RANGE("system:qiniu_verify_range","七牛鉴黄范围, 0 图片+文本,1 只鉴图片,2 只鉴文本"),
	SYSTEM_APP_VERSION_IOS("system:app_version_ios","ios当前版本"),
	SYSTEM_APP_VERSION_ANDROID("system:app_version_android","android当前版本"),
	SYSTEM_APP_UPDATE_URL_IOS("system:app_update_url_ios","ios当前版本跟新地址"),
	SYSTEM_APP_UPDATE_URL_ANDROID("system:app_update_url_android","android当前版本跟新地址"),
	SYSTEM_APP_UPDATE_DESC_IOS("system:app_update_desc_ios","ios当前版本跟新说明"),
	SYSTEM_APP_UPDATE_DESC_ANDROID("system:app_update_desc_android","android当前版本跟新说明"),
	SYSTEM_APP_MUST_UPGRADE("system:app_must_upgrade","是否需要强制升级，0、不需要，1、需要"),
	
	SYSTEM_QINIU_JS_LOG_KEY_PREFIX("system:qiniu_js_log_key_prefix","七牛上传前端日志前缀"),
	SYSTEM_QINIU_ANDROID_LOG_KEY_PREFIX("system:qiniu_android_log_key_prefix","七牛上传android日志前缀"),
	SYSTEM_QINIU_IOS_LOG_KEY_PREFIX("system:qiniu_ios_log_key_prefix","七牛上传ios日志前缀"),
	SYSTEM_QINIU_VIDEO_KEY_PREFIX("system:qiniu_video_key_prefix","七牛上传视频key前缀"),
	//评论相关配置参数
	COMMENT_USER_ANONY_KEY("comment:user_anony_key","评论匿名头像key"),
	COMMENT_USER_MAN_KEY("comment:user_man_key","评论男头像key"),
	COMMENT_USER_WOMAN_KEY("comment:user_woman_key","评论女头像key"),
	//评价或举报相关配置参数
	COMPLAINT_SAMEUID_ALIVE("complaint:sameuid_alive","同一举报人对被举报人存活时间 单位秒"),
	COMPLAINT_SAMEUID_MAXCOUNT("complaint:sameuid_maxcount","同一举报人对被举报人存活时间内最多举报个数"),
	COMPLAINT_VERITY_TIMEOUT_SECONDS("complaint:verity_timeout_seconds","七牛鉴黄超时时间单位秒"),
	COMPLAINT_HOTTOP_COUNT("complaint:hottop_count","对热门举报/表扬要显示的个数,表示每一种"),
	COMPLAINT_UNLOGIN_TOKEN_TIMEOUT("complaint:unlogin_token_timeout","未登录用户创建监督token存活时间单位秒"),
	COMPLAINT_UNLOGIN_SAMEIP_TIMEOUT("complaint:unlogin_sameip_timeout","未登录用户在一段时间同个IP段创建监督存活时间单位秒"),
	COMPLAINT_UNLOGIN_SAMEIP_COUNT("complaint:unlogin_sameip_count","未登录用户在一段时间同个IP段创建监督最大数量"),
	//意见反馈相关配置
	FEEDBACK_CREATE_DAY_LIMIT("feedback:create_day_limit","意见反馈限制次数/天"),

	GEE_TEST_SWITCH("system:geetest_switch","geetest图形验证码开关"),
	GEE_TEST_ERROR_TIMESECTION("system:geetest_error_timesection","统计连续错误次数的时间段 单位分钟"),
	GEE_TEST_WARNING_ERROR_TIMES("system:geetest_warning_error_times","需要报警的连续错误次数"),
	GEE_TEST_MONITOR_MOBILES("system:geetest_warning_monitor_mobiles","接收报警的手机号,英文逗号分隔"),
	GEE_TEST_CAPTCHA_METHOD_SWITCH("system:geetest_captcha_method_switch", "gee or captcha切换, 默认captcha"),

	;
	private static final String MODULE = "config";
	private String  key;
	private String desc;
	private  RedisConfigKeyEnum(String  key,String desc){
		this.key = MODULE.concat(":").concat(key);
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
	
	public static void main(String[] args) {
		System.out.println(RedisConfigKeyEnum.COMMENT_USER_ANONY_KEY.getKey());
	}
	
}
