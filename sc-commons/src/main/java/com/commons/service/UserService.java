package com.commons.service;

import com.commons.dto.anDto.BindPhoneDto;
import com.commons.dto.anDto.LoginDTO;
import com.commons.entity.UserEntity;
import com.commons.exception.ScException;

/**
  * @Description(功能描述): 用户
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/19 10:59
  **/
public interface UserService {

	/**
	  * @Description(功能描述): 新增用户
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/20 16:39
	  **/
	int addUser(UserEntity user);

	/**
	 * @Description(功能描述): 用户用户对象查询用户信息
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/24 10:35
	 **/
	UserEntity getUser(UserEntity user);
	/**
	  * @Description(功能描述): 保存用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 21:04
	  **/
	int saveUser(UserEntity user) ;

	/**
	 * @Description(功能描述): 登录
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 10:33:21
	 **/
	LoginDTO login(UserEntity userEntity);

	/**
	 * @Description(功能描述): 绑定手机号码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 14:45:33
	 **/
	BindPhoneDto bindPhone(UserEntity userEntity);

	/**
	 * @Description(功能描述): 找回密码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 19:55:34
	 **/
	int updateUserByKey(UserEntity userEntity);
}
