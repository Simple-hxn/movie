package com.mv.sys.controller;

import com.mv.common.util.ShiroUtils;
import com.mv.common.vo.JsonResult;
import com.mv.common.vo.PageObject;
import com.mv.sys.entity.SysFilm;
import com.mv.sys.entity.SysUser;
import com.mv.sys.service.SysFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mv.common.vo.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 管理员管理电影相关控制器
 */
@Controller
@RequestMapping("/film/")
public class SysFilmController {

    @Autowired
    private SysFilmService sysFilmService;

	/**
	 * 页面插入跳转
	 * @return
	 */
	@RequestMapping("doFilmList")
	public String doFilmList(){
		return "sys/film_list";
	}
	@RequestMapping("doFilmEditeUI")
	public String doFilmEditeUI(){
		return "sys/film_edit";
	}

	/**
	 * 获取电影播放地址,根据电影名查询电影信息获取播放链接--vip电影
	 * @return
	 */
	@RequestMapping("doFindfilmlinkvip")
	@ResponseBody
	public JsonResult doFindfilmlinkvip(String name, HttpServletRequest req) {

		SysFilm findFileByName=sysFilmService.finddownFilmByNamevip(name);

		String link =findFileByName.getVoidelink();
		return new JsonResult(link);
	}
	/**
	 * 获取电影播放地址,根据电影名查询电影信息获取播放链接--免费电影
	 * @return
	 */
	@RequestMapping("doFindfilmlink")
	@ResponseBody
	public JsonResult doFindfilmlink(String name, HttpServletRequest req) {

		SysFilm findFileByName=sysFilmService.finddownFilmByName(name);

		String link =findFileByName.getVoidelink();
		return new JsonResult(link);
	}

    /**
     * 根据电影类型的分页查询
     *
     * @param categoryId
     * @param pageCurrent
     * @return
     */

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(Integer categoryId, Integer pageCurrent) {
        PageObject<SysFilm> sysFilms = sysFilmService.findObjects(categoryId, pageCurrent);
        return new JsonResult(sysFilms);
    }

	@RequestMapping("doFindPageObjects1")
	@ResponseBody
	public JsonResult doFindPageObjects1(String name,Integer pageCurrent){
		PageObject<Map<String, Object>> sys = sysFilmService.findPageObjects1(name,pageCurrent);
		return new JsonResult(sys);
	}
    /**
     * 搜索框实现控制器方法：根据名称搜索数据库得到电影信息
     * @param name
     * @return
     */
	@RequestMapping("doFindObjectByName")
	@ResponseBody
	public JsonResult doFindObjectByName(String name){
		return new JsonResult(sysFilmService.findFileByName(name));
	}

	/**
	 * 播放事件
	 * @param name
	 * @return
	 */
	@RequestMapping("doFindObjectByNamelooking")
	@ResponseBody
	public JsonResult doFindObjectByNamelooking(String name){
		return new JsonResult(sysFilmService.findFileByNamelooking(name));
	}

	/**
     * 收藏方法控制器实现，本质为传入用户名与电影名。录入数据库字段
     * @param userId
     * @param filmName
     * @return
     */
	@RequestMapping("doSaveObjectByName")
	@ResponseBody
	public JsonResult doSaveObjectByName(Integer userId, String filmName){

		sysFilmService.saveObjectByFilmName(userId, filmName);
		return new JsonResult("收藏成功");

	}

//	/**
//	 * 下载视频的控制
//	 * @param filmName
//	 * @return
//	 */
//
//	@RequestMapping("doDownByName")
//	@ResponseBody
//	public void doDownByName(String filmName, HttpServletRequest req, HttpServletResponse resp){
//		String sysFilms=sysFilmService.DownByname(filmName);
//		try {
//
//			File file =new File(sysFilms);
//
////			FileOutputStream FileputStream=new FileOutputStream(file);
////			BufferedOutputStream bufferedOutputStream =new BufferedOutputStream(FileputStream);
////
////			byte[] buffer = new byte[4 * 1024];
////			bufferedOutputStream.write(buffer);
//			FileInputStream in = new FileInputStream(file);
//			ServletOutputStream out = resp.getOutputStream();
//			byte[] b = null;
//			while(in.available() >0) {
//				if(in.available()>10240) {
//					b = new byte[10240];
//				}else {
//					b = new byte[in.available()];
//				}
//				in.read(b, 0, b.length);
//				out.write(b, 0, b.length);
//			}
//			in.close();
//			out.flush();
//
//		}catch (IOException e) {
//
//		}
//
//
//	}

