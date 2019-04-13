package com.mv.sys.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.mv.common.exception.ServiceException;
import com.mv.sys.dao.SysUserDao;
import com.mv.sys.entity.SysUser;
import com.mv.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.mv.common.vo.*;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * 更新用户信息
	 * @param entity
	 *
	 */
	public void updateObject(SysUser entity) {
		if(entity==null||entity.getId() < 1)
			throw new IllegalArgumentException("传入的用户数据有误");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("用户密码不能为空");
		if(entity.getConservator()==null)
			throw new ServiceException("用户权限等级不能为空");
		if(entity.getVip()==null)
			throw new ServiceException("用户vip等级不能为空");
		String salt = UUID.randomUUID().toString();
		String pwd = entity.getPassword();
		SimpleHash sHash=//Shiro中的一个类
	    new SimpleHash("MD5",pwd, salt);
		String newPwd=sHash.toString();
		entity.setSalt(salt);
		entity.setPassword(newPwd);
		//2.2存储用户信息
		try{
			System.out.println(entity);
			System.out.println("开始修改数据");
			sysUserDao.updateObjectById(entity);
			System.out.println("修改数据完成");
		}catch(Exception e){
			e.printStackTrace();
			//继续报警,抛出异常
		    throw new ServiceException("系统维护中");
		}
	}

	/**
	 * 增加用户信息
	 * @param entity
	 */
	public void insertObject(SysUser entity) {
		if(entity==null)
			throw new IllegalArgumentException("传入的用户数据有误");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("用户密码不能为空");
		if(entity.getConservator()==null)
			throw new ServiceException("用户权限等级不能为空");
		if(entity.getVip()==null)
			throw new ServiceException("用户vip等级不能为空");
		String salt = UUID.randomUUID().toString();
		String pwd = entity.getPassword();
		SimpleHash sHash=//Shiro中的一个类
	    new SimpleHash("MD5",pwd, salt);
		String newPwd=sHash.toString();
		entity.setSalt(salt);
		System.out.println(newPwd);
		entity.setPassword(newPwd);
		//2.2存储用户信息
		int rows;
		try{
			rows=sysUserDao.insertObject(entity);
		}catch(Exception e){
			e.printStackTrace();
			//继续报警,抛出异常
			if(e instanceof DuplicateKeyException){
			throw new ServiceException("此用户已存在");
			}
		    throw new ServiceException("系统维护中");
		}
	}

	/**
	 * 分页的实现逻辑，根据页码和name,name初始值为空。本质是传入页码，进行判断，进行逻辑分页
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	@Override
	public PageObject<SysUser> findPageObjects(String name, Integer pageCurrent) {
		System.out.println(name);
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
			int rowCount = sysUserDao.getRowCount(name);
			System.out.println("总数："+rowCount);
			//第一页的初始值
			int pageSize=5;
			int startIndex=(pageCurrent-1)*pageSize;
			List<SysUser> records = sysUserDao.findPageObjects(name,startIndex, pageSize);
			System.out.println("查询出来的记录："+records);

			if(records==null || records.size()==0)
				throw new ServiceException("系统没有查询到对应记录");

			PageObject<SysUser> pageObject = new PageObject<SysUser>();

			pageObject.setPageCurrent(pageCurrent);
			pageObject.setPageSize(pageSize);
		    pageObject.setRowCount(rowCount);
			pageObject.setRecords(records);
			return pageObject;
	}

	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	@Override
	public int deleteObject(Integer... ids) {
		if(ids==null||ids.length==0)
			throw new ServiceException("请选中要删除的数据");
		int rows = sysUserDao.deleteObject(ids);
		if(rows==0)
			throw new ServiceException("数据已不存在");
		return rows;
	}

	/**
	 * 根据ID寻找用户
	 * @param id
	 * @return
	 */
	@Override
	public SysUser findObjectById(Integer id) {
		if(id<1)
			throw new IllegalArgumentException("选择用户错误");
		SysUser user = sysUserDao.findObjectById(id);
		if(user==null)
			throw new ServiceException("该用户的id不存在,无法查找到用户数据!");
		return user;
	}
}
