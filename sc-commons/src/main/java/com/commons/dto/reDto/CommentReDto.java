package com.commons.dto.reDto;

import lombok.Data;

/**
 * @Description:查看评论dto
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 20:20:46
 */
@Data
public class CommentReDto extends BasePageReDto {
    /**
     *活动id
     */
    private String activityId;

    /**
     *状态 0正常 1失效
     */
    private String status;
}