	/**
	 * 根据电影name查询电影列表，查询收藏
	 * @param
	 * @return
	 */
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer userId){
		if (userId == null) {
			userId = 1;
		}
		return new JsonResult(
				sysFilmService.findObjectById(userId));
	}


	/**
	 * 删除电影
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteFilmsById")
	@ResponseBody
	public JsonResult doDeleteFilmsById(Integer...id){
		System.out.println(id);
		sysFilmService.deleteObjects(id);
		return new JsonResult("删除成功");
	}

	/**
	 * 增加电影
	 * @param entity
	 * @return
	 */
	@RequestMapping("doInsertFilm")
	@ResponseBody
	public JsonResult doInsertFilm(SysFilm entity){

		sysFilmService.insertfilm(entity);
		return new JsonResult("添加成功");
	}

	/**
	 * 更新电影
	 * @param entity
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysFilm entity){
		System.out.println(entity);
		sysFilmService.updateObject(entity);
		return new JsonResult("更新成功");
	}

	/**
	 * 根据id查询电影
	 * @param id
	 * @return
	 */
	@RequestMapping("doFindFilmById")
	@ResponseBody

	public JsonResult doFindFilmById(Integer id){

		SysFilm data = sysFilmService.findFilmById(id);
		System.out.println("结果："+data.getOnTime());
		return new JsonResult(data);
	}

	/**
	 * 获取vip权限操作
	 * @return
	 */

	@RequestMapping("doGetVIPByName")
	@ResponseBody
	public JsonResult GetVIPByName(){
		int i=sysFilmService.GetVIPcs();
		System.out.println(i);
		return new JsonResult(i);
	}

	/**
	 * 上传图片
	 * @param file
	 * @param response
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("inputphoto.do")
	@ResponseBody
	public JsonResult Upload(MultipartFile file,HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {

		//获取对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		/** 页面控件的文件流* */
		MultipartFile multipartFile = null;

		Map map = multipartRequest.getFileMap();
		//判断是否有文件上传，若有获取multipartFile对象，
		for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
			Object obj = i.next();
			multipartFile = (MultipartFile) map.get(obj);
		}
		/** 获取文件名* */
		String filename = multipartFile.getOriginalFilename();

		//2,截取源文件的文件名前缀,不带后缀
		String fileNamePrefix = filename.substring(0,filename.lastIndexOf("."));
		//3,加工处理文件名，原文件加上时间戳
		String newFileNamePrefix  = fileNamePrefix + System.currentTimeMillis();
		//4,得到新文件名
		String newFileName = newFileNamePrefix + filename.substring(filename.lastIndexOf("."));


		String realPath=request.getSession().getServletContext().getRealPath("/");

		String turepath =realPath+"images/"+newFileName;
		String rename="images/"+newFileName;
		//5,构建文件对象
		File file1 = new File(turepath);
		//上传文件
		multipartFile.transferTo(file1);

		return new JsonResult(rename);

	}

	/**
	 * 上传视频
	 * @param file
	 * @param response
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("inputvideo.do")
	@ResponseBody
	public JsonResult Upload1(MultipartFile file,HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException {

		//获取对象
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		/** 页面控件的文件流* */
		MultipartFile multipartFile = null;

		Map map = multipartRequest.getFileMap();
		//判断是否有文件上传，若有获取multipartFile对象，
		for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
			Object obj = i.next();
			multipartFile = (MultipartFile) map.get(obj);
		}
		/** 获取文件名* */
		String filename = multipartFile.getOriginalFilename();

		//2,截取源文件的文件名前缀,不带后缀
		String fileNamePrefix = filename.substring(0,filename.lastIndexOf("."));
		//3,加工处理文件名，原文件加上时间戳
		String newFileNamePrefix  = fileNamePrefix + System.currentTimeMillis();
		//4,得到新文件名
		String newFileName = newFileNamePrefix + filename.substring(filename.lastIndexOf("."));


		String realPath=request.getSession().getServletContext().getRealPath("/");


		String turepath =realPath+"images/voide/"+newFileName;
		String rename="images/voide/"+newFileName;
		//5,构建文件对象
		File file1 = new File(turepath);
		//上传文件
		multipartFile.transferTo(file1);

		return new JsonResult(rename);

	}
//}
}