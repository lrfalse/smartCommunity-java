package com.personalCenter.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.entity.QuestionsImgEntity;
import com.commons.service.AskQuestionsService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description(功能描述) : 问答controller
 * @Author(作者) : xly<xielinyang>
 * @Date(开发日期) : 2018/5/2 15:06
 */
@RestController
@RequestMapping("/")
public class AskQuestionsController extends BaseApi {

    private static final Logger logger= LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private AskQuestionsService askQuestionsService;
    @Autowired
    private Environment env;

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/2 15:26
     */
    /*@PostMapping("/goWithAsk")
    public HttpResults goWithAsk(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        AskQuestionsEntity entity = askQuestionsService.goWithAsk(askQuestionsEntity);
        if(entity == null){
            return getHttpResult(0);
        }
        return getHttpResult(entity);
    }*/

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

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 14:49
     */
    @PostMapping("/goWithAsk")
    public HttpResults goWithAsk(HttpServletRequest req)throws Exception{
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(getIsJson(req).getBodyJson(), AskQuestionsEntity.class);
        askQuestionsEntity.setStatus(0);
        askQuestionsEntity.setBrowseNum(0);
        askQuestionsEntity.setPublishTime(new Date());
        AskQuestionsEntity entity = askQuestionsService.goWithAsk(askQuestionsEntity);
        if(entity != null){
            List<MultipartFile> files = ((MultipartHttpServletRequest) req).getFiles("file");
            MultipartFile file;
            BufferedOutputStream stream;
            String filePath=env.getProperty("sc.problem.img.url");
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    String fileName;
                    QuestionsImgEntity questionsImgEntity;
                    try {
                        File dir = new File(filePath);
                        if(!dir.exists()){
                            dir.mkdirs();
                        }
                        byte[] bytes = file.getBytes();
                        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                        fileName = filePath +"/"+ UUID.randomUUID()+suffix;
                        stream = new BufferedOutputStream(new FileOutputStream(fileName));
                        stream.write(bytes);
                        stream.close();
                        questionsImgEntity = new QuestionsImgEntity();
                        questionsImgEntity.setImgUrl(fileName);
                        questionsImgEntity.setType(0);
                        questionsImgEntity.setQuestionsId(entity.getId());
                        askQuestionsService.saveQuestionsImg(questionsImgEntity);
                    } catch (Exception e) {
                        logger.info("上传文件失败"+i+":"+e.getMessage());
                    }
                }
            }
        }else{
            return getHttpResult(0);
        }
        return getHttpResult(1);
    }

}
