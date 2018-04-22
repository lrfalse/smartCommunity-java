package com.personalCenter.controller;


import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.enums.AppServiceEnums;
import com.personalCenter.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private LoginRegisterService loginRegisterService;

    /**
      * @Description(功能描述): 登录
      * @author(作者): feihong
      * @date (开发日期):2018-4-18 14:41:03
      **/
    @PostMapping("/login")
    public HttpResults login(HttpServletRequest req) throws Exception {
        Object obj = loginRegisterService.login((IsJsonDTO) req.getAttribute("preHandleJsonDto"));
        if (obj.equals(AppServiceEnums.SYS_EXCEPTION.getCode()) || obj.equals(AppServiceEnums.SYS_DATA_ERROR.getCode())){
            return getHttpResultError();
        }
        return  getHttpResult(obj);
    }


}
