package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:远程开门返回值
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-02 17:17:26
 */
@Data
public class RemoteReturn implements Serializable {

    /**
     *开门状态
     */
    private String state;

    /**
     *开门成功
     */
    private String return_data;

    /**
     *错误信息
     */
    private String ico;
}
