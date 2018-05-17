package com.commons.dto.anDto;

import com.commons.dto.reDto.BasePageReDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:评论
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 19:19:43
 */
@Data
public class CommentDto extends BasePageReDto{

    /**
     *活动
     */
    private String activityId;

    /**
     *评论内容
     */
    private String content;

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
     *登录token
     */
    private String token;

    /**
     *标识 W:微信 Q:qq P:手机
     */
    private String tag;
}
