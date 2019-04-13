package com.mv.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mv.common.util.ShiroUtils;
import com.mv.common.vo.JsonResult;
import com.mv.common.vo.PageObject;
import com.mv.sys.entity.SysUser;
import com.mv.sys.service.SysUserService;

import com.mv.common.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mv.common.util.ShiroUtils;


import com.mv.sys.entity.SysUser;
import com.mv.sys.service.SysUserService;

/**
 * 用户增删改查控制器
 */
@Controller
@RequestMapping("/user/")
public class SysUserController {
//	@Autowired
//	private SysUserService sysUserService;
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("doUserList")
	public String doUserList(){
		return "sys/user_list";
	}
	@RequestMapping("deUserEditUI")
	public String deEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody

	public JsonResult doFindObjectById(Integer id){
		//模拟耗时操作
		//try{Thread.sleep(5000);}
		//catch(Exception e){e.printStackTrace();}
		SysUser user =
				sysUserService.findObjectById(id);
		//return null;
		JsonResult result = new JsonResult(user);
		return result;
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent){
		PageObject<SysUser> data=
				sysUserService.findPageObjects(name,pageCurrent);
		System.out.println(data.getPageCount());
		return new JsonResult(data);
		//System.out.println("doFindPageObjects");
		//return null;
	}
	@RequestMapping("doInsertObject")
	@ResponseBody
	public JsonResult doInsertObject(SysUser sysUser){
		SysUser user = ShiroUtils.getPrincipal();
		sysUser.setCreateUser(user.getName());
		sysUserService.insertObject(sysUser);
		return new JsonResult("添加成功!");
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysUser sysUser){
		SysUser user = ShiroUtils.getPrincipal();
		sysUser.setUpdateUser(user.getName());
		sysUserService.updateObject(sysUser);
		return new JsonResult("修改成功!");
	}
	@RequestMapping("doDeleteUsersById")
	@ResponseBody
	public JsonResult doDeleteLogsById(Integer...id){
		sysUserService.deleteObject(id);
		return new JsonResult("删除成功");
	}
}
