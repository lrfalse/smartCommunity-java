package com.commons.dto.anDto;

import com.commons.dto.reDto.BasePageReDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:首页活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 16:16:18
 */
@Data
public class ActivityListDto extends BasePageReDto{

    /**
     * 小区
     */
    private String communityId;



}
