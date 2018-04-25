package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:绑定手机号Dto
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-20 20:20:01
 */
@Data
public class BindPhoneDto implements Serializable{

    /**
     *绑定手机号码成功 1:绑定成功 0:绑定失败
     */
    private String token;

    /**
     *是否需要选择小区 1:需要选择小区 0:已经选择
     */
    private String ftag;
}
