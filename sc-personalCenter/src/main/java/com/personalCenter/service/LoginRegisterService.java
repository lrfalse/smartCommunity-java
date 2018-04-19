package com.personalCenter.service;

import com.alibaba.fastjson.JSON;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.LoginDTO;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.CommonUtils;
import com.personalCenter.mapper.LoginRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:注册登录service
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-18 15:15:36
 */
@Service
public class LoginRegisterService{

    private static final Logger logger= LoggerFactory.getLogger(LoginRegisterService.class);


    @Autowired
    private LoginRegisterMapper loginRegisterMapper;

    /**
     * @Description(功能描述): 微信登录
     * @author(作者): feihong
     * @date (开发日期):2018-04-18 15:15:36
     **/
    public Object login(IsJsonDTO isJsonDTO) {
        UserEntity userEntity  = JSON.parseObject(isJsonDTO.getBodyJson(),UserEntity.class);
        if(CommonUtils.isEmpty(userEntity)){
            logger.info("userEntity:请求数据为空");
            return AppServiceEnums.SYS_DATA_ERROR.getCode();
        }
        UserEntity userEntity1 = loginRegisterMapper.selectOne(userEntity);
        if (CommonUtils.isNotEmpty(userEntity1)){
            return bulidLoginDto(userEntity1);
        }
        int i = loginRegisterMapper.insert(userEntity);
        if (i>0) {
            return bulidLoginDto(userEntity);
        }else {
            return AppServiceEnums.SYS_EXCEPTION.getCode();
        }

    }

    /**
      * @Description(功能描述): 对象构建
      * @author(作者): feihong
      * @date (开发日期):2018-4-19 9:48:32
      **/
    public LoginDTO bulidLoginDto(UserEntity userEntity){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setImage_url(userEntity.getImageUrl());
        loginDTO.setName(userEntity.getName());
        loginDTO.setSex(userEntity.getSex());
        return loginDTO;
    }
}
