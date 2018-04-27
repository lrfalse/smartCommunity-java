package com.commons.dto.anDto;

import com.commons.entity.CommentEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:查看评论dto
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 21:21:28
 */
@Data
public class CommentPageDto implements Serializable {

    /**
     *总评论数
     */
    private long total;

    /**
     *评论信息
     */
    private List<CommentEntity> list;
}
