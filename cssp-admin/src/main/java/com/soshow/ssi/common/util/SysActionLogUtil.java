package com.soshow.ssi.common.util;

import java.util.Date;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.soshow.ssi.common.enums.base.BizErrorEnum;
import com.soshow.ssi.common.enums.base.StatusEnum;
import com.soshow.ssi.common.enums.biz.SysActionTypeEnum;
import com.soshow.ssi.common.enums.biz.SysModuleEnum;
import com.soshow.ssi.common.util.base.BizException;
import com.soshow.ssi.mongo.dto.SystemUserActionLogDTO;

/**
 * 用户操作日记工具类
 * @author wuzhq
 *
 */
public class SysActionLogUtil {
	private static final Logger loggger = LoggerFactory.getLogger(SysActionLogUtil.class);
	/**
	 * 写系统日记到mongoDB 
	 * @param module 模块
	 * @param actType 操作类型
	 * @param log 日记对象
	 * @param param 操作入参
	 * @param result 结果
	 * @param ex 异常
	 */
	public static void addUserActionLog(SysModuleEnum module,SysActionTypeEnum  actType,SystemUserActionLogDTO log,Object param,Object result,Exception ex){
		add( module,  actType, log, param,result, ex);
	}
	/**
	 * 写系统日记到mongoDB 
	 * @param module 模块
	 * @param actType 操作类型
	 * @param desc 描述
	 * @param ip  ip 
	 * @param account 账号
	 * @param deviceID 设备号
	 * @param uid uid 
	 * @param param 入参
	 * @param result 结果
	 * @param ex 异常
	 */
	public static void addUserActionLog(SysModuleEnum module,SysActionTypeEnum  actType,String desc,String ip,String account,String deviceID,Long uid,Object param,Object result,Exception ex){
		SpringContextUtil.getMongoLogTaskExecutor().submit(new Callable<Void>(){
			@Override
			public Void call() throws Exception {
				SystemUserActionLogDTO log = new SystemUserActionLogDTO();
				log.setAccount(account);
				log.setUid(uid);
				log.setIp(ip);
				log.setDeviceID(deviceID);
				log.setDesc(desc);
				log.setOptTime(new Date());
				add( module,  actType, log, param,result, ex);
				return null;
			}
			
		});
		
	}
	
	private static  void  add(SysModuleEnum module,SysActionTypeEnum  actType,SystemUserActionLogDTO log,Object param,Object result,Exception ex){
		try{
			log.setModuleCode(module.getCode());
			log.setActionTypeCode(actType.getCode());
			log.setOptTime(new Date());
			if(null!=param){
				log.setParam(JSON.toJSONString(param));
			}
			if(null!= result){
				log.setResult(JSON.toJSONString(result));
			}
			if(null!=ex){
				if(ex instanceof BizException ){
					BizException e = (BizException) ex;
					log.setStatusCode(e.getErrorEnum().getCode());
				}else{
					log.setStatusCode(BizErrorEnum.ERR01.getCode());
				}
			}else{
				log.setStatusCode(StatusEnum.SUCCESS.getCode());
			}
			SpringContextUtil.getSystemUserActionLogDAO().add(log);
		}catch(Exception e){
			loggger.info("SysActionLogUtil.addUserActionLog() error");
		}
	}
	
}
