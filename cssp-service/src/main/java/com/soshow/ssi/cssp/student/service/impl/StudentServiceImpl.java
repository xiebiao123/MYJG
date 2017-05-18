package com.soshow.ssi.cssp.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soshow.ssi.cssp.student.dao.StudentDao;
import com.soshow.ssi.cssp.student.dto.StudentCondition;
import com.soshow.ssi.cssp.student.dto.StudentDTO;
import com.soshow.ssi.cssp.student.service.StudentService;

/**
 * 学生信息ServiceImpl
 * @version 1.0
 * @author xieb
 * @date 2014年12月9日 下午2:09:48
 *
 */
@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentDao studentDao;
	
	public Integer add(StudentDTO po) {
		return studentDao.add(po);
	}
	
	public int delete(Integer id) {
		return studentDao.delete(id);
	}
	
	public int update(StudentDTO po) {
		return studentDao.updateByCondition(po);
	}
	
	public StudentDTO findById(Integer id) {
		return studentDao.findById(id);
	}
	
	public List<StudentDTO> findPageByCondition(StudentCondition pc) {
		return studentDao.findPageByCondition(pc);
	}
	
	public Long countByCondition(StudentCondition pc) {
		return studentDao.countByCondition(pc);
	}
}
