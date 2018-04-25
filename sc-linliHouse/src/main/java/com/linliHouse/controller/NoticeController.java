package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.reDto.NoticeReDto;
import com.commons.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description(功能描述) : 小区公告
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 15:54
 */
@RestController
@RequestMapping("/")
public class NoticeController extends BaseApi {

    @Autowired
    private NoticeService noticeService;

    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/25 16:05
     */
    @PostMapping("/getNotice")
    public HttpResults getNotice(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=(IsJsonDTO)req.getAttribute("preHandleJsonDto");
        NoticeReDto noticeReDto = JSON.parseObject(jsonDto.getBodyJson(), NoticeReDto.class);
        List<String> list = noticeService.getTitleDisplay(noticeReDto.getCommunityId());
        return getHttpResult(list);
    }
}
