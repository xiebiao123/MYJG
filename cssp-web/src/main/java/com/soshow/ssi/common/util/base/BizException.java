package com.soshow.ssi.common.util.base;

import com.soshow.ssi.common.enums.base.ErrorEnum;

/**
 * @author xieb
 *
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -7563054076896663813L;
	
	private ErrorEnum errorEnum; 
		
	private Object[] messageArg;

	public BizException() {
	}
	
	public BizException(String message) {
		super(message);
	}

	public BizException(ErrorEnum errorEnum) {
		super(errorEnum.getCode()+":"+errorEnum.getDescription());
		this.errorEnum = errorEnum;
	}
	public BizException(ErrorEnum errorEnum,Object ...messageArg) {
		super(errorEnum.getCode()+":"+errorEnum.getDescription());
		this.errorEnum = errorEnum;
		this.messageArg = messageArg;
	}

	public BizException(ErrorEnum errorEnum, Exception paramThrowable) {
		super(errorEnum.getCode()+":"+errorEnum.getDescription());
		this.errorEnum = errorEnum;
	}

	public BizException(Exception paramThrowable) {
		super(paramThrowable);
	}

	public ErrorEnum getErrorEnum() {
		return errorEnum;
	}

	public void setErrorEnum(ErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}

	public Object[] getMessageArg() {
		return messageArg;
	}

	public void setMessageArg(Object[] messageArg) {
		this.messageArg = messageArg;
	}
}
