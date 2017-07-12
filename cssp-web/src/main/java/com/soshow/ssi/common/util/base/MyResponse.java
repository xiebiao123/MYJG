package com.soshow.ssi.common.util.base;

import java.util.ArrayList;
import java.util.List;

import com.soshow.ssi.common.enums.CommErrorEnum;
import com.soshow.ssi.common.enums.CommStatusEnum;
import com.soshow.ssi.common.enums.base.ErrorEnum;
import com.soshow.ssi.common.enums.base.StatusEnum;

public class MyResponse<T> {

	/**
	 * tx id
	 */
	private String token;

	/**
	 * response state code
	 * 
	 */
	private String code;
	
	/**
	 * description of state code
	 */
	private String message;

	/**
	 * response data
	 */
	private T data;
	
	/**
	 * errors if there any
	 */
	private List<MyError> errors;

	public MyResponse() {
		//默认成功
		setResponseStatus(StatusEnum.SUCCESS);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String jsonToken) {
		this.token = jsonToken;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<MyError> getErrors() {
		if(null==errors){
			this.setResponseStatus(StatusEnum.FAIL);
			errors = new ArrayList<MyError>();			
		}
		return errors;
	}

	public void addError(MyError error) {
		this.getErrors().add(error);
	}
	
	public void addError(ErrorEnum errorEnum,Object ... messageArg) {
		MyError errorVO = new MyError();
		errorVO.setCode(errorEnum.getCode());
		String message = errorEnum.getDescription();
		if(null != messageArg){
			message = String.format(errorEnum.getDescription(), messageArg);
		}
		errorVO.setDesc(message);
		this.getErrors().add(errorVO);
	}
	
	public void setResponseStatus(StatusEnum statusEnum) {
		this.setCode(statusEnum.getCode());
		this.setMessage(statusEnum.getDescription());
	}

	public void setStatusResponse(CommStatusEnum find) {
		this.setCode(find.getCode());
		this.setMessage(find.getMessage());
	}

	public void setErrorResponse(CommErrorEnum err03) {
		this.setCode(err03.getCode());
		this.setMessage(err03.getMessage());
	}
}