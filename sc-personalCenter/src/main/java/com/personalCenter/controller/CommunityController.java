package com.personalCenter.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.BindPhoneDto;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.enums.AppServiceEnums;
import com.personalCenter.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:小区选择
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-20 15:15:02
 */
@RestController
public class CommunityController extends BaseApi{

    @Autowired
    private CommunityService CommunityService;

    /**
     * @Description(功能描述): 小区定位选择
     * @author(作者): feihong
     * @date (开发日期):2018-4-19 18:23:33
     **/
    @PostMapping("/positioncommunity")
    public HttpResults positionCommunity(HttpServletRequest req) throws Exception {
        Object obj=CommunityService.communityquery((IsJsonDTO)req.getAttribute("preHandleJsonDto"));
        if (obj.equals(AppServiceEnums.SYS_DATA_ERROR.getCode())){
            return getHttpResultError();
        }
        return getHttpResult(obj);
    }

    /**
      * @Description(功能描述): 小区检索
      * @author(作者): feihong
      * @date (开发日期):2018-4-20 15:15:23
      **/
    @PostMapping("/choosecommunity")
    public HttpResults chooseCommunity(HttpServletRequest req)throws Exception{
        Object obj = CommunityService.chooseCommunity((IsJsonDTO)req.getAttribute("preHandleJsonDto"));
        if(obj.equals(AppServiceEnums.SYS_DATA_ERROR.getCode())){
            return getHttpResultError();
        }
        return getHttpResult(obj);
    }

    /**
      * @Description(功能描述): 绑定手机号码
      * @author(作者): feihong
      * @date (开发日期):2018-2-20 16:16:23
      **/
    @PostMapping("/bindphone")
    public HttpResults bindPhone(HttpServletRequest req) throws Exception {
        BindPhoneDto bindPhone = CommunityService.bindPhone((IsJsonDTO) req.getAttribute("preHandleJsonDto"));
        return getHttpResult(bindPhone);
    }
}
