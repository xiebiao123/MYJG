package com.soshow.ssi.common.web;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;

import com.soshow.ssi.common.bean.WebSessionUser;
import com.soshow.ssi.common.constant.CommonConstant;
import com.soshow.ssi.common.support.SessionIdGenerator;


/**
 *
 * 重写TxnIdFilter，在返回之前，把txn写到http头，供nginx的access_log输出及手机端使用
 */
public class ResponseTxnFilter extends TxnIdFilter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String prefix = filterConfig.getInitParameter("prefix");
		if(prefix != null) {
			this.setPrefix(prefix);
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		SessionIdGenerator idGenerator = new SessionIdGenerator();
		idGenerator.setPrefix(getPrefix());
		String txnID = idGenerator.nextStringIdentifier();
		String ip = WebSession.getRequestIP();
		MDC.put(CommonConstant.MDC_LOG_KEY_TXN,txnID);
		MDC.put(CommonConstant.MDC_LOG_KEY_IP,ip);
		WebSessionUser sessionUser = WebSession.getWebSessionUser((HttpServletRequest) request);
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setHeader(CommonConstant.MDC_LOG_KEY_TXN, txnID);
		if (null != sessionUser){
			String account = sessionUser.getAccount();
			MDC.put(CommonConstant.MDC_LOG_KEY_ACCOUNT,account);
			httpResponse.setHeader(CommonConstant.MDC_LOG_KEY_ACCOUNT, account);
		}
		try {
			filterChain.doFilter(request, response);
		} finally {			
			MDC.remove(CommonConstant.MDC_LOG_KEY_TXN);
		}
	}
	
	/**
	 * idGenerator的前缀，mobile-web用W，open-web用O，info-web用I，info-job用J
	 */
	private String prefix = "W";

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		// System.out.println("setPrefix=" + prefix);
		this.prefix = prefix;
	}



}
