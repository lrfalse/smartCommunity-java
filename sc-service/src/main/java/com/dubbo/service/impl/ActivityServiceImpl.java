package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.*;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * @Description(功能描述): 首页活动展示及活动详情
     * @author(作者): feihong
     * @date (开发日期):2018-04-27 17:16:22
     **/
    @Override
    public List<ActivityDto> queryActivityDetail(ActivityListDto activityListDto) {
        if (CommonUtils.isEmpty(activityListDto)){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        List<ActivityDto> list = new ArrayList<>();
        ActivityEntity activityEntity =new ActivityEntity();
        activityEntity.setCommunityId(activityListDto.getCommunityId());
        PageHelper.startPage(activityListDto.getPages(),activityListDto.getPageSize());
        List<ActivityEntity> activityEntities = activityMapper.select(activityEntity);
        for (ActivityEntity activity : activityEntities) {
            ActivityDto activityDto = new ActivityDto();
            activityDto.setImgUrl(activity.getImgUrl());
            activityDto.setTitle(activity.getTitle());
            activityDto.setPeopleNum(activity.getPeopleNum());
            Integer id = activity.getId();
            //根据活动id查询用户头像地址
            List<ActivityPeopleDto> dtos = activityMapper.queryActivityPeople(String.valueOf(id));
            activityDto.setList(dtos);
            list.add(activityDto);
        }
        System.out.println(list);
        return  list;
    }



    /**
     * @Description(功能描述): 平论
     * @author(作者): feihong
     * @date (开发日期):2018-04-27 19:16:22
     **/
    @Override
    public int Comment(CommentDto commentDto) {
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
     * @Description(功能描述): 查看评论
     * @author(作者): feihong
     * @date (开发日期):2018-4-27 20:44
     **/
    @Override
    public PageInfo<CommentEntity> queryComment(CommentReDto commentReDto) {
        PageHelper.startPage(commentReDto.getPages(), commentReDto.getPageSize());
        ParamDto paramDto = new ParamDto();
        paramDto.put("activityId",commentReDto.getActivityId());
        List<CommentEntity> commentEntities = commentMapper.queryComment(paramDto);
        PageInfo<CommentEntity> pageInfo = new PageInfo<>(commentEntities);
        return pageInfo;
    }

    /**
     * @Description(功能描述): 查询活动参与所有人员
     * @author(作者): feihong
     * @date (开发日期):2018/5/2 20:02
     **/
    @Override
    public PageInfo<ActivityImageNameDto> getPreson(ParamDto paramDto) {
        List<ActivityImageNameDto> activityImageNameDtos = activityMapper.queryNmaeImage(paramDto);
        PageInfo pageInfo = new PageInfo(activityImageNameDtos);
        return pageInfo;
    }

    /**
     * @Description(功能描述): 我参与的活动
     * @author(作者): feihong
     * @date (开发日期):2018/5/2 11:15
     **/
    @Override
    public PageInfo<JoinActityDto> join(CommentDto commentDto) {
        if (commentDto.getTag().equals("W")){
            String wopenId = commentDto.getWopenId();
            UserEntity userEntity = new UserEntity();
            userEntity.setWopenId(wopenId);
            UserEntity entity = userMapper.selectOne(userEntity);
            Integer id = entity.getId();
            ParamDto paramDto = new ParamDto();
            paramDto.put("id",id);
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            PageInfo pageInfo = new PageInfo(dtos);
            return pageInfo;
        }else if (commentDto.getTag().equals("P")){
            String mobPhone = commentDto.getMobPhone();
            UserEntity userEntity = new UserEntity();
            userEntity.setMobPhone(mobPhone);
            UserEntity entity = userMapper.selectOne(userEntity);
            Integer id = entity.getId();
            ParamDto paramDto = new ParamDto();
            paramDto.put("id",id);
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            PageInfo pageInfo = new PageInfo(dtos);
            return pageInfo;
        }else if (commentDto.getTag().equals("Q")) {
            String qopenId = commentDto.getQopenId();
            UserEntity userEntity = new UserEntity();
            userEntity.setQopenId(qopenId);
            UserEntity entity = userMapper.selectOne(userEntity);
            Integer id = entity.getId();
            ParamDto paramDto = new ParamDto();
            paramDto.put("id", id);
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            PageInfo pageInfo = new PageInfo(dtos);
            return pageInfo;
        }
        throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
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
            int insert = activityJoinMapper.insert(bulidActivityJoin(activityJoinDto,entity));
            int i = activityMapper.updatePeopleNum(activityJoinDto.getActivityId());
            if (isinsert(i,insert)){
                return 1;
            }
            return 0;
        }else if (activityJoinDto.getTag().equals("Q")){
            UserEntity userEntity = new UserEntity();
            userEntity.setQopenId(activityJoinDto.getQopenId());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = activityJoinMapper.insert(bulidActivityJoin(activityJoinDto,entity));
            int i = activityMapper.updatePeopleNum(activityJoinDto.getActivityId());
            if (isinsert(i,insert)){
                return 1;
            }
            return 0;
        }else if (activityJoinDto.getTag().equals("P")){
            UserEntity userEntity = new UserEntity();
            userEntity.setMobPhone(activityJoinDto.getMobPhone());
            UserEntity entity = userMapper.selectOne(userEntity);
            int insert = activityJoinMapper.insert(bulidActivityJoin(activityJoinDto,entity));
            int i = activityMapper.updatePeopleNum(activityJoinDto.getActivityId());
            if (isinsert(i,insert)){
                return 1;
            }
            return 0;
        }
        throw new  ScException(AppServiceEnums.SYS_DATA_ERROR);
    }


    /**
     * @Description(功能描述): 评论对象构建
     * @author(作者): feihong
     * @date (开发日期):2018-4-27 20:08
     **/
    public CommentEntity bulidComment(CommentDto commentDto,UserEntity entity){
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CommentEntity commentEntity =new CommentEntity();
        commentEntity.setUserId(String.valueOf(entity.getId()));
        commentEntity.setCommentTime(format0.format(new Date()));
        commentEntity.setActivityId(commentDto.getActivityId());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setUserName(entity.getName());
        commentEntity.setImgUrl(entity.getImgUrl());
        commentEntity.setStatus("0");
        return commentEntity;
    }

    /**
     * @Description(功能描述): 活动加入对象构建
     * @author(作者): feihong
     * @date (开发日期):2018/4/27 22:45
     **/
    public ActivityJoinEntity bulidActivityJoin(ActivityJoinDto activityJoinDto,UserEntity userEntity){
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ActivityJoinEntity activityJoinEntity =new ActivityJoinEntity();
        activityJoinEntity.setImgUrl(userEntity.getImgUrl());
        activityJoinEntity.setUserName(userEntity.getName());
        activityJoinEntity.setCrateTime(format0.format(new Date()));
        activityJoinEntity.setActivityId(activityJoinDto.getActivityId());
        activityJoinEntity.setUserId(String.valueOf(userEntity.getId()));
        return activityJoinEntity;
    }

    /**
     * @Description(功能描述): 判断评论是否成功
     * @author(作者): feihong
     * @date (开发日期):2018/5/2 10:30
     **/
    public boolean isinsert(int i,int insert){
        if (insert>0){
            if (i>0){
                return true;
            }
            return false;
        }
        return false;
    }
}