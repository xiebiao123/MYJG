package com.soshow.ssi.mongo.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.soshow.ssi.mongo.dao.MongoTemplateDAO;
import com.soshow.ssi.mongo.dao.MongodbBaseDao;


public abstract class MongoTemplateDAOImpl<T> extends MongodbBaseDao<T> implements MongoTemplateDAO<T> {

	@Override
	public T add(T dto) {
		return super.save(dto);
	}

	@Override
	public void delete(T dto) {
		super.remove(dto, getCollectionName());
		
	}
	
	@Override
	public List<T> findPageByCondition(int pageNo,int pageSize,Query query) {
		return super.findByPage(pageNo, pageSize, query);
	}

	@Override
	public List<T> findByCondition(Query query) {
		return super.find(query);
	}
	
	@Override
	public long countByCondition(Query query) {
		return super.count(query);
	}

	@Override
	public List<T> aggregation(Aggregation aggregation){
		return super.aggregate(aggregation);
	}
	
	@Override
	public List<T> mapReduce(Query query,String mapFunction,String reduceFunction){
		return super.mapReduce(query, mapFunction, reduceFunction);
	}
	
	@Override
	public T findAndModify(Query query,Update update) {
		return super.findAndModify(query,update);
	}
}
