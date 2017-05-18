package com.soshow.ssi.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.soshow.ssi.common.cache.RedisTemplate;
import com.soshow.ssi.mongo.dao.MongoTemplateDAO;
import com.soshow.ssi.mongo.dto.SystemUserActionLogDTO;

public class SpringContextUtil  implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringContextUtil.applicationContext = context;
	}
	
	public static RedisTemplate getRedisTemplate(){
		RedisTemplate redisTemplate = (RedisTemplate)getBean("redisTemplate");
		return redisTemplate;
	}
	
//	public static ConfigService getConfigService(){
//		return (ConfigService)getBean("configService");
//	}
	
	@SuppressWarnings("unchecked")
	public static MongoTemplateDAO<SystemUserActionLogDTO> getSystemUserActionLogDAO(){
		MongoTemplateDAO<SystemUserActionLogDTO> dao = (MongoTemplateDAO<SystemUserActionLogDTO>)getBean("systemUserActionLogDAO");
		return dao;
	}
	
	public static ThreadPoolTaskExecutor  getMongoLogTaskExecutor(){
		ThreadPoolTaskExecutor mongoLogTaskExecutor = (ThreadPoolTaskExecutor)getBean("mongoLogTaskExecutor");
		return mongoLogTaskExecutor;
	}
	
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}

	public static String getConfigService() {
		return null;
	}
}
