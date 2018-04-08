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
    private String username;

    /**
     *电话号码
     */
    private String phonenumber;

    /**
     *小区
     */
    private String community;

    /**
     *房号
     */
    private String housenumber;

    /**
     *门类型,单元门,公共门
     */
    private String doortype;

    /**
     *人脸照片地址
     */
    private String imageurl;
}
