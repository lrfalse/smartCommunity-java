package com.scentranceguard.from;

import lombok.Data;

import java.io.Serializable;

@Data
public class Code implements Serializable {
    /**
     * 二维码字符串
     */
    private String owner_qrc;
    /**
     * 失效时间
     */
    private String qrc_invalid_time;
}
