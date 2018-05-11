package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description(功能描述) :用户信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/20 16:46
 **/
public interface UserMapper extends MyMapper<UserEntity> {


    UserEntity selectPhone(@Param("mobphone") String mobphone);//查询手机号码是否存在不

    int updateqqPhone(@Param("mobphone") String mobphone, @Param("qopenid") String qopenid);//根据qqopenid 绑定手机号码

    int updatewxPhone(@Param("mobphone") String mobphone, @Param("wopenid") String wopenid);//根据wxopnenid 绑定手机号码

    int updatePwd(@Param("mobphone") String mobphone, @Param("pwd") String pwd); //找回密码

	int addUserImgUrl(ParamDto paramDto);//手机号码已经注册过把头像放进头像表

	String queryUserImgId(ParamDto paramDto);

	/**
	  * @Description(功能描述): 查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/25 21:00
	  **/
	UserEntity selectUser(ParamDto dto);

	/**
	  * @Description(功能描述): 查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/26 17:15
	  **/
	List<UserEntity> selectListUser(ParamDto dto);

	/**
	  * @Description(功能描述): 更新用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/26 16:24
	  **/
	int updateUser(ParamDto dto);

	/**
	  * @Description(功能描述): 添加或查询用户
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/4 22:01
	  **/

	int adduser(UserEntity userEntity);

	/**
	  * @Description(功能描述): 查询用户是否存在
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/4 22:01
	  **/
	UserEntity queryUser(ParamDto paramDto);

	/**
	  * @Description(功能描述): 查询用户信息
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/7 10:09
	  **/
	UserEntity selectUserId(ParamDto paramDto);
}