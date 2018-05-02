package com.scentranceguard.from;

import lombok.Data;

import java.io.File;
import java.io.Serializable;

/**
 * @Description:人脸审核
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-08 14:14:04
 */
@Data
public class FaceCheck implements Serializable{

    /**
     *姓名
     */
    private String user_name;

    /**
     *电话号码
     */
    private String mob_phone;

    /**
     *小区
     */
    private String community_id;

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
