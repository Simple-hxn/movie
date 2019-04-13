package com.mv.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 */
@Controller
@RequestMapping("/")
public class PageController {
	
	@RequestMapping("/doCategoryPageUI")
	public String doCategoryPageUI() {
		return "film/page";
	}

	/**
	 * 返回主页
	 * @return
	 */
	@RequestMapping("/doIndexUI.do")
	public String doIndexUI() {
		return "index";
	}

	/**
	 * 电影详情
	 * @return
	 */
	@RequestMapping("/doDetailUI")
	public String doDetailUI() {
		return "film/detail";
	}

//	@RequestMapping("/doLoginUI.do")
//	public String doindex(){return "index";}

	/**
	 * 登陆界面
	 * @return
	 */
	@RequestMapping("login.do")
	public String dologin(){return "login";}

}
