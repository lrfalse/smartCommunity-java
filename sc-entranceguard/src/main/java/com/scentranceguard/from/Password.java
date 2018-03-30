package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class Password implements Serializable {

    /**
     * 开门密码
     */
    private String pwd_word;

    /**
     * 密码有效截至时间
     */
    private String expire_time;
}
