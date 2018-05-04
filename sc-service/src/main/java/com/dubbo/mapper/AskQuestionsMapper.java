package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.QuestionsCommentEntity;

import java.util.List;

/**
 * @Description(功能描述) : 问答
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:46
 */
public interface AskQuestionsMapper extends MyMapper<AskQuestionsEntity> {
    /**
     * @Description(功能描述) : 查询问题和评论数
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 10:50
     */
    List<AskQuestionsDto> queryAsk(ParamDto paramDto);

    /**
     * @Description(功能描述) : 问题详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:57
     */
    List<QuestionsCommentEntity> queryComment(ParamDto paramDto);
}
