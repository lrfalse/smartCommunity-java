package com.commons.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:小区业务数据
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-20 14:14:45
 */
@Data
public class BindPhoneVo implements Serializable{

    /**
     *手机号是否绑定 1:绑定 0:绑定
     */
    private String bindphone;
}
