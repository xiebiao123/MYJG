package com.soshow.ssi.common.enums.base;

public enum BizErrorEnum implements ErrorEnum {
	/** 通用错误，具体的错误描述可以在程序中指定 */
	ERR01("comm_err01", "通用错误"),
	/** 系统不能处理 （一般情况是系统有bug） */
	ERR02("comm_err02", "系统不能处理"),
	/** 参数异常 */
	ERR03("comm_err03", "参数异常"),
	/** api层接口错误 */
	ERR04("comm_err04", "api层接口错误"),
	/** api层接口失败 */
	ERR05("comm_err05", "api层接口失败"),
	/** 不支持的请求参数 */
	ERR06("comm_err06", "不支持的请求参数"),
	/** 不支持的媒体类型*/
	ERR07("comm_err07", "不支持的媒体类型"),
	/** 当前登录用户无此权限*/
	ERR08("comm_err08", "当前登录用户无此权限"),
	/** 资源不存在 */
	ERR09("comm_err09", "资源不存在"),
	ERR10("comm_err10", "用户未认证"),
	ERR11("comm_err11", "加密数据时出错"),
	ERR12("comm_err12", "开始日期不能大于结束日期"),
	ERR13("comm_err13", "信息填写有误"),
	ERR14("comm_err14", "无效的姓名"),
	ERR15("comm_err15", "无效的手机号"),
	ERR16("comm_err16", "身份证号不一致"),
	ERR17("comm_err17", "帐号或密码错误，请重新输入"),
	ERR18("comm_err18", "账号未登录"),
	ERR19("comm_err19", "密码错误次数过多，您的帐号已被锁定，时长为1小时"),
	ERR20("comm_err20", "发送短信验证码超过限制次数"),
	ERR21("comm_err21", "账号已存在"),
	ERR22("comm_err22", "用户操作无权限"),
	ERR23("comm_err23", "此信息已调整发布范围，您暂无权限查看"),
	ERR24("comm_err24", "非法请求"),
	ERR25("comm_err25", "短信验证码错误"),
	ERR26("comm_err26", "当前手机号还未注册"),
	ERR27("comm_err27", "阿里云短信发送异常"),


	ERR28("comm_err28","姓名身份证校验不通过"),
	ERR29("comm_err29","实名认证失败次数过多，您24小时以内暂时不能进行实名认证。"),
	ERR30("comm_err30","系统有点忙，请稍后再试！"),
	//身份认证正在排队中
	ERR31("comm_err31","正在进行身份校验，请耐心等待"),
	ERR32("comm_err32","由于您失败次数过多，为确保您的身份安全，您暂时无法进行实名认证。"),
	ERR33("comm_err33","当前用户未身份认证"),
	//七牛正在审核中
	ERR34("comm_err34","已提交审核，请耐心等待，审核通过后可在“我的记录”中查看"),
	
	ERR35("comm_err35","旧密码错误"),
	ERR36("comm_err36", "验证码错误，请重新输入"),
	ERR37("comm_err37", "验证码已过期"),
	ERR38("comm_err38", "此验证接口不提供服务"),
	
	// 监督/表扬相关
	ERR50("comm_err50", "您今日对该对象的%s已超过%d次上限,请明天后重试"),
	ERR51("comm_err51", "获取验证码在%d分钟内超过限制次数"),
	ERR52("comm_err52", "无效的验证码(token)"),
	ERR53("comm_err53", "没有找到模板记录"),
	
	ERR60("comm_err60", "无效的场景"),
	
	ERR70("comm_err70", "原始密码错误"),
	ERR71("comm_err71", "验证码每天最多发送%d条"),
	ERR72("comm_err72", "解密数据时出错"),
	ERR73("comm_err73", "您提交的次数过多,请您24小时后再提交意见。"),
	ERR74("comm_err74", "您的帐号已被冻结"),
	ERR75("comm_err75", "帐号错误，请重新填写"),
	;
	private final String code;
	private final String description;

	private BizErrorEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
	
}