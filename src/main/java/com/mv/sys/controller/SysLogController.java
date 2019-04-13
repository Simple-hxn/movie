package com.mv.sys.controller;

import com.mv.common.vo.JsonResult;
import com.mv.common.vo.PageObject;
import com.mv.sys.entity.SysLog;
import com.mv.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 日志查询删除控制器
 */
@Controller
@RequestMapping("/log/")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;
	@RequestMapping("doLogList")
	public String doLogList(){
		return "sys/log_list";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
		    String name,
			Integer pageCurrent){

		PageObject<SysLog> data=
				sysLogService.findPageObjects(name,
				pageCurrent);

		return new JsonResult(data);
	}
	@RequestMapping("doDeleteLogsById")
	@ResponseBody
	public JsonResult doDeleteLogsById(Integer... id){
		sysLogService.deleteLogsById(id);
		return new JsonResult("删除成功");
	}
}
