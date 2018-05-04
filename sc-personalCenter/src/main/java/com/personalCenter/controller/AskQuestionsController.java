package com.personalCenter.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.service.AskQuestionsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) : 问答controller
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 15:06
 */
@RestController
@RequestMapping("/")
public class AskQuestionsController extends BaseApi {

    @Autowired
    private AskQuestionsService askQuestionsService;

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 15:26
     */
    @PostMapping("/goWithAsk")
    public HttpResults goWithAsk(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        AskQuestionsEntity entity = askQuestionsService.goWithAsk(askQuestionsEntity);
        if(entity == null){
            return getHttpResult(0);
        }
        return getHttpResult(entity);
    }

    /**
     * @Description(功能描述) : 我的提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 16:10
     */
    @PostMapping("/mineAsk")
    public HttpResults mineAsk(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsEntity.getCommunityId());
        paramDto.put("userId",askQuestionsEntity.getUserId());
        AskQuestionsDto dto = askQuestionsService.mineAsk(paramDto);
        return getHttpResult(dto);
    }

    /**
     * @Description(功能描述) : 提问分类
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 19:38
     */
    @PostMapping("/questionClassification")
    public HttpResults questionClassification(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsEntity.getCommunityId());
        paramDto.put("type",askQuestionsEntity.getType());
        PageInfo<AskQuestionsDto> pageInfo = askQuestionsService.questionClassification(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 热门问题/最新问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 19:38
     */
    @PostMapping("/topQuestions")
    public HttpResults topQuestions(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsDto askQuestionsDto = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsDto.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsDto.getCommunityId());
        //popular 为true时是热门问题，为false时是最新问题
        paramDto.put("popular",askQuestionsDto.getPopular());
        PageInfo<AskQuestionsDto> pageInfo = askQuestionsService.topQuestions(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 问题详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:24
     */
    @PostMapping("/problemDetails")
    public HttpResults problemDetails(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        AskQuestionsEntity entity = askQuestionsService.problemDetails(askQuestionsEntity);
        return getHttpResult(entity);
    }

    /**
     * @Description(功能描述) : 问题详情评论
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:38
     */
    @PostMapping("/commentDetails")
    public HttpResults commentDetails(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        QuestionsCommentEntity questionsCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), QuestionsCommentEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("questionsId",questionsCommentEntity.getQuestionsId());
        PageInfo<QuestionsCommentEntity> pageInfo = askQuestionsService.commentDetails(paramDto);
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 我来回答
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 9:25
     */
    @PostMapping("/reply")
    public HttpResults reply(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        QuestionsCommentEntity questionsCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), QuestionsCommentEntity.class);
        int n = askQuestionsService.reply(questionsCommentEntity);
        return getHttpResult(n);
    }

}
