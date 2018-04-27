package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description:活动参加人
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 22:22:19
 */
@Data
@Table(name = "t_activity_join")
public class ActivityJoinEntity extends BaseIdEntity{

    /**
     *活动名称
     */
    private String activityId;
    /**
     *头像地址
     */
    private String imageUrl;

    /**
     *参加人名称
     */
    private String userName;

    /**
     *参加时间
     */
    private String crateTime;
}
