package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:首页活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 16:16:18
 */
@Data
public class ActivityListDto implements Serializable{

    /**
     * 小区
     */
    private String communtyID;

    /**
     *当前页
     */
    private String pageNum;

    /**
     * 每页显示多少
     */
    private String  pageSize;

}
