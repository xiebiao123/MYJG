/*
 * Copyright 2010 Mttang.com All right reserved. This software is the
 * confidential and proprietary information of Mttang.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Mttang.com.
 */
package com.soshow.ssi.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

import com.soshow.ssi.common.constant.CommonConstant;
import com.soshow.ssi.common.support.SessionIdGenerator;

/**
 * 类TxnIdFilter.java的实现描述：TODO 类实现描述 
 */
public class TxnIdFilter implements Filter {


    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        SessionIdGenerator idGenerator = new SessionIdGenerator();
        idGenerator.setPrefix("W");
        String txnID = idGenerator.nextStringIdentifier();
        try {
            MDC.put(CommonConstant.MDC_LOG_KEY_TXN,txnID);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CommonConstant.MDC_LOG_KEY_TXN);
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
