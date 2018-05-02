package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:活动人数展示及头像
 * @Author:feihong
 * @Version.10
 * @Create:2018-04-29 22:22:30
 */
@Data
public class ActivityPeopleDto implements Serializable {

    /**
     * 头像地址
     */
    private String imgUrl;

    /**
     * 参加活动人数
     */
    private String peopleNum;

    /**
     * 用户名称
     */
    private String name;
}
