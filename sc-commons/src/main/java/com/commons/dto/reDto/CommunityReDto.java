package com.commons.dto.reDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:小区Dto requestDto-->>reDto
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-24 21:21:10
 */
@Data
public class CommunityReDto implements Serializable{

    /**
     *开始页
     */
    private int pagemum;

    /**
     *省市名称
     */
    private String pname;

    /**
     *区域编码
     */
    private Integer adcode;
}
