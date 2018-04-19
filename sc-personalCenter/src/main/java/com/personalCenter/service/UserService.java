package com.personalCenter.service;

import com.commons.entity.UserEntity;
import com.personalCenter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
  * @Description(功能描述): 用户
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/19 10:59
  **/
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper; 	//用户


	/**
	  * @Description(功能描述): 新增单个省份
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/17 15:02
	 * @param user : 用户注册对象-可分解
	  **/
	public int addUser(UserEntity user){
		return userMapper.insert(user);
	}
}
