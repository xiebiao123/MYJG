package com.soshow.ssi.mongo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public abstract class MongodbBaseDao<T> {

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	protected abstract String getCollectionName();

	protected abstract Class<T> getEntityClass();

	protected List<T> findByPage(int pageNo, int pageSize, Query query) {
		query.skip((pageNo - 1) * pageSize);// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录
		return this.find(query);
	}
	
	protected long count(Query query) {
		return mongoTemplate.count(query, this.getCollectionName());
	}

	 /**
     * 通过条件查询实体(集合)
     * 
     * @param query
     */
	protected List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass(),this.getCollectionName());
    }
	
	
	protected void remove(T object,String collectionName){
		mongoTemplate.remove(object,collectionName);
	}

    /**
     * 通过一定的条件查询一个实体
     * 
     * @param query
     * @return
     */
	protected T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass(),getCollectionName());
    }

    /**
     * 查询出所有数据
     * 
     * @return
     */
	protected List<T> findAll() {
        return this.mongoTemplate.findAll(getEntityClass(),getCollectionName());
    }

    /**
     * 查询并且修改记录
     * 
     * @param query
     * @param update
     * @return
     */
	protected T findAndModify(Query query, Update update) {

        return this.mongoTemplate.findAndModify(query, update, this.getEntityClass(),this.getCollectionName());
    }

    /**
     * 按条件查询,并且删除记录
     * 
     * @param query
     * @return
     */
	protected T findAndRemove(Query query) {
        return this.mongoTemplate.findAndRemove(query, this.getEntityClass(),this.getCollectionName());
    }

    /**
     * 通过条件查询更新数据
     * 
     * @param query
     * @param update
     * @return
     */
	protected void updateFirst(Query query, Update update) {
        mongoTemplate.updateFirst(query, update, this.getCollectionName());
    }
	
	
	protected void update(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, this.getCollectionName());
    }

    /**
     * 保存一个对象到mongodb
     * 
     * @param bean
     * @return
     */
	protected T save(T bean) {
        mongoTemplate.save(bean,getCollectionName());
        return bean;
    }

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     * 
     * @param id
     * @param collectionName
     *            集合名
     * @return
     */
	protected T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass(), getCollectionName());
    }
    /**
     * 注入mongodbTemplate
     * 
     * @param mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate){
    	this.mongoTemplate = mongoTemplate;
    }

    /**
     * aggregate
     * @param aggregation
     * @return
     */
    public List<T> aggregate(Aggregation aggregation){
    	AggregationResults<T> results  = mongoTemplate.aggregate(aggregation, this.getCollectionName(), this.getEntityClass());
    	List<T> result = new ArrayList<T>();
    	for (T t : results) {
    		result.add(t);
		}
    	return result;
    }
    
    /**
     * mapReduce
     * @param query
     * @param mapFunction
     * @param reduceFunction
     * @return
     */
    public List<T> mapReduce(Query query,String mapFunction,String reduceFunction){
    	MapReduceResults<T> results = mongoTemplate.mapReduce(query,this.getCollectionName(), mapFunction, reduceFunction, this.getEntityClass());
    	List<T> result = new ArrayList<T>();
    	for (T t : results) {
    		result.add(t);
		}
    	return result;
    }  
}
