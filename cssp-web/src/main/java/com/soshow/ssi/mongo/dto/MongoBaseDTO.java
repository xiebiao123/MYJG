package com.soshow.ssi.mongo.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
/**
 * 类名:MongoBaseDTO
 * 功能：MongoDB基础查询返回对象
 * @author   xieb
 * @version  1.0
 * @since    v1.0
 * @Date	 2016-12-15 18:04:41
 */
public class MongoBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
	
    @Id
    private String id;			//查询结果返回的key
    private Date dateKey;	    //查询结果返回的日期类型key
    private Integer year;
    private Integer month;
    private Integer week;
    private Integer day;
    private Object value;		//查询结果返回的value
    
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Date getDateKey() {
		return dateKey;
	}
	public void setDateKey(Date dateKey) {
		this.dateKey = dateKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
	