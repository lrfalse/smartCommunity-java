package com.commons.entity;

import lombok.Data;

/**
 * @Description:意见回馈
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/03 11:11:41
 */
@Data
public class FeedBackEntity extends BaseEntity {

    /**
     *意见反馈内容
     */
    private String context;

    /**
     *用户id
     */
    private String user_id;

    /**
     *用户意见反馈提交图片
     */
    private String img_url;

    /**
     *反馈时间
     */
    private String feedback_time;

    /**
     *'状态：0正常 1失效'
     */
    private String status;
}

