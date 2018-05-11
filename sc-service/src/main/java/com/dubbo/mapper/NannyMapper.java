package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NannyEntity;

import java.util.List;

public interface NannyMapper extends MyMapper<NannyEntity>{

    /**
      * @Description(功能描述): 倒序保姆列表查询
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 16:49
      **/
    List<NannyEntity> queryNannyDesc(ParamDto paramDto);
    /**
     * @Description(功能描述): 正序保姆列表查询
     * @author(作者): feihong
     * @date (开发日期):2018/5/8 16:49
     **/
    List<NannyEntity> queryNannyAsc(ParamDto paramDto);
    /**
      * @Description(功能描述): 查询保姆详细
      * @author(作者): feihong
      * @date (开发日期):2018/5/9 15:57
      **/
    NannyEntity queryEntity(ParamDto paramDto);
}