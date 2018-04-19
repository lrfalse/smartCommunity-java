package com.linli.controller;


import com.commons.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:登录注册
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-10 09:9:44
 */
@RestController
public class LoginRegisterController {

    /**
      * @Description(功能描述): 微信登录
      * @author(作者): feihong
      * @date (开发日期):2018-4-18 14:41:03
      **/
    @PostMapping("/weixin")
    public String weixinLogin(UserEntity userEntity){

        return "";
    }
}
