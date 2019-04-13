package com.mv.sys.entity;

import java.util.Date;

public class SysUser {
	/** 用户对象的唯一标识id*/
	private Integer id;
	/** 用户名称*/
	private String name;
	/** 用户密码*/
	private String password;
	/** 用户是否为VIP 0表示否定  非0(建议为1)表示肯定*/
	private Integer vip;
	/** 用户是否为管理员 0表示否定  非0(建议为1)表示肯定*/
	private Integer conservator;
	/**
	 * 盐值
	 */
	private String salt;

	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCreateUser() {

		return createUser;
	}
	public void setCreateUser(String createUser) {

		this.createUser = createUser;
	}
	public Date getCreateTime() {

		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public String getUpdateUser() {

		return updateUser;
	}
	public void setUpdateUser(String updateUser) {

		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {

		return updateTime;
	}
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getVip() {
		return vip;
	}
	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Integer getConservator() {
		return conservator;
	}
	public void setConservator(Integer conservator) {
		this.conservator = conservator;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", name=" + name + ", password=" + password + ", vip=" + vip + ", conservator="
				+ conservator + "]";
	}
	
}
