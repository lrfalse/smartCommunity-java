package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:评论
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 19:19:43
 */
@Data
public class CommentDto implements Serializable{
    /**
     *用户id
     */
    private String userId;

    /**
     *活动
     */
    private String activityId;

    /**
     *评论内容
     */
    private String content;

    /**
     *评论状态(0正常，1失效)
     */
    private String status;

    /**
     *评论时间
     */
    private String commentTime;

    /**
     *用户微信openid
     */
    private String wopenId;

    /**
     *qq_openid
     */
    private String qopenId;

    /**
     *手机号码
     */
    private String mobPhone;

    /**
     *标识 W:微信 Q:qq P:手机
     */
    private String tag;
}
