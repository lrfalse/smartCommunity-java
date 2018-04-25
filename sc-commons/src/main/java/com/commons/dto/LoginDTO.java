package com.commons.dto;

import lombok.Data;

/**
 * @Description:登录信息返回
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-19 09:9:36
 */
@Data
public class LoginDTO {

    /**
     *用户名
     */
    private String name;

    /**
     *姓别
     */
    private String sex;

    /**
     *头像地址
     */
    private String image_url;

    /**
     *是否首次登录 1:非首次登录 0:首次登录
     */
    private String token;

    /**
     *手机号码
     */
    private String mobphone;

    /**
     *登录失败
     */
    private String status;
}
