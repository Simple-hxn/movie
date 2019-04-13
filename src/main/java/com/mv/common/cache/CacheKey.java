package com.mv.common.cache;

import java.lang.reflect.Method;

/**
 * 我们怎么去保证这个对象的唯一性，还要跟业务相关
 * 我们可以把这个对象当做map的key值
 * 我们可以从三个维度去定义
 * 1.类名
 * 2.方法对象=方法名+方法参数
 * 3.参数的具体的值
 * key的值是一个对象，我们要判断两个对象是否相等，我们必须要重写对象的equals和hash方法
 * @author tarena
 *
 */
public class CacheKey {
	// 目标的类对象
	private Class<?> targetClass;
	// 目标方法对象
	private Method targetMethod;
	// 参数类型
	private String actualArgs;
	
	// 提供set和get方法
	public Class<?> getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}
	public Method getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
	}
	public String getActualArgs() {
		return actualArgs;
	}
	public void setActualArgs(String actualArgs) {
		this.actualArgs = actualArgs;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualArgs == null) ? 0 : actualArgs.hashCode());
		result = prime * result + ((targetClass == null) ? 0 : targetClass.hashCode());
		result = prime * result + ((targetMethod == null) ? 0 : targetMethod.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheKey other = (CacheKey) obj;
		if (actualArgs == null) {
			if (other.actualArgs != null)
				return false;
		} else if (!actualArgs.equals(other.actualArgs))
			return false;
		if (targetClass == null) {
			if (other.targetClass != null)
				return false;
		} else if (!targetClass.equals(other.targetClass))
			return false;
		if (targetMethod == null) {
			if (other.targetMethod != null)
				return false;
		} else if (!targetMethod.equals(other.targetMethod))
			return false;
		return true;
	}
}
