package com.commons.service;

import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.ChatTypeEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.entity.QuestionsImgEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     * @Description(功能描述) : 根据标题title搜索问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/8 10:05
     */
    PageInfo<AskQuestionsDto> problemSearch(ParamDto paramDto);

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
    AskQuestionsDto problemDetails(ParamDto paramDto);

    /**
     * @Description(功能描述) : 浏览量增加
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/8 17:44
     */
    void browsingIncrease(AskQuestionsEntity askQuestionsEntity);

    /**
     * @Description(功能描述) : 问题详情评论列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:46
     */
    PageInfo<QuestionsCommentEntity> commentList(ParamDto paramDto);

    /**
     * @Description(功能描述) : 评论详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/7 14:47
     */
    QuestionsCommentEntity commentDetails(QuestionsCommentEntity questionsCommentEntity);

    /**
     * @Description(功能描述) : 我来回答
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 9:27
     */
    int reply(QuestionsCommentEntity questionsCommentEntity);

    /**
     * @Description(功能描述) : 保存问答图片
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 17:40
     */
    int saveQuestionsImg(QuestionsImgEntity questionsImgEntity);

    /**
     * @Description(功能描述) : 查询邻里聊天室类型list
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/5 16:41
     */
    List<ChatTypeEntity> getChatTypeList(ChatTypeEntity chatTypeEntity);
}
