package com.commons.entity;

import lombok.Data;
import lombok.Value;

import javax.persistence.Table;


/**
 * @Description:社区活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 16:16:23
 */
@Data
@Table(name="t_activity")
public class ActivityEntity extends BaseIdEntity{

    /**
     *小区id
     */
    private String communityId;

    /**
     *标题
     */
    private String title;

    /**
     *图片地址
     */
    private String imgUrl;

    /**
     *活动内容
     */
    private String content;

    /**
     *发布方
     */
    private String issuer;

    /**
     *发布时间
     */
    private String publishTime;

    /**
     *有效开始时间
     */
    private String startTime;

    /**
     *有效结束时间
     */
    private String end_time;

    /**
     *活动地址
     */
    private String address;

    /**
     *活动人数
     */
    private Integer peopleNum;

    /**
     *活动费用
     */
    private String cost;

    /**
     *活动截止时间
     */
    private String abortTime;

    /**
     * 联系人
     */
    private String contacts;

    /**
     *联系电话
     */
    private String phone;

    /**
     *状态（0：正常 1：失效）
     */
    private String status;
}
