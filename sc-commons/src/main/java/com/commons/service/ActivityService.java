package com.commons.service;

import com.commons.dto.anDto.*;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.ActivityJoinDto;
import com.commons.dto.reDto.CommentReDto;
import com.commons.entity.ActivityEntity;
import com.commons.entity.CommentEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @Description:社区活动
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-27 15:15:52
 */
public interface ActivityService {

    /**
      * @Description(功能描述): 首页活动
      * @author(作者): feihong
      * @date (开发日期):2018-04-27 17:15:52
      **/
    List<ActivityDto> queryActivityDetail(ActivityListDto activityListDto);

    /**
      * @Description(功能描述): 评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 19:23
      **/
    int Comment(CommentDto commentDto);

    /**
      * @Description(功能描述): 查看评论
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 20:42
      **/
    PageInfo<CommentEntity> queryComment(CommentReDto commentReDto);

    /** 
      * @Description(功能描述): 加入活动
      * @author(作者): feihong
      * @date (开发日期):2018/4/27 22:37
      **/
    int joinActivityxx(ActivityJoinDto activityJoinDto);

    /**
      * @Description(功能描述): 我参加的活动
      * @author(作者): feihong
      * @date (开发日期):2018/5/2 14:10
      **/
    PageInfo<JoinActityDto> injoin(CommentDto userId);

    /**
      * @Description(功能描述): 查询活动参与所有人员
      * @author(作者): feihong
      * @date (开发日期):2018/5/2 20:00
      **/
    PageInfo<ActivityImageNameDto> getPreson(CommentReDto commentReDto);

    /**
      * @Description(功能描述): 活动详情
      * @author(作者): feihong
      * @date (开发日期):2018/5/17 15:13
      **/
    ActivityDetailDto findActivityDetail(String activityId);
}
