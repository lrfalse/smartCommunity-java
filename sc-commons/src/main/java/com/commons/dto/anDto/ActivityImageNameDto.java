package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:展示用户头像及名称
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/02 11:11:11
 */
@Data
public class ActivityImageNameDto implements Serializable {
    /**
     *名称
     */
    private String userName;

    /**
     *头像
     */
    private String imageUrl;
}
