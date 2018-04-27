package com.commons.dto.anDto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:查看评论dto
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 20:20:46
 */
@Data
public class CommentQueryDto implements Serializable {

    /**
     *当前页
     */
    private Integer pageNum;

    /**
     *每页显示大小
     */
    private Integer pageSize;

    /**
     *活动id
     */
    private String activityId;
}
