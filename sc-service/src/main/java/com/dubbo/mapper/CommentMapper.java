package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.CommentEntity;

import java.util.List;

/**
 * @Description:评论
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-27 19:19:26
 */
public interface CommentMapper extends MyMapper<CommentEntity> {

    List<CommentEntity>  queryComment(ParamDto paramDto);//查看评论
}
