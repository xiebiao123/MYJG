package com.soshow.ssi.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soshow.ssi.common.enums.base.BizErrorEnum;
import com.soshow.ssi.common.enums.base.CommonErrorEnum;
import com.soshow.ssi.common.enums.base.StatusEnum;
import com.soshow.ssi.common.util.base.BizException;
import com.soshow.ssi.common.util.base.MyError;
import com.soshow.ssi.common.util.base.MyResponse;
/**
 * 系统通用异常处理
 * @author xieb
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	/**
	 * 处理异常
	 * 
	 * @param request
	 * @param t
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public MyResponse<Void> processThrowable(HttpServletRequest request, Exception t) {

		MyResponse<Void> responseVO = new MyResponse<Void>();
		responseVO.setResponseStatus(StatusEnum.FAIL);
		if (t instanceof MethodArgumentNotValidException) {
			handleException(responseVO, (MethodArgumentNotValidException) t);
		} else if (t instanceof BindException) {
			handleException(responseVO, (BindException) t);
		} else if (t instanceof HttpMessageConversionException) {
			handleException(responseVO, (HttpMessageConversionException) t);
		} else if (t instanceof HttpMediaTypeNotSupportedException) {
			handleException(responseVO, (HttpMediaTypeNotSupportedException) t);
		} else if (t instanceof UnauthorizedException) {
			handleException(responseVO, (UnauthorizedException) t);
		} else if (t instanceof BizException) {
			handleException(responseVO, (BizException) t);
		} else {
			handleException(responseVO, t, request);
		}
		return responseVO;
	}

	protected void handleException(MyResponse<Void> responseVO, MethodArgumentNotValidException e) {
		responseVO.setResponseStatus(StatusEnum.FAIL);
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		processFieldErrors(responseVO, fieldErrors);
	}

	private void processFieldErrors(MyResponse<Void> responseVO, List<FieldError> fieldErrors) {

		for (FieldError fieldError : fieldErrors) {
			MyError errorVO = new MyError();
			// String localizedErrorMessage =
			// resolveLocalizedErrorMessage(fieldError);
			errorVO.setCode(CommonErrorEnum.ERR03.getCode());
			errorVO.setField(fieldError.getField());
			// errorVO.setDesc(localizedErrorMessage);
			if ("typeMismatch".equals(fieldError.getCode())) {
				errorVO.setDesc("输入的参数类型不正确");
			} else {
				errorVO.setDesc(fieldError.getDefaultMessage());
			}
			responseVO.getErrors().add(errorVO);
		}
	}

	protected void handleException(MyResponse<Void> responseVO, BindException e) {
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		processFieldErrors(responseVO, fieldErrors);
	}

	protected void handleException(MyResponse<Void> responseVO, HttpMessageConversionException e) {
		responseVO.setResponseStatus(StatusEnum.FAIL);
		responseVO.addError(CommonErrorEnum.ERR06);
	}

	protected void handleException(MyResponse<Void> responseVO, HttpMediaTypeNotSupportedException e) {
		responseVO.setResponseStatus(StatusEnum.FAIL);
		responseVO.addError(CommonErrorEnum.ERR07);
	}

	protected void handleException(MyResponse<Void> responseVO, UnauthorizedException e) {
		responseVO.setResponseStatus(StatusEnum.FAIL);
		responseVO.addError(CommonErrorEnum.ERR08);
	}

	protected void handleException(MyResponse<?> responseVO, BizException e) {
		responseVO.setResponseStatus(StatusEnum.FAIL);
		if (null != e.getErrorEnum()) {
			responseVO.addError(e.getErrorEnum(), e.getMessageArg());
		} else {
			MyError error = new MyError();
			error.setCode(CommonErrorEnum.ERR01.getCode());
			error.setDesc(e.getMessage());
			responseVO.addError(error);
		}
		if (logger.isInfoEnabled()) {
			logger.info(e.getMessage());
		}
	}

	protected void handleException(MyResponse<?> responseVO,Exception e, HttpServletRequest request) {
		responseVO.addError(BizErrorEnum.ERR02);
		if(logger.isErrorEnabled()) {
			logger.error("系统处理错误："+request.getRequestURI(),e);
		}
	}
}