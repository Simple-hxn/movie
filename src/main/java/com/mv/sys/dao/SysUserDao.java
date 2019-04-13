package com.mv.sys.dao;

import com.mv.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
	/**
	 * 无条件查询所有用户
	 * @return
	 */
	List<SysUser> findUserObjects();
	/**
	 * 按id查询用户
	 * @return
	 */
	SysUser findObjectById(Integer id);

	/**
	 * 项数据库用户表中,插入指定用户对象的表数据
	 * @param sysUser
	 */
	int insertObject(SysUser sysUser);

	/**
	 * 根据用户id,修改指定用户数据
	 * @param sysUser
	 */
	int updateObjectById(SysUser sysUser);
	/**
     * 依据条件查询当前页数据
     * @param startIndex 当前页的起始位置
     * @param pageSize 当前模块的页面大小(每页最多显示多少条记录)
     * @return
     */
	int getRowCount(@Param("userName") String userName);

	/**
	 * 分页查询
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUser> findPageObjects(
            @Param("userName") String name,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
	 int deleteObject(
             @Param("ids") Integer... ids);

	 SysUser findUserByName(String name);
}
