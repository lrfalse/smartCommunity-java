package com.dubbo.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.*;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.CommentReDto;
import com.commons.dto.reDto.UserReDto;
import com.commons.entity.ActivityEntity;
import com.commons.entity.ActivityJoinEntity;
import com.commons.entity.CommentEntity;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.ActivityService;
import com.commons.service.UserService;
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

    @Autowired
    private UserService userService;

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
            activityDto.setAbortTime(activity.getAbortTime());
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
        if (CommonUtils.isNotEmpty(commentDto.getToken())) {
            LoginDTO entity = userService.getRedisUser(commentDto.getToken());
            if (CommonUtils.isEmpty(entity)) {
                throw new ScException(AppServiceEnums.NULL_USER_DATA);
            }
            int insert = commentMapper.insert(bulidComment(commentDto, entity));
            return insert;
        }
      return 0;
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
        List<ActivityImageNameDto> activityImageNameDtos = activityMapper.queryNmaeImg(paramDto);
          PageInfo pageInfo = new PageInfo(activityImageNameDtos);
        return pageInfo;
    }

    /**
     * @Description(功能描述): 我参与的活动
     * @author(作者): feihong
     * @date (开发日期):2018/5/2 11:15
     **/
    @Override
    public PageInfo<JoinActityDto> injoin(UserReDto userReDto) {
             LoginDTO entity = userService.getRedisUser(userReDto.getToken());
             ParamDto paramAcDto = new ParamDto();
             paramAcDto.put("id",entity.getUserId());
            List<JoinActityDto> dtos = activityMapper.queryActivityJoin(paramAcDto);
            if (dtos.size()==0){
                throw new ScException(AppServiceEnums.NOT_JOIN_ACTIVITY);
            }
            PageInfo<JoinActityDto> pageInfo = new PageInfo<>(dtos);
            return pageInfo;
    }

    /**
     * @Description(功能描述): 参加活动
     * @author(作者): feihong
     * @date (开发日期):2018/4/27 22:43
	 * @return 0:失败 1：成功
     **/

	public int  joinActivityxx(UserReDto userReDto) {
        LoginDTO user = userService.getRedisUser(userReDto.getToken());
        ParamDto paramAcDto = new ParamDto();
		paramAcDto.put("activityId",userReDto.getActivityId());
		List<String> list = activityMapper.queryUserId(paramAcDto);
		if (list.contains(String.valueOf(user.getUserId()))){
			throw new ScException(AppServiceEnums.EXIST_JOIN);
		}
		int insert = activityJoinMapper.insert(bulidActivityJoin(userReDto,user));
		int i = activityMapper.updatePeopleNum(userReDto.getActivityId());
		if (isinsert(i,insert)){
			return 1;			//成功
		}
		return 0;				//失败
	}

    /**
      * @Description(功能描述): 活动详情
      * @author(作者): feihong
      * @date (开发日期):2018/5/17 15:17
      **/
    public ActivityDetailDto findActivityDetail(String activityId) {
        if (CommonUtils.isEmpty(activityId)){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        ParamDto paramDto = new ParamDto();
        paramDto.put("activityId",activityId);
        ActivityDetailDto activityDetail = activityMapper.findActivityDetail(paramDto);
        return activityDetail;
    }


    /**
     * @Description(功能描述): 评论对象构建
     * @author(作者): feihong
     * @date (开发日期):2018-4-27 20:08
     **/
    public CommentEntity bulidComment(CommentDto commentDto,LoginDTO entity){
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CommentEntity commentEntity =new CommentEntity();
        commentEntity.setUserId(String.valueOf(entity.getUserId()));
        commentEntity.setCommentTime(format0.format(new Date()));
        commentEntity.setActivityId(commentDto.getActivityId());
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setUserName(entity.getName());
        commentEntity.setImgUrl(entity.getImg_url());
        commentEntity.setStatus("0");
        return commentEntity;
    }

    /**
     * @Description(功能描述): 活动加入对象构建
     * @author(作者): feihong
     * @date (开发日期):2018/4/27 22:45
     **/
    public ActivityJoinEntity bulidActivityJoin(UserReDto userReDto,LoginDTO loginDTO){
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ActivityJoinEntity activityJoinEntity =new ActivityJoinEntity();
        activityJoinEntity.setImgUrl(loginDTO.getImg_url());
        activityJoinEntity.setUserName(loginDTO.getName());
        activityJoinEntity.setCrateTime(format0.format(new Date()));
        activityJoinEntity.setActivityId(userReDto.getActivityId());
        activityJoinEntity.setUserId(String.valueOf(loginDTO.getUserId()));
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