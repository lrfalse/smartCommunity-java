package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:活动详情返回参数
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/17 15:15:33
 */
@Data
public class ActivityDetailDto implements Serializable {

    /**
     *活动id
     */
    private String id;
    /**
     *活动内容
     */
    private String content;

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
     *发布方
     */
    private String issuer;

    /**
     *发布时间
     */
    private String publishTime;

}
