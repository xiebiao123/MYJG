package com.soshow.ssi.job.exception;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;

/**
 * 通用job异常类
 * @author xieb
 */
public class MyElasticJobExceptionHandler implements JobExceptionHandler {

	@Override
	public void handleException(String jobName, Throwable cause) {
		System.out.println(jobName+"::"+cause);
	}
    
}