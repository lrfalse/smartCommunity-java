package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

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


	/**
	  * @Description(功能描述): 查询用户信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/25 21:00
	  **/
	UserEntity selectUser(ParamDto dto);


}