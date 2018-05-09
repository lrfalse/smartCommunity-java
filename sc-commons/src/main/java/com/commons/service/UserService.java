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
	LoginDTO logIn(UserEntity userEntity);
	/**
	  * @Description(功能描述): 根据token获取用户信息 token生产规则 token=md5(phone)
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/9 10:29
	  **/
	LoginDTO getRedisUser(String md5Token);

	/**
	 * @Description(功能描述): 绑定手机号码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 14:45:33
	 **/
	LoginDTO bindPhone(UserEntity userEntity);

	/**
	 * @Description(功能描述): 找回密码
	 * @author(作者): feihong
	 * @date (开发日期):2018-4-24 19:55:34
	 **/
	int updateUserByKey(UserEntity userEntity);


}
