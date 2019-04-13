package com.mv.common.util;

import com.mv.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;


/**
 * 调用该认证方法
 */
public class ShiroUtils {

	 public static SysUser getPrincipal(){

		 return (SysUser)SecurityUtils
		.getSubject().getPrincipal();
	 }
}
