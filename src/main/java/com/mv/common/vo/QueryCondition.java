package com.mv.common.vo;

import java.io.Serializable;

public class QueryCondition implements Serializable{
	
	private static final long serialVersionUID = 3290267271654500887L;

	private String filmName;
	
	private String category;
	
	private Integer pageCurrent;
	
	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	
	
}
