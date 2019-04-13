package com.mv.common.aspect;

import com.mv.common.cache.ServiceMapCache;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.mv.common.cache.ServiceMapCache;

@Order(1)
@Service
@Aspect
public class SysCacheClear {

	@Autowired
	private ServiceMapCache cache;

	/**
	 * 后置通知，在return之后执行
	 * @return
	 */
	@After("@annotation(com.mv.common.annotation.requiredClearCache)")
	public void AfterRunningMethod() {
		System.out.println("执行后置通知，清除缓存");
		cache.clear();
	}
}
