
package com.mv.common.web;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mv.common.vo.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 注解描述的方法为一个异常处理方法
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandlerRunTimeException(RuntimeException e) {
		// 打印到控制台，给运维人员看
		e.printStackTrace();
		// 封装异常信息
		return new JsonResult(e); 
	}
}
