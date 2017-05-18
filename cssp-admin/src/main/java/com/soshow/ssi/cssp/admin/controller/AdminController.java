package com.soshow.ssi.cssp.admin.controller;

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
import com.soshow.ssi.cssp.admin.dto.AdminCondition;
import com.soshow.ssi.cssp.admin.dto.AdminDTO;
import com.soshow.ssi.cssp.admin.service.AdminService;

/**
 * 管理员controller
 * @author xieb
 * @version 1.00
 * 2016/03/21
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController{

    private final Logger logger = Logger.getLogger(AdminController.class);
	
	@Resource
	private AdminService adminService;
	
	/**
	 *通过id查找管理员
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MyResponse<AdminDTO> findCityById(@PathVariable Integer id) {
		MyResponse<AdminDTO> response = new MyResponse<AdminDTO>();
		try {
			AdminDTO admin= adminService.findById(id);
			response.setData(admin);
			response.setStatusResponse(CommStatusEnum.FIND);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *添加管理员
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public MyResponse<Void> addCity(@Valid @RequestBody AdminDTO admin) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			adminService.add(admin);
			response.setStatusResponse(CommStatusEnum.ADD);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *删除管理员
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public MyResponse<Void> deleteCity(@PathVariable Integer id) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			adminService.delete(id);
			response.setStatusResponse(CommStatusEnum.DELETE);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *修改管理员
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public MyResponse<Void> updateCity(@Valid @RequestBody AdminDTO admin) {
		MyResponse<Void> response = new MyResponse<Void>();
		try {
			adminService.update(admin);
			response.setStatusResponse(CommStatusEnum.UPDATE);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}

	/**
	 *通过条件分页查询管理员
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public MyResponse<List<AdminDTO>> findCityPageByCondition(AdminCondition condition) {
		MyResponse<List<AdminDTO>> response = new MyResponse<List<AdminDTO>>();
		try {
			/*初始化分页查询*/
			if(condition.getPageSize()>0){
				condition.cal();
			}
			long count =  adminService.countByCondition(condition);
			if(count==0){
				response.setStatusResponse(CommStatusEnum.NOFIND);
				return response;
			}
			response.setToken(count+"");
			List<AdminDTO> adminList = adminService.findPageByCondition(condition);
			response.setData(adminList);
			response.setStatusResponse(CommStatusEnum.FIND);
		} catch (Throwable t) {
			logger.error("系统错误", t);
			response.setErrorResponse(CommErrorEnum.Err03);
		}
		return response;
	}
}
