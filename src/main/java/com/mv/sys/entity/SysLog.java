package com.mv.sys.entity;

import java.util.Date;

public class SysLog {
	/** 日志对象的唯一标识id*/
	private Integer id;
	/** 操作/日志生成时间*/
	private Date createTime;
	/** 用户行为*/
	private String operation;
	/** 用户名称(需保证用户表name属性不可重复)*/
	private String filmName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
}
