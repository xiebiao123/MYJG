package com.soshow.ssi.common.util.base;

import com.soshow.ssi.common.enums.base.ErrorEnum;
import com.soshow.ssi.common.enums.base.StatusEnum;

/**
 * 
 * @author wuzhq
 *
 * @param <T>
 */
public class Response<T> {

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
	
	
	public Response() {
		//默认为失败状态
		setResponseStatus(StatusEnum.FAIL);
	}
	
	public Response(ErrorEnum errorEnum) {
		this.code = errorEnum.getCode();
		this.message = errorEnum.getDescription();
	}
	
	public Response(ErrorEnum errorEnum, T data) {
		this.code = errorEnum.getCode();
		this.message = errorEnum.getDescription();
		this.data = data;
	}
	
	public final static <T> Response<T> newSuccess() {
		return new Response<T>(StatusEnum.SUCCESS);
	}
	
	public final static <T> Response<T> newSuccess(T data) {
		return new Response<T>(StatusEnum.SUCCESS, data);
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
	public void setResponseStatus(ErrorEnum errorEnum, Object... msgArgs) {
		this.setCode(errorEnum.getCode());
		this.setMessage(String.format(errorEnum.getDescription(), msgArgs));
	}
}