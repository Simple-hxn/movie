package com.mv.common.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.mv.common.annotation.RequiredLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;


import com.mv.common.annotation.*;
import com.mv.common.exception.ServiceException;
import com.mv.sys.dao.SysLogDao;
import com.mv.sys.entity.SysFilm;
import com.mv.sys.entity.SysLog;
import org.springframework.stereotype.Service;

/**
 *
 * order意思就是被多个切面所切的话，有一个优先权执行通知，数值越低，优先级越高
 * 切面的表达式：
 * 粗粒度：
 * bean(*ServiceImpl) // 根据Spring中bean来定义，只能精确到一个类
 * with(com.jt.sys.service.impl**) // 根据包来定义到类，只能精确到有个类
 * @annotation 精粒度的切点，可以精确到具体的一个类，但是要自己定义注解
 * execution 晶粒度的切点 execution(*com.jt.sys.service..*.*(..))
 * execution(返回值类型 包名.类名.方法名(参数列表))
 * 借助@Aspect注解描述此类为一个切面对象 我们要在此类中实现日志记录操作 用户添加，修改等业务操作执行我们要进行日志记录
 * @author tarena
 *
 *
 */

@Aspect
@Service
public class SysLogAspect {

	@Autowired
	private SysLogDao sysLogDao;

	/**
	 * @Aroud 修饰的方法为一个环绕通知 可以在目标方法执行之前和之后添加业务
	 * @param jp
	 * @return
	 * @throws Throwable
	 */

	@Around("@annotation(com.mv.common.annotation.RequiredLog)")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("返回："+jp.getArgs());
		// 2.执行目标方法(可以基于业务不执行业务)
		Object result = jp.proceed(); // 通过反射机制
		// 先获取目标类，获取的是实现类的类对象
		Class<?> targetCls = jp.getTarget().getClass();
		// 获取目标方法对象
		Method targetMethod = getTargetMethod(targetCls, jp);
		// 获取目标方法上注解
		RequiredLog requiredLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
		// 获取注解中定义的操作名
		String operation = requiredLog != null ? requiredLog.operation() : "unkown";
		System.out.println(operation);
		System.out.println(result.getClass());
		// 封装用户行为信息
		if(result.getClass() == ArrayList.class) {
			@SuppressWarnings("unchecked")
			List<SysFilm> sysFilms = (ArrayList<SysFilm>)result;
			for (SysFilm sysFilm : sysFilms) {
	        	SysLog log = new SysLog();
	    		log.setOperation(operation);
	    		log.setFilmName(sysFilm.getName());
	    		int row = sysLogDao.insertObject(log);
	    		if (row == 0) {
	    			throw new ServiceException("日志保存失败");
	    		}
			}
		} else{
			// 用jp对象获取参数值
			Object[] args = jp.getArgs();
			SysLog log = new SysLog();
			String name;
			SysFilm sysFilm;
			Object object;
			//判断参数个数
			if (args.length==1){
				//判断类型
				object=	(Object) args[0];
				String classtype=object.getClass().getTypeName();

				if (classtype=="java.lang.String"){//下载
					// 从参数里取出第一个参数
					name= (String)args[0];
					// 封装log信息
					log.setOperation(operation);
					log.setFilmName(name);
					// 把数据放入数据库
					int row= sysLogDao.insertObject(log);
					if (row == 0) {
						throw new ServiceException("日志保存失败");
					}
				}else {
					// 从参数里取出第一个参数
					sysFilm = (SysFilm) args[0];
					// 封装log信息
					log.setOperation(operation);
					log.setFilmName(sysFilm.getName());
					// 把数据放入数据库
					int row = sysLogDao.insertObject(log);
					if (row == 0) {

					}


				}


			}else {
				//从参数取出第二个参数，收藏时第二个参数时String
				name= (String)args[1];
				// 转换编码格式
				String filmName = new String(name.getBytes("iso-8859-1"), "UTF-8");
				// 封装log信息
				log.setOperation(operation);
				log.setFilmName(filmName);
				// 把数据放入数据库
				int row = sysLogDao.insertObject(log);
				if (row == 0) {
					throw new ServiceException("日志保存失败");
				}
			}


		}
		return result;
	}

	// 获取目标方法对象
	private Method getTargetMethod(Class<?> targetCls, ProceedingJoinPoint jp) throws Exception, SecurityException {
		// 获取方法签名（方法名+参数）方法的唯一标识
		MethodSignature signature = (MethodSignature) jp.getSignature();
		// 获取方法名
		String methodName = signature.getName();
		// 获取方法签名的参数类型
		Class<?>[] parametertypes = signature.getParameterTypes();
		// 根据参数类型，方法名可以直接获取目标方法对象
		Method targetMethod = targetCls.getDeclaredMethod(methodName, parametertypes);
		// 返回目标方法对象
		return targetMethod;
	}


}