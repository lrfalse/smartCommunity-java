package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;
/**
 * @Description:密钥获取javabean
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-03 10:10:04
 */
@Data
public class SecertReturnData extends AccessToken implements Serializable{

    /**
     *状态 1:成功, 0:失败
     */
    private String state;

    /**
     *信息提示 error:显示错误,success:显示正确
     */
    private String ico;

    /**
     *
     */
    private String data_ver;
}
