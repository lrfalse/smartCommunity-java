package com.commons.service;

import com.commons.dto.anDto.CommentDto;
import com.commons.dto.anDto.CommentQueryDto;
import com.commons.dto.reDto.ActivityJoinDto;
import com.commons.entity.ActivityEntity;
import com.commons.entity.CommentEntity;
import com.github.pagehelper.Page;


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
    List<ActivityEntity> queryActivity(String communityId);

    /**
     * @Description(功能描述): 活动详情
     * @author(作者): feihong
     * @date (开发日期):2018-04-27 17:15:52
     **/
    ActivityEntity ActivityList(String activityId);

    /**
      * @Description(功能描述): 评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 19:23
      **/
    int addComment(CommentDto commentDto);

    /**
      * @Description(功能描述): 查看评论
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 20:42
      **/
    Page<CommentEntity> queryComment(CommentQueryDto commentQueryDto);

    /** 
      * @Description(功能描述): 加入活动
      * @author(作者): feihong
      * @date (开发日期):2018/4/27 22:37
      **/
    int joinActivity(ActivityJoinDto activityJoinDto);



}
