package com.commons.service;

import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.entity.QuestionsImgEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Description(功能描述) : 问答
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:49
 */
public interface AskQuestionsService {

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 15:29
     */
    AskQuestionsEntity goWithAsk(AskQuestionsEntity askQuestionsEntity);

    /**
     * @Description(功能描述) : 我的问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 18:06
     */
    AskQuestionsDto mineAsk(ParamDto paramDto);

    /**
     * @Description(功能描述) : 提问分类
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 19:42
     */
    PageInfo<AskQuestionsDto> questionClassification(ParamDto paramDto);

    /**
     * @Description(功能描述) : 热门问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 19:42
     */
    PageInfo<AskQuestionsDto> topQuestions(ParamDto paramDto);

    /**
     * @Description(功能描述) : 问题详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:25
     */
    AskQuestionsEntity problemDetails(AskQuestionsEntity askQuestionsEntity);

    /**
     * @Description(功能描述) : 问题详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:46
     */
    PageInfo<QuestionsCommentEntity> commentDetails(ParamDto paramDto);

    /**
     * @Description(功能描述) : 我来回答
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 9:27
     */
    int reply(QuestionsCommentEntity questionsCommentEntity);

    /**
     * @Description(功能描述) : 问答图片
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 17:40
     */
    int saveQuestionsImg(QuestionsImgEntity questionsImgEntity);
}
