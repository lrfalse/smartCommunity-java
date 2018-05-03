package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:我参加的活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/02 20:20:38
 */
@Data
public class JoinActityDto implements Serializable{

    /**
     *活动名称
     */
    private String name;

    /**
     *图片地址
     */
    private String imgUrl;
}
