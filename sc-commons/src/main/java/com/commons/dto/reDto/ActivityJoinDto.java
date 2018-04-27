package com.commons.dto.reDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:活动参加
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 22:22:28
 */
@Data
public class ActivityJoinDto implements Serializable{

    /**
     *用户唯一id W:微信 Q:qq P:手机
     */
    private String tag;

    /**
     *活动id
     */
    private String activityId;

    /**
     *用户id
     */
    private String qopenId;

    /**
     *微信id
     */
    private String wopenId;

    /**
     *手机号码
     */
    private String mobPhone;
}
