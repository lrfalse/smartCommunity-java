package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.service.NoticeService;
import com.dubbo.mapper.NoticeMapper;
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

    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/25 14:56
     */
    @Override
    public List<String> getTitleDisplay(String communityId) {
        return noticeMapper.queryNoticeTitle(communityId);
    }
}
