package com.soshow.ssi.cssp.student.service;

import java.util.List;

import com.soshow.ssi.cssp.student.dto.StudentCondition;
import com.soshow.ssi.cssp.student.dto.StudentDTO;

/**
 * 学生信息Service
 * @version 1.0
 * @author xieb
 * @date 2014年12月9日 下午2:08:06
 *
 */
public interface StudentService {

	/**
	 * 新增学生信息
	 */
	public Integer add(StudentDTO po);
	/**
	 * 删除学生信息
	 */
	public int delete(Integer id);
	/**
	 * 修改学生信息
	 */
	public int update(StudentDTO po);
	/**
	 * 根据id获取学生信息
	 */
	public StudentDTO findById(Integer id);
	/**
	 * 分页查询学生信息
	 */
	public List<StudentDTO> findPageByCondition(StudentCondition pc);
	
	/**
	 * 统计查询数据的条数
	 */
	public Long countByCondition(StudentCondition pc);
	
}
