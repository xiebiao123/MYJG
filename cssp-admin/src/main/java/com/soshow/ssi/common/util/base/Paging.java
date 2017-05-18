package com.soshow.ssi.common.util.base;

import java.io.Serializable;

/**
 * 分页实体
 * 
 */
public class Paging implements Serializable {

	private static final long serialVersionUID = 1191993639127496965L;
	
	/**
	 * 每页大小的最大值
	 */
	private int maxPageSize = 1000;
	
	/**
	 * 页号
	 */
	private int pageNo;
	
	/**
	 * 每一页数量
	 */
	private int pageSize;
	
	/**
	 * 数据库起始下标（包含）
	 */
	private int startRow;
	
	/**
	 * 数据库结束下标（包含）
	 */
	private int endRow;

	/**
	 * 默认不分页
	 */
	public Paging() {
		this.pageNo = 1;
		this.pageSize = this.maxPageSize;
		cal();
	}

	/**
	 * 指定页号和数量分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 */
	public Paging(int pageNo, int pageSize) {
		if (pageSize > this.maxPageSize) {
			throw new IllegalArgumentException("pageSize can't more than " + this.maxPageSize);
		}
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		cal();
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		cal();
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > this.maxPageSize) {
			throw new IllegalArgumentException("pageSize can't more than " + this.maxPageSize);
		}
		this.pageSize = pageSize;
		cal();
	}

	public void cal() {
		if ((this.pageNo > 0) && (this.pageSize > 0)) {
			int i = this.pageNo * this.pageSize;
			this.startRow = (i - this.pageSize);
			this.endRow = pageSize;
		}
	}

	public int getStartRow() {
		return this.startRow;
	}

	public int getEndRow() {
		return this.endRow;
	}

}