package com.decorpot.utils;

import java.util.HashMap;
import java.util.Map;

public class DataCache {
	
	private static Map<String, Object> cache;
	private static DataCache dataCache = null;
	
	private DataCache(){
		cache = new HashMap<>();
	}

	public static DataCache getInstance(){
		if( dataCache == null){
			synchronized (DataCache.class) {
				if(dataCache == null){
					dataCache = new DataCache();
				}
			}
		}
		return dataCache;
	}
	
	public static void put(String key, Object value ){
		System.out.println("came in cache key -- " + key);
		synchronized (cache) {
			System.out.println("key came -> " + key);
			System.out.println("value came -> + " + value.toString());
			
			cache.put(key, value);
			System.out.println("cache value -> " + cache);
		}
	}
	
	public static Object get(String key){
		return cache.get(key);
		//return null;
	}
}
