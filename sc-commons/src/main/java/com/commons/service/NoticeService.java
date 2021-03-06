package com.commons.service;

import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.anDto.NoticeDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;
import com.commons.entity.NoticeEntity;
import com.github.pagehelper.PageInfo;

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
    PageInfo<NoticeEntity> getTitleDisplay(ParamDto paramDto,Integer page,Integer rows);

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:39
     */
    PageInfo<NoticeDto> getNoticeList(ParamDto paramDto,Integer page,Integer rows);

    /**
     * @Description(功能描述) : 公告详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:00
     */
    NoticeDto getNoticeDetails(ParamDto paramDto);

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:17
     */
    PageInfo<NoticeCommentEntity> getNoticeCommentList(ParamDto paramDto,Integer page,Integer rows);
    
    /**
     * @Description(功能描述) : 发布评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 16:00
     */
    int releaseNoticeComment(NoticeCommentEntity noticeCommentEntity,LoginDTO loginDTO);

    /**
     * @Description(功能描述) : 浏览数增加
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/8 17:39
     */
    void browseNumIncreased(NoticeEntity noticeEntity);
}
