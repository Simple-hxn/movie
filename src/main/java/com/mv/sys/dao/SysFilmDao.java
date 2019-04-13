package com.mv.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mv.sys.entity.SysFilm;

public interface SysFilmDao {

	/**
	 * 根据分类和页码值来查询数据，返回值都是一个集合
	 * @param qc
	 * @return
	 */
	List<SysFilm> selectObjects(@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize,
								@Param("categoryId")Integer categoryId);

	/**
	 * 查询出所有的记录数
	 * @return
	 */
	Integer getAllCount(@Param("categoryId")Integer categoryId);

	/**
	 * @param name
	 * @param pageCurrent
	 * @param pageSize
	 * @return
	 */
	List<SysFilm> findFilmByName(@Param("name")String name);


	/**
	 * 根据电影名查询出电影信息
	 * @param filmNames
	 * @return
	 */
	List<SysFilm> findObjectsByFilmNames(@Param("filmNames")List<String> filmNames);

	/**
	 * 下面的方法都是后台管理的部分
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysFilm> findObjects(
			@Param("name")String name,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	List<Map<String,Object>> findPageObjects(
			@Param("name")String name,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);
	int getRowCount1(
			@Param("name")String name);
	int delteObjects(@Param("ids")Integer...ids);
	SysFilm findFilmById(Integer id);
	int insertFilm(SysFilm entity);
	int updateObject(SysFilm entity);

	SysFilm finddownFilmByName(@Param("name")String name);
}
