package com.personalCenter.mapper;

import com.commons.config.MyMapper;
import com.commons.entity.UserEntity;
import org.apache.ibatis.annotations.Param;


/**
 * @Description:登录注册mapper
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-18 16:16:29
 */
public interface LoginRegisterMapper extends MyMapper<UserEntity>{

    UserEntity phoneQuery(String mobphone);//根据手机号查询

    int  updateQopenid(@Param("qopenId") String qopenId, @Param("mobPhone") String mobPhone);//根据手机号码绑定qq

    int  updateWopenid(@Param("wopenid") String wopenid,@Param("mobphone") String mobphone);//根据手机号绑定微信

    int  updatewphone(@Param("wopenId") String wopenId,@Param("mobPhone") String mobPhone);//根据微信绑定手机

    int  updateqphone(@Param("qopenId") String qopenId,@Param("mobPhone") String mobPhone);//根据QQ绑定手机
}
