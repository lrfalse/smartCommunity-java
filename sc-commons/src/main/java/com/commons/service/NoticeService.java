package com.commons.service;

import com.commons.dto.anDto.NoticeCommentDto;
import com.commons.dto.anDto.NoticeDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;
import com.commons.entity.NoticeEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description(功能描述) : 小区公告
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 14:04
 */
public interface NoticeService {
    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/25 14:48
     */
    List<String> getTitleDisplay(NoticeEntity noticeEntity);

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:39
     */
    List<NoticeDto> getNoticeList(ParamDto paramDto);

    /**
     * @Description(功能描述) : 公告详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:00
     */
    NoticeEntity getNoticeDetails(NoticeEntity noticeEntity);

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:17
     */
    PageInfo<NoticeCommentDto> getNoticeCommentList(ParamDto paramDto);
    
    /**
     * @Description(功能描述) : 发布评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 16:00
     */
    int releaseNoticeComment(NoticeCommentEntity noticeCommentEntity);
}
