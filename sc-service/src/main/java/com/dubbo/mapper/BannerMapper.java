package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.BannerEntity;

import java.util.List;

/**
 * @Description(功能描述) : 首页轮播图
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 22:18
 */
public interface BannerMapper extends MyMapper<BannerEntity> {

    /**
     * @Description(功能描述) : 首页轮播图数据
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/28 9:18
     */
    List<BannerEntity> queryBannerList(ParamDto paramDto);
}
