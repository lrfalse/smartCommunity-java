package com.commons.service;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.BannerEntity;

import java.util.List;

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
    List<BannerEntity> getBannerList(ParamDto paramDto);
}
