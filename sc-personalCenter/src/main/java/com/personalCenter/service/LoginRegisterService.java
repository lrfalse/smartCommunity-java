package com.personalCenter.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.LoginDTO;
import com.commons.dto.LoginErrorDto;
import com.commons.entity.CommunityEntity;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.CommonUtils;
import com.commons.utils.MD5Utils;
import com.personalCenter.mapper.LoginRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:注册登录service
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-18 15:15:36
 */
@Service
public class LoginRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(LoginRegisterService.class);


    @Autowired
    private LoginRegisterMapper loginRegisterMapper;

    /**
     * @Description(功能描述): 登录
     * @author(作者): feihong
     * @date (开发日期):2018-04-18 15:15:36
     **/
    public Object login(IsJsonDTO isJsonDTO) {
        UserEntity userEntity = JSON.parseObject(isJsonDTO.getBodyJson(), UserEntity.class);
        if (CommonUtils.isEmpty(userEntity)) {
            logger.info("userEntity:请求数据为空");
            return AppServiceEnums.SYS_DATA_ERROR.getCode();
        }
        //QQ登录
        if (userEntity.getToken().equals("Q")) {
            UserEntity userEntity1 = loginRegisterMapper.selectOne(userEntity);
            if (CommonUtils.isEmpty(userEntity1)) {
                int i = loginRegisterMapper.insert(userEntity);
                if (i > 0) {
                    return bulidLoginDto(userEntity);
                } else {
                    return AppServiceEnums.SYS_EXCEPTION.getCode();
                }
            } else {
                return bulidLoginDto1(userEntity1);
            }
            //微信登录
        } else if (userEntity.getToken().equals("W")) {
            UserEntity userEntity1 = loginRegisterMapper.selectOne(userEntity);
            if (CommonUtils.isEmpty(userEntity1)) {
                int i = loginRegisterMapper.insert(userEntity);
                if (i > 0) {
                    return bulidLoginDto(userEntity);
                } else {
                    return AppServiceEnums.SYS_EXCEPTION.getCode();
                }
            }
            return bulidLoginDto1(userEntity1);
            //手机登录
        } else if (userEntity.getToken().equals("P")) {
            userEntity.setPwd(MD5Utils.md5(userEntity.getPwd()));
            UserEntity entity = loginRegisterMapper.selectOne(userEntity);
            if (CommonUtils.isEmpty(entity)) {
                return buidLoginError();
            }else {
                return bulidLoginDtoPone(entity);
            }
        }
        return buidLoginError();
    }

    /**
     * @Description(功能描述): 对象构建
     * @author(作者): feihong
     * @date (开发日期):2018-4-19 9:48:32
     **/
    public LoginDTO bulidLoginDto1(UserEntity userEntity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setImage_url(userEntity.getImageUrl());
        loginDTO.setName(userEntity.getName());
        loginDTO.setSex(userEntity.getSex());
        loginDTO.setToken("1");//非首次登录
        return loginDTO;
    }

    public LoginDTO bulidLoginDto(UserEntity userEntity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setImage_url(userEntity.getImageUrl());
        loginDTO.setName(userEntity.getName());
        loginDTO.setSex(userEntity.getSex());
        loginDTO.setToken("0");//首次登录
        return loginDTO;
    }

    /**
     * @Description(功能描述): 手机登陆返回对象
     * @author(作者): feihong
     * @date (开发日期):2018-4-21 9:48:32
     **/
    public LoginDTO bulidLoginDtoPone(UserEntity userEntity) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMobphone(userEntity.getMobPhone());
        loginDTO.setName(userEntity.getName());
        loginDTO.setSex(userEntity.getSex());
        return loginDTO;
    }

    /**
      * @Description(功能描述): 登录失败
      * @author(作者): feihong
      * @date (开发日期):2018-4-21 21:44:33
      **/
    public LoginErrorDto buidLoginError(){
        LoginErrorDto loginErrorDto = new LoginErrorDto();
        loginErrorDto.setFtag("账号不存在");
        return loginErrorDto;
    }
}
