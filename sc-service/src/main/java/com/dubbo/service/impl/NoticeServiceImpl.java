package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.NoticeCommentDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;
import com.commons.entity.NoticeEntity;
import com.commons.service.NoticeService;
import com.dubbo.mapper.NoticeCommentMapper;
import com.dubbo.mapper.NoticeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description(功能描述) : 小区公告
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 14:55
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeCommentMapper noticeCommentMapper;

    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/25 14:56
     */
    @Override
    public List<String> getTitleDisplay(NoticeEntity noticeEntity) {
        PageHelper.startPage(noticeEntity.getPage(), noticeEntity.getRows());
        return noticeMapper.queryNoticeTitle(noticeEntity.getCommunityId());
    }

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:41
     */
    @Override
    public List<NoticeEntity> getNoticeList(NoticeEntity noticeEntity) {
        PageHelper.startPage(noticeEntity.getPage(), noticeEntity.getRows());
        return noticeMapper.select(noticeEntity);
    }
    
    /**
     * @Description(功能描述) : 小区公告评论数
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 17:13
     */
    @Override
    public int getNoticeCommentCount(NoticeCommentEntity noticeCommentEntity) {
        return noticeCommentMapper.selectCount(noticeCommentEntity);
    }

    /**
     * @Description(功能描述) : 小区公告详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:05
     */
    @Override
    public NoticeEntity getNoticeDetails(NoticeEntity noticeEntity) {
        return noticeMapper.selectOne(noticeEntity);
    }

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 19:17
     */
    public Page<NoticeCommentDto> getNoticeCommentList(ParamDto paramDto) {
        Page<NoticeCommentDto> page=PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        noticeCommentMapper.queryNoticeCommentList(paramDto);
        return page;
    }
}
