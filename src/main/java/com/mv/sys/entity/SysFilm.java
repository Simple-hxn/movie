package com.mv.sys.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SysFilm implements Serializable{
	private static final long serialVersionUID = -6881176574887286024L;
	/** 该文件对象的唯一表示id*/
	private Integer id;
	/** 文件名称*/
	private String name;
	/** 文件标签*/
	private String lable;
	/** 上映时间*/
	private String onTime;
	/** 评分*/
	private Double grade;
	/** 介绍*/
	private String introduce;
	/** 文件类型*/
	private Integer categoryId;
	/** 所属地区*/
	private String area;
	/** 文件存储路径*/
	private String route;
	
	private Integer vip;
	/**
	 * 视频存储地址
	 */
	private String voidelink;

	public String getVoidelink() {
		return voidelink;
	}
	public void setVoidelink(String voidelink) {
		this.voidelink = voidelink;
	}


	public Integer getVip() {
		return vip;
	}
	public void setVip(Integer vip) {
		this.vip = vip;
	}
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
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getOnTime() {
		return onTime;
	}
	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}

	public String tostring(){
		return "re"+id+","+name+","+lable+","+onTime+","+grade+","+categoryId+","+area+","+route+","+vip+"]";
	}
}
