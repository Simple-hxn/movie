package com.mv.common.web;

import com.mv.common.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常封装
 */
@ControllerAdvice
public class DealException {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult handlRunTimeException(RuntimeException e){
		e.printStackTrace();
		return new JsonResult(e.getMessage());
	}
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doShiroException(ShiroException exp){
		JsonResult r = new JsonResult();
		r.setState(0);
		if(exp instanceof UnknownAccountException){
			r.setMessage("用户名不存在");
		}else if(exp instanceof AuthenticationException){
			r.setMessage("用户不存在！或密码错误！");
		}
		else if(exp instanceof IncorrectCredentialsException){
			r.setMessage("密码错误");
		}else if(exp instanceof AuthorizationException){
			r.setMessage("没有权限!");
		}else{
			r.setMessage(exp.getMessage());
		}
		exp.printStackTrace();
		return r;
	}
}
