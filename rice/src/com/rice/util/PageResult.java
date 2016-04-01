package com.rice.util;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;
/**
 * 用于存储分页查询结果的POJO类
 * @author Administrator
 *
 */
@DataTransferObject
public class PageResult<T> {
	private Integer rowCount;
	private List<T> data=new ArrayList<T>();
	public PageResult(Integer rowCount, List<T> data) {
		super();
		this.rowCount = rowCount;
		this.data = data;
	}
	public PageResult() {
		super();
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
