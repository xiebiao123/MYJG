package com.soshow.ssi.mongo.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.soshow.ssi.mongo.dto.SystemUserActionLogDTO;
@Service("systemUserActionLogDAO")
public class SystemUserActionLogDAOImpl extends MongoTemplateDAOImpl<SystemUserActionLogDTO> {

	@Override
	protected String getCollectionName() {
		return "tb_sys_user_log";
	}

	@Override
	protected Class<SystemUserActionLogDTO> getEntityClass() {
		return SystemUserActionLogDTO.class;
	}
	
	@Override
	public SystemUserActionLogDTO findById(String id) {
		return super.findById(id);
	}
	
	@Override
	public List<SystemUserActionLogDTO> findPageByCondition(int pageNo, int pageSize, Query query) {
		return super.findPageByCondition(pageNo, pageSize, query);
	}

	@Override
	public long countByCondition(Query query) {
		return super.countByCondition(query);
	}
	
}
