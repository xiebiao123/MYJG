package com.soshow.ssi.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * @author shenzh
 * @date 2016-10-01
 */
public interface MongoTemplateDAO<T> {
	
	T add(T dto);
	
	void delete(T dto);
	
	List<T> findPageByCondition(int pageNo,int pageSize,Query query);
	
	long countByCondition(Query query);
	
	List<T> aggregation(Aggregation aggregation);
	
	T findAndModify(Query query,Update update);
	
	List<T> mapReduce(Query query,String mapFunction,String reduceFunction);
	
	List<T> findByCondition(Query query);
}
