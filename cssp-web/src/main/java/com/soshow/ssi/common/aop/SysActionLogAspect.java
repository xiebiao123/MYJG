package com.soshow.ssi.common.aop;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.soshow.ssi.common.annotation.SysActionLog;
import com.soshow.ssi.common.bean.WebSessionUser;
import com.soshow.ssi.common.enums.base.StatusEnum;
import com.soshow.ssi.common.enums.biz.SysActionTypeEnum;
import com.soshow.ssi.common.util.SysActionLogUtil;
import com.soshow.ssi.common.util.WebUtil;
import com.soshow.ssi.common.util.base.Response;
import com.soshow.ssi.common.web.WebSession;
import com.soshow.ssi.mongo.dto.SystemUserActionLogDTO;

/**
 * 系统操作日记切面
 * @author wuzhq
 *
 */
@Component
@Aspect
@Order(1)
public class SysActionLogAspect {
	private final Logger loggger = LoggerFactory.getLogger(SysActionLogAspect.class);
	@Resource
	@Qualifier("mongoLogTaskExecutor")
	private ThreadPoolTaskExecutor mongoLogTaskExecutor;
	
	private static ExpressionParser parser = new SpelExpressionParser();
	
	@AfterReturning(pointcut="@annotation(com.soshow.ssi.common.annotation.SysActionLog)&&@annotation(log)",returning="result")
	public void addSucLog(final JoinPoint point,final SysActionLog log,final Object result){
		String ip = WebUtil.getRequestIP();
		WebSessionUser user= WebSession.getWebSessionUser();
		mongoLogTaskExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				addLog(ip,user,point,log,null,result);
				return null;
			}
		});
	}
	
	private  void addLog(String ip,WebSessionUser user, JoinPoint point, SysActionLog log,Exception ex,Object result){
		SystemUserActionLogDTO dto = new SystemUserActionLogDTO();
		Object [] args = point.getArgs();
		Object param = args[0];
		dto.setIp(ip);
		dto.setOptTime(new Date());
		if(null != user){
			dto.setAccount(user.getAccount());
			dto.setUid(user.getUid());
		}
		SysActionTypeEnum actType = log.actType();
		String desc = log.desc();
		if("".equals(desc)){
			desc = actType.getDesc();
		}
		dto.setDesc(desc);

		if(!StringUtils.isEmpty(log.el())){
			Object obj = parser.parseExpression(log.el()).getValue(param);
			System.out.println(obj);
		}
		
		boolean created = true;
		if (result != null && Response.class.isAssignableFrom(result.getClass())) {
			if (!StatusEnum.SUCCESS.getCode().equals(((Response<?>)result).getCode())) {
				created = false;
			}
		}
		if (created)
			SysActionLogUtil.addUserActionLog(log.module(), actType, dto, param, result, ex);
	}
	
	
	
	@AfterThrowing(pointcut="@annotation(com.soshow.ssi.common.annotation.SysActionLog)&&@annotation(log)",throwing="ex")
	public void addExcpLog( final JoinPoint point,final SysActionLog log,final Exception ex){
		String ip = getIp();
		WebSessionUser user= WebSession.getWebSessionUser();
		mongoLogTaskExecutor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				addLog(ip,user,point,log,ex,null);
				return null;
			}
		});
		
	}
	
	
	private String getIp(){
		String ip  = "";
		try {
			ip = WebSession.getRequestIP();
		} catch (UnknownHostException e) {
			loggger.info("SysActionLogAspect.getIp() exception"); 
		}
		return ip;
	}
}
