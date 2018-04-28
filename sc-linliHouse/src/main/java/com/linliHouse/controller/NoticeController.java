package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.NoticeCommentDto;
import com.commons.dto.anDto.NoticeDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NoticeCommentEntity;
import com.commons.entity.NoticeEntity;
import com.commons.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) : 小区公告
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/4/25 15:54
 */
@RestController
@RequestMapping("/")
public class NoticeController extends BaseApi {

    @Autowired
    private NoticeService noticeService;

    /**
     * @Description(功能描述) : 首页小区公告信息(标题显示)
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/25 16:05
     */
    @PostMapping("/getNotice")
    public HttpResults getNotice(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        NoticeEntity noticeEntity = JSON.parseObject(jsonDto.getBodyJson(), NoticeEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",noticeEntity.getCommunityId());
        PageInfo<String> pageInfo = noticeService.getTitleDisplay(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 小区公告列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 9:35
     */
    @PostMapping("/getNoticeList")
    public HttpResults getNoticeList(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        NoticeDto noticeDto = JSON.parseObject(jsonDto.getBodyJson(), NoticeDto.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",noticeDto.getCommunityId());
        PageInfo<NoticeDto> pageInfo = noticeService.getNoticeList(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 小区公告详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 17:58
     */
    @PostMapping("/getNoticeDetails")
    public HttpResults getNoticeDetails(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        NoticeEntity noticeEntity = JSON.parseObject(jsonDto.getBodyJson(), NoticeEntity.class);
        NoticeEntity data = noticeService.getNoticeDetails(noticeEntity);
        return getHttpResult(data);
    }

    /**
     * @Description(功能描述) : 小区公告详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/26 18:14
     */
    @PostMapping("/getNoticeCommentDetails")
    public HttpResults getNoticeCommentDetails(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        NoticeCommentEntity noticeCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), NoticeCommentEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("noticeId",noticeCommentEntity.getNoticeId());
        paramDto.put("status",noticeCommentEntity.getStatus());
        PageInfo<NoticeCommentDto> pageInfo = noticeService.getNoticeCommentList(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 公告发布评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/4/27 15:46
     */
    @PostMapping("/releaseNoticeComment")
    public HttpResults releaseNoticeComment(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        NoticeCommentEntity noticeCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), NoticeCommentEntity.class);
        int n = noticeService.releaseNoticeComment(noticeCommentEntity);
        return getHttpResult(n);
    } 

}
