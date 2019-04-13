package com.mv.sys.controller;

import com.mv.common.vo.JsonResult;
import com.mv.sys.service.SysFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 电影下载日志的控制器
 */
@Controller
@RequestMapping("/paixu/")
public class SysSortDownloadController {
	@Autowired
	private SysFilmService sysFilmService;
	//页面加载
	@RequestMapping("doSortFilm")
	public String doSortFilm(){
		return "sys/sortfim_list";
	}
	//获取下载信息
	@RequestMapping("doSortDownload")
	@ResponseBody
	public JsonResult doSortDownload(Integer pageCurrent){
		return new JsonResult(sysFilmService.sortFilm(pageCurrent));
	}
}
