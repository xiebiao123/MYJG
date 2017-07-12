package com.soshow.ssi.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.soshow.ssi.common.bean.WebSessionUser;
import com.soshow.ssi.common.constant.CommonConstant;
import com.soshow.ssi.common.enums.base.BizErrorEnum;
import com.soshow.ssi.common.util.base.BizException;

/**
 * 登录校验拦截器
 */
public class UserSigninInterceptor  extends HandlerInterceptorAdapter{
	
	private List<String> ignoreUrls;
	
	private   int getSessionActivityTime(){
//		String str = SpringContextUtil.getConfigService().getConfigValue(RedisConfigKeyEnum.SYSTEM_USER_LOGIN_SESSION_ACTIVITY_TIME.getKey());
//		return Integer.valueOf(str);
		return 60;
	}
	
	private PathMatcher matcher = new AntPathMatcher();
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		MDC.remove(CommonConstant.MDC_LOG_KEY_ACCOUNT);
		MDC.remove(CommonConstant.MDC_LOG_KEY_UID);
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String  url = requestURI.substring(contextPath.length());
		MDC.put(CommonConstant.MDC_LOG_KEY_IP, WebSession.getRequestIP());
		if(null!=ignoreUrls && ignoreUrls.size()>0){
			for(String e : ignoreUrls){
				if(matcher.match(e, url)){
					return true;
				}
			}
		}
		WebSessionUser user= WebSession.getWebSessionUser();
		
		if(null!= user && null!= user.getUid()){
			//延长seesion有效时间
			WebSession.addSessionActivityTime(user, getSessionActivityTime());
			//加入日记记录上下文内容
			MDC.put(CommonConstant.MDC_LOG_KEY_ACCOUNT, user.getAccount());
			MDC.put(CommonConstant.MDC_LOG_KEY_UID, String.valueOf(user.getUid()));
			return true;
		}
		throw new BizException(BizErrorEnum.ERR18);
	}

	public List<String> getIgnoreUrls() {
		return ignoreUrls;
	}

	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}
	
	
}
