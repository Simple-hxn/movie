package com.mv.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserFilmDao {

	/**
	 * 根据userId查询出电影名称
	 * @param userId
	 * @return
	 */
	List<String> findObjectById(Integer userId);

	/**
	 * 插入userId和电影名
	 * @param userId
	 * @param filmName
	 * @return
	 */
	int insertObjectByName(@Param("userId")Integer userId,@Param("filmName")String filmName);
}
