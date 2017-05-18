package com.soshow.ssi.cssp.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soshow.ssi.cssp.activity.dao.ActivityDao;
import com.soshow.ssi.cssp.activity.dto.ActivityCondition;
import com.soshow.ssi.cssp.activity.dto.ActivityDTO;
import com.soshow.ssi.cssp.activity.service.ActivityService;

/**
 * 活动信息ServiceImpl
 * @version 1.0
 * @author xieb
 * @date 2014年12月9日 下午2:09:48
 *
 */
@Service
public class ActivityServiceImpl implements ActivityService{

	@Resource
	private ActivityDao activityDao;
	
	public Integer add(ActivityDTO po) {
		return activityDao.add(po);
	}
	
	public int delete(Integer id) {
		return activityDao.delete(id);
	}
	
	public int update(ActivityDTO po) {
		return activityDao.updateByCondition(po);
	}
	
	public ActivityDTO findById(Integer id) {
		return activityDao.findById(id);
	}
	
	public List<ActivityDTO> findPageByCondition(ActivityCondition pc) {
		return activityDao.findPageByCondition(pc);
	}
	
	public Long countByCondition(ActivityCondition pc) {
		return activityDao.countByCondition(pc);
	}
}
