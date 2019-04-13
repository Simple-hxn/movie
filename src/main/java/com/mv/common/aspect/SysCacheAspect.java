package com.mv.common.aspect;

import java.lang.reflect.Method;

import com.mv.common.cache.ServiceMapCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mv.common.cache.CacheKey;
import com.mv.common.cache.ServiceMapCache;

@Service
@Aspect
public class SysCacheAspect {

	@Autowired
	private ServiceMapCache mapCache;
	/**
	 * 环绕通知，切面的实现方法
	 * @param jp
	 * @return
	 * @throws Throwable
	 * @throws JsonProcessingException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Around("@annotation(com.mv.common.annotation.RequiredCache)")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("执行环绕通知，存储数据");
		// 关键：如何去设计这个key值
		CacheKey key = obtainKey(jp);
		// 去缓存中去取值
		Object value = mapCache.get(key);
		if (value != null) {
			return value;
		}
		Object result = jp.proceed();
		// 向缓存中存入值
		mapCache.put(key, result);
		return result;
	}

	private CacheKey obtainKey(ProceedingJoinPoint jp) throws NoSuchMethodException, SecurityException, JsonProcessingException {
		// 1.构建Cache对象
		CacheKey key = new CacheKey();
		// 2.封装Cache对象
		Class<?> targetCls = jp.getTarget().getClass();
		// 获得他的签名对象
		MethodSignature ms = (MethodSignature) jp.getSignature();
		// 获得具体的方法对象
		Method targetMethod = targetCls.getDeclaredMethod(ms.getName(),
							  ms.getParameterTypes());
		// 参数的具体的值
		key.setTargetClass(targetCls);
		key.setTargetMethod(targetMethod);
		key.setActualArgs(new ObjectMapper().writeValueAsString(jp.getArgs()));
		// 返回key对象
		return key;
	}
}
