package com.soshow.ssi.common.enums.base;

public enum CommonErrorEnum implements ErrorEnum {
	/** 通用错误，具体的错误描述可以在程序中指定 */
	ERR01("comm_err01", "通用错误"),
	/** 系统不能处理 （一般情况是系统有bug） */
	ERR02("comm_err02", "系统异常"),
	/** 参数异常 */
	ERR03("comm_err03", "参数错误"),
	/** api层接口错误 */
	ERR04("comm_err04", "app层接口错误"),
	/** 不支持的请求参数 */
	ERR06("comm_err06", "不支持的请求参数"),
	/** 不支持的媒体类型*/
	ERR07("comm_err07", "不支持的媒体类型"),
	/** 当前登录用户无此权限*/
	ERR08("comm_err08", "当前登录用户无此权限"),
	/** 资源不存在 */
	ERR09("comm_err09", "资源不存在"),
//	
	ERR11("comm_err11", "上传文件出错")
	
	;
	
	
	
	private final String code;
	private final String description;

	private CommonErrorEnum(String code, String description) {
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