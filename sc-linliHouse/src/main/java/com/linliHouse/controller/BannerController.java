package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.BannerEntity;
import com.commons.service.BannerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) : 首页轮播图Controller
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 22:07
 */
@RestController
@RequestMapping("/")
public class BannerController extends BaseApi {

    @Autowired
    private BannerService bannerService;

    /**
     * @Description(功能描述) : 首页轮播图数据
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 22:11
     */
    @PostMapping("/getBannerList")
    public HttpResults getBannerList(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        BannerEntity noticeDto = JSON.parseObject(jsonDto.getBodyJson(), BannerEntity.class);
        //TODO 传入参数未确定
        ParamDto paramDto = new ParamDto();
        PageInfo<BannerEntity> pageInfo = bannerService.getBannerList(paramDto);
        return getHttpResult(pageInfo);
    }
}
