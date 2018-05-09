package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:登录信息返回
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-19 09:9:36
 */
@Data
public class LoginDTO implements Serializable{

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
    private String img_url;

    /**
     *手机号码
     */
    private String mobphone;

    /**
     *小区信息
     */
    private Integer communtiyId;
}
