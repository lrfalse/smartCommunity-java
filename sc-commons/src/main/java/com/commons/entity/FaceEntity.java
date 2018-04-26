package com.commons.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:人脸审核
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-08 14:14:04
 */
@Data
public class FaceEntity extends BaseEntity implements Serializable{

    /**
     *姓名
     */
    private String user_name;

    /**
     *电话号码
     */
    private String phone_number;

    /**
     *小区
     */
    private String community;

    /**
     *房号
     */
    private String house_number;

    /**
     *门类型,单元门,公共门
     */
    private String door_type;

    /**
     *人脸照片地址
     */
    private String image_url;
}
