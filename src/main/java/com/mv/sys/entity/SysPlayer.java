package com.mv.sys.entity;

import java.util.Date;

public class SysPlayer {
	/** 演员对象的唯一标识id*/
	private Integer id;
	/** 演员名字*/
	private String name;
	/** 演员出生日期*/
	private Date birthday;
	/** 演员性别 0为女性 1为男性*/
	private Integer gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "SysPlayer [id=" + id + ", name=" + name + ", birthday=" + birthday + ", gender=" + gender + "]";
	}
	
}
