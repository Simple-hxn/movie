package com.mv.sys.service.impl;

import com.mv.common.exception.ServiceException;
import com.mv.common.vo.PageObject;
import com.mv.sys.dao.SysLogDao;
import com.mv.sys.entity.SysLog;
import com.mv.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	/** 依据用户名称,分页查询用户日志列表*/
	public PageObject<SysLog> findPageObjects(
			String name, Integer pageCurrent) {

		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码不正确");
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records =
				sysLogDao.findPageObjects(name, startIndex, pageSize);
		int rowCount = sysLogDao.getRowCount(name);
		if(records == null || records.size()==0)
			throw new ServiceException("系统没有查询到对应记录");
		PageObject<SysLog> pageObject = new PageObject<SysLog>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
	    pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		return pageObject;
	}

	/**
	 * 增加日志
	 * @param log
	 * @return
	 */
	public int insertObject(SysLog log) {
		if(log == null)
			throw new IllegalArgumentException("日志记录异常!请尽快联系开发人员进行修改");
		int rows = sysLogDao.insertObject(log);
		return rows;

	}
	public int deleteLogsById(String name, Integer... ids) {
		if(name == null || name == "")
			throw new IllegalArgumentException("当前用户名不正确!\n今天,你登录了吗?");
		if(ids == null)
			throw new IllegalArgumentException("请选择日志id再尝试删除操作");
		int rows = sysLogDao.deleteLogsById(ids);
		return rows;
	}

	/**
	 * 删除日志
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("ht:sc")
	@Override
	public int deleteLogsById(Integer... ids) {
		if(ids==null||ids.length<1)
			throw new ServiceException("请选中要删除的数据");
		int rows = sysLogDao.deleteLogsById(ids);
		if(rows == 0){
			throw new ServiceException("数据失败或数据不存在，请刷新后重试");
		}
		return rows;
	}

}
