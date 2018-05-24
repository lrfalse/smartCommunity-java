package com.commons.dto.anDto;

import com.commons.dto.reDto.BasePageReDto;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.SortedSet;

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
    @Valid()
    private String communityId;

    /**
     *标识  0:首页查询5条,1 查询所有活动
     */
    private String tag;

}
