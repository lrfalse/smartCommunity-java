package com.commons.service;

import com.commons.dto.anDto.BannerDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.BannerEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Description(功能描述) : 首页轮播图
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 22:01
 */
public interface BannerService {

    /**
     * @Description(功能描述) : 轮播图数据
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 22:05
     */
    PageInfo<BannerDto> getBannerList(ParamDto paramDto,Integer page,Integer rows);
}
