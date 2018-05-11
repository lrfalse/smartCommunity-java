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

    private String name;		//用户名
    private String sex;			//姓别
    private String img_url;		//头像地址
    private String mobphone;	//手机号码
    private Long communtiyId;   //小区信息
	private Long userId;		//用户id
    private String token;		//登录用户唯一标识
}
