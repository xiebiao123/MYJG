package com.soshow.ssi.cssp.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soshow.ssi.cssp.admin.dao.AdminDao;
import com.soshow.ssi.cssp.admin.dto.AdminCondition;
import com.soshow.ssi.cssp.admin.dto.AdminDTO;
import com.soshow.ssi.cssp.admin.service.AdminService;

/**
 * 管理员ServiceImpl
 * @version 1.0
 * @author xieb
 * @date 2014年12月9日 下午2:09:48
 *
 */
@Service
public class AdminServiceImpl implements AdminService{

	@Resource
	private AdminDao adminDao;
	
	public Integer add(AdminDTO po) {
		return adminDao.add(po);
	}
	
	public int delete(Integer id) {
		return adminDao.delete(id);
	}
	
	public int update(AdminDTO po) {
		return adminDao.updateByCondition(po);
	}
	
	public AdminDTO findById(Integer id) {
		return adminDao.findById(id);
	}
	
	public List<AdminDTO> findPageByCondition(AdminCondition pc) {
		return adminDao.findPageByCondition(pc);
	}
	
	public Long countByCondition(AdminCondition pc) {
		return adminDao.countByCondition(pc);
	}
}
