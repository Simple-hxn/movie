package com.mv.sys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.mv.common.vo.PageDownload;
import com.mv.sys.entity.SysFilm;
import com.mv.common.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 电影相关接口
 */
public interface SysFilmService {
	
	/**
	 * 查询所有的电影，根据页码值，页码值是必须的值，如果有分类的话，根据分类查询
	 * @param
	 * @param pageCurrent
	 * @return
	 */
	PageObject<SysFilm> findObjects(Integer categoryId, Integer pageCurrent);

	/**
	 * 根据名称查询电影信息，名称为必须值，本质为数据库模糊查询
	 * @param name
	 * @return
	 */
	List<SysFilm> findFileByName(String name);

	/**
	 * 电影收藏实现，输入用户的数据库主键id和电影名，输入收藏表sys_User_film
	 * @param userId
	 * @param filmName
	 * @return
	 */
	int saveObjectByFilmName(Integer userId, String filmName);

	/**
	 * 查询根据用户id查询收藏
	 * @param userId
	 * @return
	 */
	List<SysFilm> findObjectById(Integer userId);


	/**
	 * 删除电影
	 * @param userId
	 */

	int deleteObjects(Integer...userId);

	/**
	 * 增加电影
	 * @param entity
	 */
	int insertfilm(SysFilm entity);

	/**
	 * 更新电影
	 * @param entity
	 */
	int updateObject(SysFilm entity);

	/**
	 * 查询电影根据ID
	 * @param id
	 * @return
	 */
	SysFilm	findFilmById(Integer id);

	PageObject<PageDownload> sortFilm(Integer pageCurrent);

	PageObject<Map<String,Object>> findPageObjects1(String name, Integer pageCurrent);

//	String DownByname(String name);
	//下载视频
	SysFilm finddownFilmByName(String name);
	SysFilm finddownFilmByNamevip(String name);
	//获取vip权限认证
	int GetVIPcs();
	//在线播放视频
	List<SysFilm> findFileByNamelooking(String name);
//	String GetMultipartFileFilename(MultipartFile file, HttpServletResponse response, HttpServletRequest request);

	}
