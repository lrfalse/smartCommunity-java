package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:首页活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 16:16:18
 */
@Data
public class ActivityListDto implements Serializable{

    /**
     * 小区
     */
    private String activityId;

    /**
     *活动名称
     */
    private String page;
}
