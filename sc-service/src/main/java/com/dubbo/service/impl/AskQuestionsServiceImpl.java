package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.ChatTypeEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.entity.QuestionsImgEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.AskQuestionsService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.AskQuestionsMapper;
import com.dubbo.mapper.ChatTypeMapper;
import com.dubbo.mapper.QuestionsCommentMapper;
import com.dubbo.mapper.QuestionsImgMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Description(功能描述) : 问答
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 14:50
 */
@Service
public class AskQuestionsServiceImpl implements AskQuestionsService{

    @Autowired
    private AskQuestionsMapper askQuestionsMapper;
    @Autowired
    private QuestionsCommentMapper questionsCommentMapper;
    @Autowired
    private QuestionsImgMapper questionsImgMapper;
    @Autowired
    private ChatTypeMapper chatTypeMapper;

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 15:30
     */
    @Override
    public AskQuestionsEntity goWithAsk(AskQuestionsEntity aqe) {
        if(aqe != null){
            aqe.setStatus(0);
            aqe.setBrowseNum(0);
            aqe.setPublishTime(new Date());
            if(CommonUtils.isNotEmpty(aqe.getUserId(),aqe.getCommunityId(),aqe.getTitle(),aqe.getContent(),aqe.getType())){
                int n = askQuestionsMapper.insert(aqe);
                if(n > 0){
                    return aqe;
                }
            }
        }
        return null;
    }

    /**
     * @Description(功能描述) : 我的提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 16:12
     */
    @Override
    public AskQuestionsDto mineAsk(ParamDto paramDto) {
        List<AskQuestionsDto> list = askQuestionsMapper.queryAsk(paramDto);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * @Description(功能描述) : 提问分类、根据标题title搜索问题、热门问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 19:52
     */
    @Override
    public PageInfo<AskQuestionsDto> getAskQuestionsPageInfo(ParamDto paramDto,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<AskQuestionsDto> list = askQuestionsMapper.queryAsk(paramDto);
        PageInfo<AskQuestionsDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @Description(功能描述) : 问题详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:25
     */
    @Override
    public AskQuestionsDto problemDetails(ParamDto paramDto) {
        List<AskQuestionsDto> list = askQuestionsMapper.queryAsk(paramDto);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * @Description(功能描述) : 浏览量增加
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/8 17:46
     */
    @Override
    public void browsingIncrease(AskQuestionsEntity askQuestionsEntity) {
        if(CommonUtils.isEmpty(askQuestionsEntity.getBrowseNum())){
            askQuestionsEntity.setBrowseNum(1);
        }else{
            askQuestionsEntity.setBrowseNum(askQuestionsEntity.getBrowseNum()+1);
        }
        askQuestionsMapper.updateByPrimaryKeySelective(askQuestionsEntity);
    }

    /**
     * @Description(功能描述) : 问题详情评论列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:49
     */
    @Override
    public PageInfo<QuestionsCommentEntity> commentList(ParamDto paramDto,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<QuestionsCommentEntity> list = askQuestionsMapper.queryComment(paramDto);
        PageInfo<QuestionsCommentEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @Description(功能描述) : 评论详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/7 14:48
     */
    @Override
    public QuestionsCommentEntity commentDetails(QuestionsCommentEntity questionsCommentEntity) {
        return questionsCommentMapper.selectOne(questionsCommentEntity);
    }

    /**
     * @Description(功能描述) : 我来回答
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 9:28
     */
    @Override
    public int reply(QuestionsCommentEntity qce) {
        if(qce !=null && CommonUtils.isNotEmpty(qce.getUserId()) && CommonUtils.isNotEmpty(qce.getQuestionsId()) && CommonUtils.isNotEmpty(qce.getContent())){
            qce.setCommentTime(new Date());
            qce.setStatus(0);
            int n = questionsCommentMapper.insert(qce);
            if (n < 0) {
                throw new ScException(AppServiceEnums.SYS_EXCEPTION);
            }
            return 1;
        }
        return 0;
    }

    /**
     * @Description(功能描述) : 保存问题图片
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 17:41
     */
    @Override
    public int saveQuestionsImg(QuestionsImgEntity questionsImgEntity) {
        int n = questionsImgMapper.insert(questionsImgEntity);
        if(n > 0){
            return 1;
        }
        return 0;
    }

    /**
     * @Description(功能描述) : 查询邻里聊天室类型list
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/5 16:41
     */
    public List<ChatTypeEntity> getChatTypeList(ChatTypeEntity chatTypeEntity){
        ChatTypeEntity ct;
        if(chatTypeEntity == null){
            ct = new ChatTypeEntity();
            ct.setStatus(0);
        }else{
            ct = chatTypeEntity;
        }
        return chatTypeMapper.select(ct);
    }
}
