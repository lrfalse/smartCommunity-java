package com.personalCenter.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.entity.ContactUsEntity;
import com.commons.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) : 联系我们Controller
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 10:59
 */
@RestController
@RequestMapping("/")
public class ContactUsController extends BaseApi {

    @Autowired
    private ContactUsService contactUsService;

    /**
     * @Description(功能描述) : 联系我们
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 11:04
     */
    @PostMapping("/getContactUs")
    public HttpResults getContactUs(HttpServletRequest req)throws Exception{
        ContactUsEntity contactUsEntity = contactUsService.getContactUs();
        return getHttpResult(contactUsEntity);
    }
}
