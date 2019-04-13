package com.mv.sys.controller;

import com.mv.common.util.ShiroUtils;
import com.mv.common.vo.JsonResult;
import com.mv.sys.dao.SysUserDao;
import com.mv.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 登陆相关控制器
 */
@Controller
@RequestMapping("/")
public class SysLoginController {

	@Autowired
	SysUserDao sysUserDao;

	/**
	 * 页面跳转4个
	 * @return
	 */
	@RequestMapping("doAdminUI")
	public String doAdminUI(){
		return "starter";
	}

	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}

	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "index";
	}

	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}

    /**
     * 登陆判断
     * @param username
     * @param password
     * @return
     */
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password){

        //生成toke对象，进行shiro默认对比对象
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		System.out.println(username);
		System.out.println(password);
		//生成sybject对象
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject);
        //调用loginn内置方法进行登陆判断与权限认证。
		subject.login(token);

		return new JsonResult("登录成功");
	}

	/**
	 * 是否管理员判断，双层判断
	 * @param username
	 * @return
	 */

	@RequestMapping("doselect")
	@ResponseBody
	public JsonResult doselect(String username){
	SysUser sysUser=sysUserDao.findUserByName(username);
	Integer conservator=sysUser.getConservator();
	return new JsonResult(Integer.toString(conservator));
	}

	/**
	 * 管理员权限认证
	 * @return
	 */

	@RequestMapping("permission")
	@ResponseBody
	public JsonResult doGetPermisson(){
		//获得认证权限的用户对象
		SysUser user = ShiroUtils.getPrincipal();
		int conservator = user.getConservator();
		String name=user.getName();
		List<String> list = new ArrayList<>();
		if(conservator==1){
			list.add("manager");
					}
		else{
			list.add("user");
		}
		return new JsonResult(list);
	}


	/**
	 * 获取会话的用户名
	 * @return
	 */

	@RequestMapping("Penameseesion")
	@ResponseBody
	public JsonResult doGetPenameseesion(){
		//获得认证权限的用户对象
		SysUser user = ShiroUtils.getPrincipal();
		String conservator = user.getName();
		String name=user.getName();
		List<String> list = new ArrayList<>();
		list.add(name);
		return new JsonResult(list);
	}
}
