package com.personalCenter.mapper;

import com.commons.config.MyMapper;
import com.commons.entity.UserEntity;


/**
 * @Description:登录注册mapper
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-18 16:16:29
 */
public interface LoginRegisterMapper extends MyMapper<UserEntity>{

    UserEntity userQuery(String openid);
}
