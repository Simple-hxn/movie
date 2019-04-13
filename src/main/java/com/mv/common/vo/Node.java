package com.mv.common.vo;

import java.io.Serializable;

/**
 * 定义序列化
 */
public class Node implements Serializable{
	private static final long serialVersionUID = 1987059851801274852L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
