package com.linli.controller;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.AskQuestionsDto;
import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.AskQuestionsEntity;
import com.commons.entity.ChatTypeEntity;
import com.commons.entity.QuestionsCommentEntity;
import com.commons.entity.QuestionsImgEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.AskQuestionsService;
import com.commons.service.RedisService;
import com.commons.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    private static final Logger logger= LoggerFactory.getLogger(AskQuestionsController.class);

    @Autowired
    private AskQuestionsService askQuestionsService;
    @Autowired
    private Environment env;
	@Autowired
	private UserService userService;
    @Autowired
    private RedisService redisService;

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
		LoginDTO loginDTO=userService.getRedisUser(askQuestionsEntity.getToken());
		paramDto.put("communityId",askQuestionsEntity.getCommunityId());
		paramDto.put("userId",loginDTO.getUserId());
		paramDto.put("status",askQuestionsEntity.getStatus());
		AskQuestionsDto dto = askQuestionsService.mineAsk(paramDto);
		if(dto == null){
            throw new ScException(AppServiceEnums.DOES_NOT_EXIST);
        }
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
        paramDto.put("status",askQuestionsEntity.getStatus());
        paramDto.put("type",askQuestionsEntity.getType());
        PageInfo<AskQuestionsDto> pageInfo = askQuestionsService.getAskQuestionsPageInfo(paramDto,askQuestionsEntity.getPage(),askQuestionsEntity.getRows());
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 根据标题title搜索问题
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/8 10:18
     */
    @PostMapping("/problemSearch")
    public HttpResults problemSearch(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsEntity.getCommunityId());
        paramDto.put("status",askQuestionsEntity.getStatus());
        paramDto.put("title",askQuestionsEntity.getTitle());
        PageInfo<AskQuestionsDto> pageInfo = askQuestionsService.getAskQuestionsPageInfo(paramDto,askQuestionsEntity.getPage(),askQuestionsEntity.getRows());
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
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsEntity.getCommunityId());
        paramDto.put("status",askQuestionsEntity.getStatus());
        //popular 为true时是热门问题，为false时是最新问题
        paramDto.put("popular",askQuestionsEntity.getPopular());
        PageInfo<AskQuestionsDto> pageInfo = askQuestionsService.getAskQuestionsPageInfo(paramDto,askQuestionsEntity.getPage(),askQuestionsEntity.getRows());
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
        AskQuestionsDto askQuestionsDto = JSON.parseObject(jsonDto.getBodyJson(), AskQuestionsDto.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",askQuestionsDto.getCommunityId());
        paramDto.put("status",askQuestionsDto.getStatus());
        paramDto.put("id",askQuestionsDto.getId());
        AskQuestionsDto dto = askQuestionsService.problemDetails(paramDto);
        if(dto!=null){
            String ip = req.getRemoteAddr()+"problemDetails"+dto.getId();
            String temp = redisService.get(ip);
            //判断用户是否在15分钟内重复查看该条信息，重复则不增加浏览量
            if(temp == null || !ip.equals(temp.replace("\"",""))){
                redisService.set(ip,ip,15);
                AskQuestionsEntity askQuestionsEntity = new AskQuestionsEntity();
                askQuestionsEntity.setId(dto.getId());
                askQuestionsEntity.setBrowseNum(dto.getBrowseNum());
                askQuestionsService.browsingIncrease(askQuestionsEntity);
            }
        }
        return getHttpResult(dto);
    }

    /**
     * @Description(功能描述) : 问题详情评论列表
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/3 11:38
     */
    @PostMapping("/commentList")
    public HttpResults commentList(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        QuestionsCommentEntity questionsCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), QuestionsCommentEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("questionsId",questionsCommentEntity.getQuestionsId());
        paramDto.put("status",questionsCommentEntity.getStatus());
        PageInfo<QuestionsCommentEntity> pageInfo = askQuestionsService.commentList(paramDto,questionsCommentEntity.getPage(),questionsCommentEntity.getRows());
        return getHttpResult(pageInfo);
    }

    /**
     * @Description(功能描述) : 评论详情
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/7 14:45
     */
    @PostMapping("/commentDetails")
    public HttpResults commentDetails(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        QuestionsCommentEntity questionsCommentEntity = JSON.parseObject(jsonDto.getBodyJson(), QuestionsCommentEntity.class);
        QuestionsCommentEntity entity= askQuestionsService.commentDetails(questionsCommentEntity);
        return getHttpResult(entity);
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
     * @Description(功能描述) : 查询邻里聊天室类型list
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/5 16:41
     */
    @PostMapping("/getChatTypeList")
    public HttpResults getChatTypeList(HttpServletRequest req)throws Exception{
        IsJsonDTO jsonDto=getIsJson(req);
        ChatTypeEntity chatTypeEntity = JSON.parseObject(jsonDto.getBodyJson(), ChatTypeEntity.class);
        ParamDto paramDto = new ParamDto();
        paramDto.put("communityId",chatTypeEntity.getCommunityId());
        paramDto.put("status",0);
        List<ChatTypeEntity> list= askQuestionsService.getChatTypeList(paramDto);
        if(list==null){
            return getHttpResult(0);
        }
        return getHttpResult(list);
    }

    /**
     * @Description(功能描述) : 去提问
     * @Author(作者) : xly<xielinyang>
     * @Date(开发日期) : 2018/5/4 14:49
     */
    @PostMapping("/goWithAsk")
    public HttpResults goWithAsk(HttpServletRequest req)throws Exception{
        AskQuestionsEntity askQuestionsEntity = JSON.parseObject(getIsJson(req).getBodyJson(), AskQuestionsEntity.class);
        LoginDTO loginDTO=userService.getRedisUser(askQuestionsEntity.getToken());
        askQuestionsEntity.setUserId(loginDTO.getUserId());
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
                        fileName = filePath +"/"+ UUID.randomUUID().toString().replace("-","")+suffix;
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
