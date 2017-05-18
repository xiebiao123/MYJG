package com.soshow.ssi.cssp.activity.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soshow.ssi.common.enums.CommErrorEnum;
import com.soshow.ssi.common.enums.CommStatusEnum;
import com.soshow.ssi.common.util.base.MyResponse;
import com.soshow.ssi.cssp.activity.dto.ActivityCondition;
import com.soshow.ssi.cssp.activity.dto.ActivityDTO;
import com.soshow.ssi.cssp.activity.service.ActivityService;

/**
 * 活动信息controller
 * @author xieb
 * @version 1.00
 * 2016/03/21
 */
@Controller
@RequestMapping(value = "activity")
public class ActivityController{

    private final Logger logger = Logger.getLogger(ActivityController.class);
	
	@Resource
	private ActivityService activityService;
	
	/**
	 *通过id查找活动信息
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MyResponse<ActivityDTO> findCityById(@PathVariable Integer id) {
		MyResponse<ActivityDTO> response = new MyResponse<ActivityDTO>();
		try {
			ActivityDTO activity= activityService.findById(id);
			response.setData(activity);
			response.setStatusResponse(CommStatusEnum.FIND);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *添加活动信息
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public MyResponse<Void> addCity(@Valid @RequestBody ActivityDTO activity) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			activityService.add(activity);
			response.setStatusResponse(CommStatusEnum.ADD);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *删除活动信息
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public MyResponse<Void> deleteCity(@PathVariable Integer id) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			activityService.delete(id);
			response.setStatusResponse(CommStatusEnum.DELETE);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *修改活动信息
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public MyResponse<Void> updateCity(@Valid @RequestBody ActivityDTO activity) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			activityService.update(activity);
			response.setStatusResponse(CommStatusEnum.UPDATE);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *通过条件分页查询活动信息
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public MyResponse<List<ActivityDTO>> findCityPageByCondition(ActivityCondition condition) {
		MyResponse<List<ActivityDTO>> response = new MyResponse<List<ActivityDTO>>();
		try {
			/*初始化分页查询*/
			if(condition.getPageSize()>0){
				condition.cal();
			}
			long count =  activityService.countByCondition(condition);
			if(count==0){
				response.setStatusResponse(CommStatusEnum.NOFIND);
				return response;
			}
			response.setToken(count+"");
			List<ActivityDTO> activityList = activityService.findPageByCondition(condition);
			response.setData(activityList);
			response.setStatusResponse(CommStatusEnum.FIND);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}
}
