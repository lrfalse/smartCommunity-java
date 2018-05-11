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
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",activityListDto.getCommunityId());
        List<ActivityEntity> activityEntities=null;
        //首页查询五个活动
        if (activityListDto.getTag().equals("0")){
            //PageHelper.startPage(activityListDto.getPages(),activityListDto.getPageSize());
            List<ActivityEntity> activityEntities1 = activityMapper.homeQueryActivity(paramDto);
            activityEntities=activityEntities1;
        }else if (activityListDto.getTag().equals("1")){
            PageHelper.startPage(activityListDto.getPages(),activityListDto.getPageSize());
            List<ActivityEntity> activityEntities2 = activityMapper.queryActivity(paramDto);
            activityEntities=activityEntities2;
        }
        for (ActivityEntity activity : activityEntities) {
            ActivityDto activityDto = new ActivityDto();
            activityDto.setActivityId(String.valueOf(activity.getId()));
            activityDto.setImgUrl(activity.getImgUrl());
            activityDto.setTitle(activity.getTitle());
            activityDto.setPeopleNum(activity.getPeopleNum());
            Long id = activity.getId();
            //根据活动id查询用户头像地址
            List<ActivityPeopleDto> dtos = activityMapper.queryActivityPeople(String.valueOf(id));
            activityDto.setList(dtos);
            list.add(activityDto);
        }
        return  list;
    }



    /**
     * @Description(功能描述): 平论
     * @author(作者): feihong
     * @date (开发日期):2018-04-27 19:16:22
     **/
    @Override
    public int Comment(CommentDto commentDto) {
        ParamDto paramDto = new ParamDto();
        if (commentDto.getTag().equals("W")){
            paramDto.put("wopenId",commentDto.getWopenId());
            UserEntity entity = userMapper.selectUserId(paramDto);
            if (entity.equals(null)){
                throw new ScException(AppServiceEnums.SYS_DATA_ERROR);}
            int insert = commentMapper.insert(bulidComment(commentDto,entity));
            return insert;
        }else if (commentDto.getTag().equals("Q")){
            paramDto.put("qopenId",commentDto.getQopenId());
            UserEntity entity = userMapper.selectUserId(paramDto);
            if (entity.equals(null)){
                throw new ScException(AppServiceEnums.SYS_DATA_ERROR);}
            int insert = commentMapper.insert(bulidComment(commentDto,entity));
            return insert;
        }else if (commentDto.getTag().equals("P")){
            paramDto.put("mobPhone",commentDto.getMobPhone());
            UserEntity entity = userMapper.selectUserId(paramDto);
            if (entity.equals(null)){
                throw new ScException(AppServiceEnums.SYS_DATA_ERROR);}
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
        ParamDto paramDto = new ParamDto();
        paramDto.put("activityId",commentReDto.getActivityId());
        paramDto.put("status",commentReDto.getStatus());
        PageHelper.startPage(commentReDto.getPages(), commentReDto.getPageSize());
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
        public PageInfo<ActivityImageNameDto> getPreson(CommentReDto commentReDto) {
        ParamDto paramDto = new ParamDto();
        paramDto.put("activityId",commentReDto.getActivityId());
        PageHelper.startPage(commentReDto.getPages(),commentReDto.getPageSize());
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
    public PageInfo<JoinActityDto> injoin(CommentDto commentDto) {
        ParamDto paramAcDto = new ParamDto();
        if (commentDto.getTag().equals("W")){
            paramAcDto.put("wopenId",commentDto.getWopenId());
            UserEntity entity = userMapper.selectUserId(paramAcDto);
            ParamDto paramDto = new ParamDto();
            paramDto.put("id",entity.getId());
            //PageHelper.startPage(commentDto.getPages(),commentDto.getPageSize());
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            if (dtos.size()==0){
                throw new ScException(AppServiceEnums.NOT_JOIN_ACTIVITY);
            }
            PageInfo<JoinActityDto> pageInfo = new PageInfo<>(dtos);
            return pageInfo;
        }else if (commentDto.getTag().equals("P")){
            paramAcDto.put("mobPhone",commentDto.getMobPhone());
            UserEntity entity = userMapper.selectUserId(paramAcDto);
            ParamDto paramDto = new ParamDto();
            paramDto.put("id",entity.getId());
            //PageHelper.startPage(commentDto.getPages(),commentDto.getPageSize());
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            if (dtos.size()==0){
                throw new ScException(AppServiceEnums.NOT_JOIN_ACTIVITY);
            }
            PageInfo<JoinActityDto> pageInfo = new PageInfo<>(dtos);
            return pageInfo;
        }else if (commentDto.getTag().equals("Q")) {
            paramAcDto.put("qopenId",commentDto.getQopenId());
            UserEntity entity = userMapper.selectUserId(paramAcDto);
            ParamDto paramDto = new ParamDto();
            paramDto.put("id", entity.getId());
            //PageHelper.startPage(commentDto.getPages(),commentDto.getPageSize());
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramDto);
            if (dtos.size()==0){
                throw new ScException(AppServiceEnums.NOT_JOIN_ACTIVITY);
            }
            PageInfo<JoinActityDto> pageInfo = new PageInfo<>(dtos);
            return pageInfo;
        }
        throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
    }

    /**
     * @Description(功能描述): 参加活动
     * @author(作者): feihong
     * @date (开发日期):2018/4/27 22:43
	 * @return 0:失败 1：成功
     **/

	public int joinActivityxx(ActivityJoinDto activityJoinDto) {
		ParamDto paramAcDto = new ParamDto();
		paramAcDto.put("activityId",activityJoinDto.getActivityId());
		ParamDto paramDto = new ParamDto();
		if(CommonUtils.isNotEmpty(activityJoinDto.getWopenId())){
			paramDto.put("wopenId",activityJoinDto.getWopenId());
		}else if(CommonUtils.isNotEmpty(activityJoinDto.getQopenId())){
			paramDto.put("qopenId",activityJoinDto.getQopenId());
		}else if(CommonUtils.isNotEmpty(activityJoinDto.getMobPhone())){
			paramDto.put("mobPhone",activityJoinDto.getMobPhone());
		}else{
			throw new  ScException(AppServiceEnums.SYS_DATA_ERROR);
		}
		UserEntity entity = userMapper.selectUserId(paramDto);
		List<String> list = activityMapper.queryUserId(paramAcDto);
		if (list.contains(String.valueOf(entity.getId()))){
			throw new ScException(AppServiceEnums.EXIST_JOIN);
		}
		int insert = activityJoinMapper.insert(bulidActivityJoin(activityJoinDto,entity));
		int i = activityMapper.updatePeopleNum(activityJoinDto.getActivityId());
		if (isinsert(i,insert)){
			return 1;			//成功
		}
		return 0;				//失败
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