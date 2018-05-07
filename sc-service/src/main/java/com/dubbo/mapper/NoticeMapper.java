package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.anDto.NoticeDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeEntity;

import java.util.List;

/**
 * @Description(功能描述) : 小区公告
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 14:52
 */
public interface NoticeMapper extends MyMapper<NoticeEntity> {

    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:41
     */
    List<NoticeEntity> queryNoticeTitle(ParamDto paramDto);

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 13:43
     */
    List<NoticeDto> queryNoticeList(ParamDto paramDto);
}
