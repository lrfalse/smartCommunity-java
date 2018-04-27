package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.entity.ActivityEntity;

/**
 * @Description:社区活动
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-27 16:16:33
 */
public interface ActivityMapper extends MyMapper<ActivityEntity>{

    int updatePeopleNum(String attivityId);//更新活动人数
}
