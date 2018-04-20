package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.entity.UserEntity;
import com.commons.service.UserService;
import com.dubbo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @Description(功能描述): 用户
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/19 10:59
  **/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	/**
	  * @Description(功能描述): 新增用户
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/20 16:49
	  **/
	public int addUser(UserEntity user){
		return userMapper.insert(user);
	}
}
