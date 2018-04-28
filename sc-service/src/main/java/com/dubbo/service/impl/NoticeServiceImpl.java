package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.NoticeCommentDto;
import com.commons.dto.anDto.NoticeDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;
import com.commons.entity.NoticeEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.NoticeService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.NoticeCommentMapper;
import com.dubbo.mapper.NoticeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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
    public PageInfo<String> getTitleDisplay(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        List<String> list = noticeMapper.queryNoticeTitle(paramDto);
        PageInfo<String> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:41
     */
    @Override
    public PageInfo<NoticeDto> getNoticeList(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        List<NoticeDto> list = noticeMapper.queryNoticeList(paramDto);
        PageInfo<NoticeDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @Description(功能描述) : 小区公告详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:05
     */
    @Override
    public NoticeEntity getNoticeDetails(NoticeEntity noticeEntity) {
        NoticeEntity data = noticeMapper.selectOne(noticeEntity);
        //浏览次数增加
        this.browseNumIncreased(data);
        return data;
    }

    /**
     * @Description(功能描述) : 浏览次数增加
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 15:17
     */
    private void browseNumIncreased(NoticeEntity noticeEntity){
        if(CommonUtils.isEmpty(noticeEntity.getBrowseNum())){
            noticeEntity.setBrowseNum(1);
        }else{
            noticeEntity.setBrowseNum(noticeEntity.getBrowseNum()+1);
        }
        noticeMapper.updateByPrimaryKey(noticeEntity);
    }

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 19:17
     */
    @Override
    public PageInfo<NoticeCommentDto> getNoticeCommentList(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        List<NoticeCommentDto> list = noticeCommentMapper.queryNoticeCommentList(paramDto);
        PageInfo<NoticeCommentDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @Description(功能描述) : 发布评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 15:54
     */
    @Override
    public int releaseNoticeComment(NoticeCommentEntity noticeCommentEntity) {
        if(noticeCommentEntity!=null){
            noticeCommentEntity.setStatus(0);
            noticeCommentEntity.setCommentTime(new Date());
            int n = noticeCommentMapper.insert(noticeCommentEntity);
            if (n < 0) {
                throw new ScException(AppServiceEnums.SYS_EXCEPTION);
            }
            return 1;
        }else{
            return 0;
        }
    }
}
