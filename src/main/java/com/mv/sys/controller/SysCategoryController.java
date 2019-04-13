package com.mv.sys.controller;

import com.mv.common.vo.JsonResult;

import com.mv.common.vo.Node;
import com.mv.sys.service.SysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分页控制器
 */
@Controller
@RequestMapping("/category/")
public class SysCategoryController {
	@Autowired
	private SysCategoryService sysCategoryService;
	@RequestMapping("dofindCategory")
	@ResponseBody
	public JsonResult dofindCategory(){
		return new JsonResult(sysCategoryService.findCategory());
	}
	@RequestMapping(" findObjectById")
	@ResponseBody
	public JsonResult findObjectById(Integer id){
		System.out.println(id);
		Node data = sysCategoryService.findObjectById(id);
		return new JsonResult(data);
	}
}
