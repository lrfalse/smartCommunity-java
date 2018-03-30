package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class Secert implements Serializable{

    private String m;

    private String f;

    private String a;

    private String app_id;

    private String app_secret;

    /**
     * 密钥有效天数
     */
    private String effective_days;
}
