package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.commons.service.RedisService;
import com.commons.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description(功能描述) :redis 服务接口
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/24 19:31
 **/
@Service
public class RedisServiceImpl implements RedisService{
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	  * @Description(功能描述): 设置值，默认10分钟
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 19:50
	  **/
	public void set(String key,Object object){
		try {
			stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(object),10L,TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setNoTime(String key,Object object){
		try {
			stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(object));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  * @Description(功能描述): 设置有过期时间的缓存值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 20:11
	 * @param expire : 过期时间 单位分
	  **/
	public void set(String key,Object object,long expire){
		try {
			stringRedisTemplate.opsForValue().set(key, JsonUtils.toJson(object),expire, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  * @Description(功能描述): 存储list值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 19:52
	  **/
	public void setList(String key, List list){
		try {
			stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	  * @Description(功能描述): 根据key获取所存储的值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 19:52
	  **/
	public String get(String key){
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	  * @Description(功能描述): 根据key删除所保存的值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 19:53
	  **/
	public void delete(String key) {
		stringRedisTemplate.opsForValue().getOperations().delete(key);
	}

	/**
	  * @Description(功能描述): 根据key判断是否存在值
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 19:54
	  **/
	public boolean isExist(String key) {
		return stringRedisTemplate.opsForValue().getOperations().hasKey(key);
	}
}
