package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.ChatTypeEntity;

import java.util.List;

/**
 * @Description(功能描述) : 邻里聊天室类型
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/4 9:35
 */
public interface ChatTypeMapper extends MyMapper<ChatTypeEntity> {

    /**
     * @Description(功能描述) : 查询该类型下的问题发布人总数 - 去重
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/22 18:30
     */
    Integer queryTypeCount(ParamDto paramDto);

    /**
     * @Description(功能描述) : 查询类型列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/22 18:38
     */
    List<ChatTypeEntity> queryType(ParamDto paramDto);
}
