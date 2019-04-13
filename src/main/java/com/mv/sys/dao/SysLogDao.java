package com.mv.sys.dao;

import com.mv.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogDao {
	/**
	 * 保存日志信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysLog entity);
	/**
	 * 按照查询条件统计用户操作日志数
	 * @param username (用户名)
	 * @return 查询到的记录数,按照这个值以及pageSize
	 * 计算总页数
	 */
	int getRowCount(@Param("filmName") String filmname);
	/**
	 * 依据用户名称查询当前页用户日志数据
	 * @param username 查询条件(按用户名查询)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前模块的页面大小(每页最多显示多少条记录)
	 * @return
	 */
	List<SysLog> findPageObjects(
			@Param("filmName")String filmName,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int deleteLogsById(@Param("ids")Integer... ids);
	int getFimRow(@Param("filmName")String filmName,
				  @Param("operation")String operation);
}
