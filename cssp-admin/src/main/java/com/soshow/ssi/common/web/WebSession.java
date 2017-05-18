package com.soshow.ssi.common.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.util.StringUtils;
import com.soshow.ssi.common.bean.WebSessionUser;
import com.soshow.ssi.common.cache.RedisTemplate;
import com.soshow.ssi.common.enums.redis.RedisCacheKeyEnum;
import com.soshow.ssi.common.util.AESEncrypter;
import com.soshow.ssi.common.util.EncryptUtil;
import com.soshow.ssi.common.util.SpringContextUtil;

public class WebSession {
	private static final Logger logger = LoggerFactory.getLogger(WebSession.class);
	
	public static final String WEB_SESSION_ID = "wmcy_web_session_id";
	
	public static final String WEB_SESSION_TOKEN = "wmcy_web_session_token";
	
	public static final String COMPLAINT_WEB_SESSION_TOKEN = "wmcy_complaint_web_session_token";
	
	private static final String  USER_AGENT_CHANEL_FLAG = "Chl";
	
	private static final String  USER_AGENT_DEVICEID_FLAG = "Did";
	
	public static Long getUid(){
		try{
			WebSessionUser user = getWebSessionUser();
			if(null != user){
				return  user.getUid();
			}
		}catch (Exception e){
			logger.error("WebSession.getUid() error!",e);
		}
		return null;
	}
	
	
	public static WebSessionUser getWebSessionUser(){
		return getWebSessionUser(null);
	}

	public static WebSessionUser getWebSessionUser(HttpServletRequest request){
		try{
			String seesionId = null;
			Cookie id = null;
			Cookie token = null;
			if(request == null){
				id = getCookie(WEB_SESSION_ID);
				token = getCookie(WEB_SESSION_TOKEN);
			}else {
				id = getCookie(request,WEB_SESSION_ID);
				token = getCookie(request,WEB_SESSION_TOKEN);
			}

			String seesionToken = null;
			if(null!=id && null!=token){
				String idValue = id.getValue();
				seesionToken = token.getValue();
				seesionId = AESEncrypter.decrypt(idValue, seesionToken.getBytes());
			}
			if(null!=seesionId){
				RedisTemplate redisTemplate = SpringContextUtil.getRedisTemplate();
				WebSessionUser user = (WebSessionUser)redisTemplate.get(RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+seesionId);
				if(null!=user && seesionToken.equals(user.getSessionToken())){
					return user;
				}
			}
		}catch (Exception e){
			logger.error("WebSeesion.getWebSeesionUser() error!",e);
		}
		return null;
	}

	
	
	public static String getSessionUserCacheKey(){
		Long uid = getUid();
		return getSessionUserCacheKey(uid);
	}
	
	public static String getSessionUserCacheKey(Long uid){
		try{
			String md5Uid = EncryptUtil.md5(String.valueOf(uid));
			return RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+md5Uid;
			
		}catch (Exception e){
			logger.error("WebSession.getSessionUserCacheKey() error!",e);
		}
		return null;
	}

	public static void setWebSessionUser(WebSessionUser user, int seconds) throws Exception{
		
		String md5Uid = EncryptUtil.md5(String.valueOf(user.getUid()));
		String sessionToken = UUID.randomUUID().toString().replaceAll("-", "");
		String sessionId = AESEncrypter.encrypt(md5Uid, sessionToken.getBytes());
		HttpServletResponse response = getHttpServletResponse();
		Cookie sessionTokenCookie =  new Cookie(WEB_SESSION_TOKEN,sessionToken);
		sessionTokenCookie.setPath("/");
		sessionTokenCookie.setHttpOnly(true);
		sessionTokenCookie.setMaxAge(seconds);
		response.addCookie(sessionTokenCookie);
		
		Cookie sessionIdCookie =  new Cookie(WEB_SESSION_ID,sessionId);
		sessionIdCookie.setPath("/");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(seconds);
		response.addCookie(sessionIdCookie);
		
		user.setSessionToken(sessionToken);
		SpringContextUtil.getRedisTemplate().setex(RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+md5Uid, seconds, user);
	}
	
	public  static void addSessionActivityTime(WebSessionUser user, int seconds){
		String md5Uid = EncryptUtil.md5(String.valueOf(user.getUid()));
		SpringContextUtil.getRedisTemplate().setex(RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+md5Uid, seconds, user);
	}
	
	
	public static void clearSessionUser(Long uid ) {
		String md5Uid = EncryptUtil.md5(String.valueOf(uid));
		SpringContextUtil.getRedisTemplate().del(RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+md5Uid);
	}
	
