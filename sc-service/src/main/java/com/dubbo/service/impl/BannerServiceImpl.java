package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.BannerEntity;
import com.commons.service.BannerService;
import com.dubbo.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description(功能描述) : 首页轮播图
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/27 22:15
 */
@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    private BannerMapper bannerMapper;

    /**
     * @Description(功能描述) : 首页轮播图数据
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 22:22
     */
    @Override
    public List<BannerEntity> getBannerList(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        List<BannerEntity> list = bannerMapper.selectAll();
        return list;
    }
}
