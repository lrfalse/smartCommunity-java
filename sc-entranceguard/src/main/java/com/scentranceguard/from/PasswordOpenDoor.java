package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordOpenDoor implements Serializable {
    private String m;

    private String f;

    private String a;

    private String app_id;

    private String app_secret;

    /**
     * 开门密钥
     */
    private String access_token;

    /**
     * 设备序列号
     */
    private String device_sncode;
}
