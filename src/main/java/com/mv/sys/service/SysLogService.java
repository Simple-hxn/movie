package com.mv.sys.service;


import com.mv.sys.entity.SysLog;
import com.mv.common.vo.*;

/**
 * 日记操作接口
 */
public interface SysLogService {
	/** 依据用户名称查询当前页用户日志数据*/
	PageObject<SysLog> findPageObjects(
            String name, Integer pageCurrent);

	/**
	 * 增加日志
	 * @param log
	 * @return
	 */
	int insertObject(SysLog log);

	/**
	 * 删除日记
	 * @param ids
	 * @return
	 */
	int deleteLogsById(Integer... ids);
}
