package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.CommentDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.ActivityJoinDto;
import com.commons.dto.reDto.CommentReDto;
import com.commons.entity.ActivityEntity;
import com.commons.entity.ActivityJoinEntity;
import com.commons.entity.CommentEntity;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.ActivityService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.ActivityJoinMapper;
import com.dubbo.mapper.ActivityMapper;

import com.dubbo.mapper.CommentMapper;
import com.dubbo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Description:社区活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 16:16:22
 */
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActivityJoinMapper activityJoinMapper;
    /**
      * @Description(功能描述): 活动详情
      * @author(作者): feihong
      * @date (开发日期):2018-04-27 17:16:22
      **/
    @Override
    public ActivityEntity ActivityList(String activityId) {
        if (CommonUtils.isEmpty(activityId) && CommonUtils.isEmpty(activityId)){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        ActivityEntity activityEntity =new ActivityEntity();
        activityEntity.setId(Integer.valueOf(activityId));
        ActivityEntity entity = activityMapper.selectOne(activityEntity);
        return entity;
    }

    /**
      * @Description(功能描述): 首页活动展示
      * @author(作者): feihong
      * @date (开发日期):2018-04-27 17:16:22
      **/
    @Override
    public List<ActivityEntity> queryActivity(String communityId) {
        if (CommonUtils.isEmpty(communityId)){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        ActivityEntity activityEntity =new ActivityEntity();
        activityEntity.setCommunityId(communityId);
        List<ActivityEntity> select = activityMapper.select(activityEntity);
        return  select;
    }

    /**
      * @Description(功能描述): 平论
      * @author(作者): feihong
      * @date (开发日期):2018-04-27 19:16:22
      **/
    @Override
    public int addComment(CommentDto commentDto) {
        if (commentDto.getTag().equals("W")){
            UserEntity userEntity = new UserEntity();
            userEntity.setWopenId(commentDto.getWopenId());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = commentMapper.insert(bulidComment(commentDto,entity));
            return insert;
        }else if (commentDto.getTag().equals("Q")){
            UserEntity userEntity = new UserEntity();
            userEntity.setQopenId(commentDto.getQopenId());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = commentMapper.insert(bulidComment(commentDto,entity));
            return insert;
        }else if (commentDto.getTag().equals("P")){
            UserEntity userEntity = new UserEntity();
            userEntity.setMobPhone(commentDto.getMobPhone());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = commentMapper.insert(bulidComment(commentDto,entity));
            return insert;
        }

    throw new  ScException(AppServiceEnums.SYS_DATA_ERROR);
}
    /**
      * @Description(功能描述): 参加活动
      * @author(作者): feihong
      * @date (开发日期):2018/4/27 22:43
      **/
    @Override
    public int joinActivity(ActivityJoinDto activityJoinDto) {
        if (activityJoinDto.getTag().equals("W")){
            UserEntity userEntity = new UserEntity();
            userEntity.setWopenId(activityJoinDto.getWopenId());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = activityJoinMapper.insert(bulidActivityJoin(entity));

            return insert;
        }else if (activityJoinDto.getTag().equals("Q")){
            UserEntity userEntity = new UserEntity();
            userEntity.setQopenId(activityJoinDto.getQopenId());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = activityJoinMapper.insert(bulidActivityJoin(entity));
            return insert;
        }else if (activityJoinDto.getTag().equals("P")){
            UserEntity userEntity = new UserEntity();
            userEntity.setMobPhone(activityJoinDto.getMobPhone());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = activityJoinMapper.insert(bulidActivityJoin(entity));
            return insert;
        }
        throw new  ScException(AppServiceEnums.SYS_DATA_ERROR);
    }

    /**
     * @Description(功能描述): 查看评论
     * @author(作者): feihong
     * @date (开发日期):2018-4-27 20:44
     **/
    @Override
    public PageInfo<CommentEntity> queryComment(CommentReDto commentQueryDto) {
        ParamDto<String,String> paramDto = new ParamDto<>();
        paramDto.put("activityId",commentQueryDto.getActivityId());
         PageHelper.startPage(paramDto.getPage(), paramDto.getRows());
        PageInfo<CommentEntity> objects =new PageInfo<>(commentMapper.queryComment(paramDto));
        return objects;
    }

    /**
  * @Description(功能描述): 评论对象构建
  * @author(作者): feihong
  * @date (开发日期):2018-4-27 20:08
  **/
   public CommentEntity bulidComment(CommentDto commentDto,UserEntity entity){
       CommentEntity commentEntity =new CommentEntity();
       commentEntity.setUserId(String.valueOf(entity.getId()));
       commentEntity.setCommentTime(String.valueOf(new Date()));
       commentEntity.setActivityId(commentDto.getActivityId());
       commentEntity.setContent(commentDto.getContent());
       commentEntity.setUserName(entity.getName());
       commentEntity.setImageUrl(entity.getImageUrl());
       return commentEntity;
   }

   /**
     * @Description(功能描述): 活动加入对象构建
     * @author(作者): feihong
     * @date (开发日期):2018/4/27 22:45
     **/
   public ActivityJoinEntity bulidActivityJoin(UserEntity userEntity){
       ActivityJoinEntity activityJoinEntity =new ActivityJoinEntity();
       activityJoinEntity.setImageUrl(userEntity.getImageUrl());
       activityJoinEntity.setUserName(userEntity.getName());
       activityJoinEntity.setCrateTime(String.valueOf(new Date()));
       return activityJoinEntity;
   }
}