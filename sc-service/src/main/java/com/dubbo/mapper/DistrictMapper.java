package com.dubbo.mapper;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.DistrictEntity;
import com.commons.config.MyMapper;

import java.util.List;

/**
 * @Description(功能描述) :省市区
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/11 18:53
 **/

public interface DistrictMapper extends MyMapper<DistrictEntity> {

    /**
      * @Description(功能描述): 查询市场
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 20:31
      **/

    List<DistrictEntity> queryCity(ParamDto paramDto);
}
