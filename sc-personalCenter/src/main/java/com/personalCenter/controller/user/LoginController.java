package com.personalCenter.controller.user;


import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.anDto.BindPhoneDto;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.LoginDTO;
import com.commons.entity.UserEntity;

import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:登录注册
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-10 09:9:44
 */
@RestController
public class LoginController extends BaseApi {


    @Autowired
    private UserService userService;
    /**
     * @Description(功能描述): 登录
     * @author(作者): feihong
     * @date (开发日期):2018-4-18 14:41:03
     **/
    @PostMapping("/login")
    public HttpResults login(HttpServletRequest req) throws Exception {
        IsJsonDTO jsonDto = (IsJsonDTO) req.getAttribute("preHandleJsonDto");
        UserEntity entity = JSON.parseObject(jsonDto.getBodyJson(), UserEntity.class);
        LoginDTO login = userService.logIn(entity);
        return getHttpResult(login);
    }

    /**
     * @Description(功能描述): 绑定手机号码
     * @author(作者): feihong
     * @date (开发日期):2018-2-20 16:16:23
     **/
    @PostMapping("/bindphone")
    public HttpResults bindPhone(HttpServletRequest req) throws Exception {
        IsJsonDTO jsonDto = (IsJsonDTO)req.getAttribute("preHandleJsonDto");
        UserEntity entity = JSON.parseObject(jsonDto.getBodyJson(), UserEntity.class);
        LoginDTO loginDTO = userService.bindPhone(entity);
        return getHttpResult(loginDTO);
    }

    /**
     * @Description(功能描述): 找回密码
     * @author(作者): feihong
     * @date (开发日期):2018-5-4 22:00:33
     **/
    @PostMapping("/backpwd")
    public HttpResults backPwd(HttpServletRequest req) throws Exception {
        IsJsonDTO isJsonDTO = (IsJsonDTO) req.getAttribute("preHandleJsonDto");
        UserEntity user = JSON.parseObject(isJsonDTO.getBodyJson(), UserEntity.class);
        if(CommonUtils.isEmpty(user.getPwd() ) || CommonUtils.isEmpty(user.getMobPhone())){
            return getHttpResultError();
        }else {
            int result=userService.updateUserByKey(user);
            return getHttpResult(result);
        }
    }
}
