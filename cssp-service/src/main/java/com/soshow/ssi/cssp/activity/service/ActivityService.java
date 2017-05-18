package com.soshow.ssi.cssp.activity.service;

import java.util.List;

import com.soshow.ssi.cssp.activity.dto.ActivityCondition;
import com.soshow.ssi.cssp.activity.dto.ActivityDTO;

/**
 * 活动信息Service
 * @version 1.0
 * @author xieb
 * @date 2014年12月9日 下午2:08:06
 *
 */
public interface ActivityService {

	/**
	 * 新增活动信息
	 */
	public Integer add(ActivityDTO po);
	/**
	 * 删除活动信息
	 */
	public int delete(Integer id);
	/**
	 * 修改活动信息
	 */
	public int update(ActivityDTO po);
	/**
	 * 根据id获取活动信息
	 */
	public ActivityDTO findById(Integer id);
	/**
	 * 分页查询活动信息
	 */
	public List<ActivityDTO> findPageByCondition(ActivityCondition pc);
	
	/**
	 * 统计查询数据的条数
	 */
	public Long countByCondition(ActivityCondition pc);
	
}
