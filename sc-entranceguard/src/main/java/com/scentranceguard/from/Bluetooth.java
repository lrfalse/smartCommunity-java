package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-04 15:15:31
 */
@Data
public class Bluetooth implements Serializable{

    /**
     *开门密钥
     */
    private String sdkKey;

    /**
     *蓝牙mac
     */
    private String bluetooth_mac;
}
