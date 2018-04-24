package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
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

	/**
	  * @Description(功能描述): 用户用户对象查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 10:35
	  **/
	public UserEntity getUser(UserEntity user){
		return userMapper.selectOne(user);
	}

	public UserEntity saveUser(UserEntity user) {
		if(CommonUtils.isEmpty(user)){
			//TODO 判断用户信息
		}else{
			throw new ScException(AppServiceEnums.USER_EXIST);		//用户信息已存在
		}
		return null;
	}


}
