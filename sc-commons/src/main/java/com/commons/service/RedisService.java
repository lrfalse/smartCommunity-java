package com.commons.service;



/**
 * @Description(功能描述) :redis缓存服务
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/24 19:30
 **/
public interface RedisService {

	/**
	 * @Description(功能描述): 设置值
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 19:50
	 **/
	void set(String key, Object object);

	/** 
	  * @Description(功能描述): 设置无过期时间
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/9 9:49
	  **/ 
	void setNoTime(String key,Object object);
	/**
	 * @Description(功能描述): 设置有过期时间的缓存值
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 20:11
	 * @param expire : 过期时间 单位分
	 **/
	void set(String key, Object object, long expire);
	/**
	 * @Description(功能描述): 根据key获取所存储的值
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 19:52
	 **/
	String get(String key);
	/**
	 * @Description(功能描述): 根据key删除所保存的值
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 19:53
	 **/
	void delete(String key);
	/**
	 * @Description(功能描述): 根据key判断是否存在值
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 19:54
	 **/
	boolean isExist(String key);


}
