package com.mv.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * 
 * 创建一个map集合，把它交给spring来管理
 * 设计这个缓存对象的时候我们要考虑的是他的key值，
 * 因为我们要保证它的key值是唯一存在的
 * @author tarena
 *
 */

@Component
public class ServiceMapCache {
	
	/**
	 * 为什么这个对象要被私有化?
	 * 因为他不能被我们自己创建
	 * 我们必须把它交给spring来创建
	 */
	private ServiceMapCache() {}
	
	// 设计一个缓存map，高并发的map
	Map<CacheKey, Object> cache = new ConcurrentHashMap<>();
	
	// 存储方法
	public void put(CacheKey key, Object value) {
		cache.put(key, value);
	}

	// 去数据的方法
	public Object get(CacheKey key) {
		return cache.get(key);
	}
	
	// 清除数据的方法
	public void clear() {
		cache.clear();
	}
}
