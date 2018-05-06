package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:活动dto
 * @Author:feihong
 * @Version.10
 * @Create:2018-04-28 21:21:08
 */
@Data
public class ActivityDto implements Serializable{

    /**
     *活动id
     */
    private String activityId;
    /**
     * 活动图片地址
     */
    private String imgUrl;

    /**
     * 活动名称
     */
    private String title;

    /**
     *人数
     */
    private Integer peopleNum;

    /**
     * 活动人数及头像
     */
    private List<ActivityPeopleDto> list;


}
