package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.anDto.NoticeCommentDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;

import java.util.List;

/**
 * @Description(功能描述) : 小区公告评论
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/26 17:10
 */
public interface NoticeCommentMapper extends MyMapper<NoticeCommentEntity> {

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 19:30
     */
    List<NoticeCommentDto> queryNoticeCommentList(ParamDto paramDto);
}
