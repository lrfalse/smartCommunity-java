package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.anDto.ActivityImageNameDto;
import com.commons.dto.anDto.ActivityPeopleDto;
import com.commons.dto.anDto.JoinActityDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.ActivityEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:社区活动
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-27 16:16:33
 */
public interface ActivityMapper extends MyMapper<ActivityEntity>{

    int updatePeopleNum(@Param("id")String id);//更新活动人数

    List<ActivityPeopleDto> queryActivityPeople(@Param("activityId")String activityId);//查询参加活动人头像地址

    List<ActivityImageNameDto> queryNmaeImage(ParamDto paramDto);//查询参加人名称及头像

    List<JoinActityDto> queryActivityJoin(ParamDto paramDto);//查询我参与的活动
}