	public static void clearSeesionUserAndCookie(Long uid){
		String md5Uid = EncryptUtil.md5(String.valueOf(uid));
		HttpServletResponse response = getHttpServletResponse();
		Cookie sessionTokenCookie =  new Cookie(WEB_SESSION_TOKEN,null);
		sessionTokenCookie.setPath("/");
		sessionTokenCookie.setHttpOnly(true);
		sessionTokenCookie.setMaxAge(0);
		response.addCookie(sessionTokenCookie);
		
		Cookie sessionIdCookie =  new Cookie(WEB_SESSION_ID,null);
		sessionIdCookie.setPath("/");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(0);
		response.addCookie(sessionIdCookie);
		SpringContextUtil.getRedisTemplate().del(RedisCacheKeyEnum.WEB_SEESION_PREFIX.getKey()+md5Uid);
	}
	
	
	public  static void clearSeesionUserAndCookie( ){
		Long uid =  getUid();
		clearSeesionUserAndCookie(uid);
	}
	
	
	public static Cookie getCookie(String name){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie :cookies){
				if(name.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}
	private static Cookie getCookie(HttpServletRequest request,String name){
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
			for(Cookie cookie :cookies){
				if(name.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}
	
	public static String getRequestIP() throws UnknownHostException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			String ips = request.getHeader("X-Forwarded-For");
			if(null != ips){
				String[] ipArr = ips.split(",");
				int postion = 3;
				int len = ipArr.length;
				if(len > 0){
					if(len >= postion){
						ip = ipArr[len - postion];
					}else{
						ip = ipArr[0];
					}
				}
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if("127.0.0.1".equals(ip)){
				ip = InetAddress.getLocalHost().getHostAddress();
			}
		}
		if(null!=ip){
			int index = ip.indexOf(",");
			if(index > 0){
				ip = ip.substring(0, index);
			}
		}
		return ip;
	}
	
	public static String getUserAgent() throws UnknownHostException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String ua = request.getHeader("user-agent");
		return ua;
	}
	/**
	 * 
	 * @param type 1 取渠道 ，2 取设备编号
	 * @return
	 */
	public static String getChannelSourceOrDeviceIDFromUserAgent( int type){
		String result = null;
		try{
			String ua = getUserAgent();
			if(StringUtils.isEmpty(ua)){
				return result;
			}
			String[] arrs  = ua.split(" ");
			int len = arrs.length;
			String deviceIDStr = arrs[len-1];
			String chanelStr = arrs[len-2];
			if(1==type){//取渠道
				String[] arr = chanelStr.split("/");
				int num = arr.length;
				if(2==num&&USER_AGENT_CHANEL_FLAG.equals(arr[0])){
					result = arr[1];
				}
				if(StringUtils.isEmpty(result)){
					String lowerUA = ua.toLowerCase();
					if(lowerUA.contains("gzjd/")){//android是带的GZJD,ios是带的gzjd
						result = "-1";
					}else{
						result = "h5";
					}
				}
				
			}else if(2==type){//取设备id
				String[] arr = deviceIDStr.split("/");
				int num = arr.length;
				if(2==num&&USER_AGENT_DEVICEID_FLAG.equals(arr[0])){
					result = arr[1];
				}
			}
		}catch(Exception e){
			logger.error("WebSeesion.getChanelSourceOrDeviceIDFromUserAgent() error!",e);
		}
		return result;
	}
	
	public static HttpServletResponse  getHttpServletResponse(){
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	
	public static int getComplaintsInSameIpCount() throws UnknownHostException {
		String key = RedisCacheKeyEnum.COMPLAINT_IP_COUNT_PREFIX.getKey() + getRequestIP();
		Integer count = SpringContextUtil.getRedisTemplate().get(key);
		return count != null ? count : 0;
	}
	
	public static void setComplaintInSameIpCount(int count, int seconds) throws UnknownHostException {
		String key = RedisCacheKeyEnum.COMPLAINT_IP_COUNT_PREFIX.getKey() + getRequestIP();
		SpringContextUtil.getRedisTemplate().setex(key, seconds, count);
	}
	
	public static void setComplaintWebSession(String token, int seconds) {
		String key = RedisCacheKeyEnum.COMPLAINT_WEB_SESSION_PREFIX.getKey()+token;
		SpringContextUtil.getRedisTemplate().setex(key, seconds, Boolean.TRUE);
	}
	
	public static Boolean getComplaintWebSession(String token) {
		return SpringContextUtil.getRedisTemplate().get(RedisCacheKeyEnum.COMPLAINT_WEB_SESSION_PREFIX.getKey()+token); 
	}
	
	public static void clearComplaintWebSession(String token) {
		String key = RedisCacheKeyEnum.COMPLAINT_WEB_SESSION_PREFIX.getKey()+token;
		SpringContextUtil.getRedisTemplate().del(key);
	}
}
