package com.mv.sys.service;

import com.mv.sys.entity.SysUser;
import com.mv.common.vo.*;

/**
 * 用户相关操作接口
 */
public interface SysUserService {
	/**
	 * 依据条件查询当前页数据
	 * @param name
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysUser> findPageObjects(String name, Integer pageCurrent);
	/**
	 * 更新用户自身信息
	 * @param entity
	 *
	 * @return
	 */
	void updateObject(SysUser entity);

	/**
	 * 项数据库用户表中,插入指定用户对象的表数据
	 * @param sysUser
	 */
	void insertObject(SysUser sysUser);

	/**
	 * 根据用户id可批量删除用户
	 * @param ids
	 * @return
	 */
	int deleteObject(Integer... ids);
	/**
	 * 根据用户id查询用户详细信息
	 * @param id
	 * @return
	 */
	SysUser findObjectById(Integer id);
}
