package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:账号不存在
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-21 21:21:41
 */
@Data
public class LoginErrorDto implements Serializable{

    /**
     *登录状态
     */
    private String ftag;
}
