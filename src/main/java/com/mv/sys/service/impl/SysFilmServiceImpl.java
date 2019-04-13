package com.mv.sys.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mv.common.annotation.requiredClearCache;
import com.mv.common.util.ShiroUtils;
import com.mv.common.vo.JsonResult;
import com.mv.common.vo.PageDownload;

import com.mv.common.vo.PageObject;
import com.mv.sys.dao.SysLogDao;
import com.mv.sys.dao.SysUserFilmDao;
import com.mv.sys.entity.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mv.common.annotation.RequiredCache;
import com.mv.common.annotation.RequiredLog;
import com.mv.common.exception.ServiceException;
import com.mv.sys.dao.SysFilmDao;

import com.mv.sys.entity.SysFilm;
import com.mv.sys.service.SysFilmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SysFilmServiceImpl implements SysFilmService {

	@Autowired
	private SysFilmDao sysFilmDao;

	@Autowired
	private SysUserFilmDao sysUserFilmDao;

	@Autowired
	private SysLogDao sysLogDao;
//	@Autowired
//	private SysUserDao sysUserDao;
//
//	@Autowired
//	private SysLogDao sysLogDao;
//
	//初步加载时
	@RequiredCache
	@Override
	public PageObject<SysFilm> findObjects(Integer categoryId, Integer pageCurrent) {
		System.out.println("查询所有的电影");
		if(pageCurrent == null || pageCurrent < 1){
			throw new ServiceException("页码值不正确");
		}
		// 定义每页显示值
		Integer pageSize = 8;
		// 查询出总记录数

		Integer rowCount = sysFilmDao.getAllCount(categoryId);

		if (rowCount == 0) {
			throw new ServiceException("没有相应的影片");
		}
		// 计算出总页数
		Integer pageCount = (rowCount-1) / pageSize + 1;
		// 计算开始行
		Integer startIndex = pageSize*(pageCurrent-1);
		List<SysFilm> sysFilms = sysFilmDao.selectObjects(startIndex, pageSize, categoryId);
		// 封装参数到pageObject
		PageObject<SysFilm> page = new PageObject<>();
		page.setPageCount(pageCount);
		page.setPageCurrent(pageCurrent);
		page.setPageSize(pageSize);
		page.setRecords(sysFilms);
		page.setRowCount(rowCount);
		// 返回参数
		return page;
	}
	/**
	 * 基于电影名查询
	 */
	@RequiredLog(operation="查询电影名")

	/**
	 * 搜索框的实现
	 */
	@RequiredCache
	public List<SysFilm> findFileByName(String name) {
		System.out.println("根据电影查询数据库");
		if (StringUtils.isEmpty(name)) {
			throw new ServiceException("请输入电影名");
		}
		List<SysFilm> SysFilms = sysFilmDao.findFilmByName(name);
		if (SysFilms == null || SysFilms.size()==0) {
			throw new ServiceException("没有相关电影");
		}
		return SysFilms;
	}

	/**
	 * 在线播放逻辑逻辑
	 * @param name
	 * @return
	 */
	@RequiredLog(operation="在线播放")
	@Override
	public List<SysFilm> findFileByNamelooking(String name) {
		System.out.println("根据电影查询数据库");
		if (StringUtils.isEmpty(name)) {
			throw new ServiceException("请输入电影名");
		}
		List<SysFilm> SysFilms = sysFilmDao.findFilmByName(name);
		if (SysFilms == null || SysFilms.size()==0) {
			throw new ServiceException("没有相关电影");
		}
		return SysFilms;
	}
	/**
	 * 根据传入用户的ID和选择的电影名，进行收藏操作。
	 * @param userId
	 * @param filmName
	 * @return
	 */
	@RequiredLog(operation="收藏")
	@Override
	public int saveObjectByFilmName(Integer userId, String filmName) {

		SysUser user = ShiroUtils.getPrincipal();

		try {
			userId = user.getId();
			if (filmName == null) {
				throw new ServiceException("电影名不存在");
			}
			int count = sysUserFilmDao.insertObjectByName(userId, filmName);
			return count;

		}catch (Exception e){

//			throw new ServiceException("请先登陆！");
			throw new IllegalArgumentException("请先登陆！");
		}


	}

	/**
	 * 查询用户收藏记录，根据用户id查询数据库表sys_User_film
	 * @param userId
	 * @return
	 */
	@RequiredLog(operation="查询收藏")
	@Override
	public List<SysFilm> findObjectById(Integer userId) {
//		System.out.println("根据用户id查询数据库");
		SysUser user = ShiroUtils.getPrincipal();
		try {
			userId = user.getId();
			List<String> filmNames = sysUserFilmDao.findObjectById(userId);
			if(filmNames == null)
				throw new ServiceException("没有收藏记录");
			//3.获取电影id
			List<SysFilm> sysFilms = sysFilmDao.findObjectsByFilmNames(filmNames);
			return sysFilms;
		}catch (Exception e){
			throw new IllegalArgumentException("请先登陆！");

		}



	}






	/**
	 * 删除电影，必要的用户权限是管理员
	 * @param ids
	 * @return
	 */
	@RequiredLog(operation="更新电影")
	@requiredClearCache
	@RequiresPermissions("ht:sc")
	@Override
	public int deleteObjects(Integer...ids) {
		if(ids==null|ids.length<1)
			throw new ServiceException("请选中要删除的数据");
		int rows = sysFilmDao.delteObjects(ids);
		if(rows <1)
			throw new ServiceException("删除失败");
		return rows;
	}

	/**
	 * 增加电影
	 * @param sysFilm
	 * @return
	 */
	@RequiredLog(operation="新增电影")
	@requiredClearCache
	@Override
	public int insertfilm(SysFilm sysFilm) {
		if(sysFilm==null)
			throw new ServiceException("请输入有效数据");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getName()))
			throw new ServiceException("请输入电影名");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getArea()))
			throw new ServiceException("请输入电影来源地区");
		if(sysFilm.getCategoryId()==null)
			throw new ServiceException("请选择电影类型");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getRoute()))
			throw new ServiceException("请上传图片");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getVoidelink()))
			throw new ServiceException("请上传视频");
		int rows = sysFilmDao.insertFilm(sysFilm);
		if(rows == 0)
			throw new ServiceException("添加失败");
		return rows;
	}

	/**
	 * 修改电影信息，权限为xg
	 * @param sysFilm
	 * @return
	 */

	@RequiredLog(operation="修改电影")
	@requiredClearCache
	@RequiresPermissions("ht:xg")
	@Override
	public int updateObject(SysFilm sysFilm) {

		if(sysFilm==null)
			throw new ServiceException("请输入有效数据");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getName()))
			throw new ServiceException("请输入电影名");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getArea()))
			throw new ServiceException("请输入电影来源地区");
		if(sysFilm.getCategoryId()==null)
			throw new ServiceException("请选择电影类型");
		if(com.alibaba.druid.util.StringUtils.isEmpty(sysFilm.getRoute()))
			throw new ServiceException("请输入存放路径地址");
		int rows = sysFilmDao.updateObject(sysFilm);
		if(rows == 0)
			throw new ServiceException("修改失败");
		return 0;
	}


	/**
	 * 查询电影。根据用户名
	 * @param id
	 * @return
	 */
	@Override
	public SysFilm findFilmById(Integer id) {
		return sysFilmDao.findFilmById(id);
	}

	/**
	 * 分页的逻辑实现
	 * @param name
	 * @param pageCurrent
	 * @return
	 */

	@Override
	public PageObject<Map<String,Object>> findPageObjects1(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysFilmDao.getRowCount1(name);
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		List<Map<String,Object>> records =
				sysFilmDao.findPageObjects(name,startIndex, pageSize);
		System.out.println(records);
		if(records==null || records.size()==0)
			throw new ServiceException("系统没有查询到对应记录");
		PageObject<Map<String,Object>> pageObject =
				new PageObject<Map<String,Object>>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		return pageObject;
	}

	/**
	 * 用户下载的操作记录，仅查询记录为下载的操作
	 * @param pageCurrent
	 * @return
	 */

	public PageObject<PageDownload> sortFilm(Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysFilmDao.getRowCount1("");
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysFilm> film = sysFilmDao.findObjects("", startIndex, pageSize);
		List<PageDownload> list = new ArrayList();
		PageObject<PageDownload> pageObject = new PageObject<PageDownload>();
		for(SysFilm sf:film){
			PageDownload pd = new PageDownload();
			String filmName = sf.getName();
			pd.setFilmName(filmName);
			int row = sysLogDao.getFimRow(filmName,"下载");
			pd.setDownloadTimes(row);
			pd.setOperation("下载");
			list.add(pd);
		}
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(list);
		return pageObject;
	}

	/**
	 * vip权限认证，
	 */
	@RequiresPermissions("ht:vip")

	public int GetVIPcs() {

		return 1;
	}
	@RequiredLog(operation="下载")
	@requiredClearCache
	@RequiresPermissions("ht:vip")
	@Override
	public SysFilm finddownFilmByNamevip(String name){
		return sysFilmDao.finddownFilmByName(name);
	};

	@requiredClearCache
	@RequiredLog(operation="下载")
	@Override
	public SysFilm finddownFilmByName(String name){
		return sysFilmDao.finddownFilmByName(name);
	}


}
