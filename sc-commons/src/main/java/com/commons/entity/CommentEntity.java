package com.commons.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @Description:评论
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 19:19:15
 */
@Data
@Table(name = "t_activity_comment")
public class CommentEntity extends BaseIdEntity {

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
     *用户名称
     */
    private String userName;

    /**
     *头像地址
     */
    private String imageUrl;
}
