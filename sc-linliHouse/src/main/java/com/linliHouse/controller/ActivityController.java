package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.anDto.*;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.ActivityJoinDto;
import com.commons.dto.reDto.CommentReDto;
import com.commons.entity.CommentEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.ActivityService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:社区活动
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-27 15:15:20
 */
@RestController
public class ActivityController extends BaseApi{


    @Autowired
    private ActivityService activityService;

    /**
      * @Description(功能描述): 首页社区活动列表
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 15:33
      **/
    @PostMapping("/activity")
    public HttpResults homeActivity(HttpServletRequest req) throws Exception {
        ActivityListDto listDto = JSON.parseObject(getIsJson(req).getBodyJson(), ActivityListDto.class);
        List<ActivityDto> activityDtos = activityService.queryActivityDetail(listDto);
        PageInfo<ActivityDto> pageInfo = new PageInfo<>(activityDtos);
        return getHttpResult(pageInfo);

    }

    /**
      * @Description(功能描述): 评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 19:13
      **/
    @PostMapping("comment")
    public HttpResults comment(HttpServletRequest req) throws Exception {
        CommentDto entity = JSON.parseObject(getIsJson(req).getBodyJson(), CommentDto.class);
        int i = activityService.Comment(entity);
        return getHttpResult(i);
    }

    /**
      * @Description(功能描述): 查看评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 20:36
      **/
    @PostMapping("queryComment")
    public HttpResults queryComment(HttpServletRequest req) throws Exception {
        CommentReDto commentReDto = JSON.parseObject(getIsJson(req).getBodyJson(), CommentReDto.class);
        if (CommonUtils.isEmpty(commentReDto.getPages())&&CommonUtils.isEmpty(commentReDto.getPageSize())&&CommonUtils.isEmpty(commentReDto.getActivityId())){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        PageInfo<CommentEntity> pageInfo = activityService.queryComment(commentReDto);
        return getHttpResult(new BasePageDto<>(pageInfo));
    }

    /**
      * @Description(功能描述): 参加活动
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 22:34
      **/
    @PostMapping("joinActivity")
    public HttpResults joinActivity(HttpServletRequest req) throws Exception {
        ActivityJoinDto join = JSON.parseObject(getIsJson(req).getBodyJson(), ActivityJoinDto.class);
        int i = activityService.joinActivityxx(join);
        return getHttpResult(i);
    }
    
    /** 
      * @Description(功能描述): 我参与的活动
      * @author(作者): feihong
      * @date (开发日期):2018/5/2 10:14
      **/
    @PostMapping("join")
   public HttpResults Join(HttpServletRequest req) throws Exception {
        CommentDto commentDto= JSONObject.parseObject(getIsJson(req).getBodyJson(), CommentDto.class);
        PageInfo<JoinActityDto> dtoPageInfo = activityService.injoin(commentDto);
       return getHttpResult(dtoPageInfo);
   }

   /**
     * @Description(功能描述): 查看活动参与所有人员
     * @author(作者): feihong
     * @date (开发日期):2018/5/2 19:56
     **/
   @PostMapping("getPreson")
    public HttpResults getPreson(HttpServletRequest req) throws Exception {
       CommentReDto parse = JSONObject.parseObject(getIsJson(req).getBodyJson(), CommentReDto.class);
       PageInfo<ActivityImageNameDto> preson = activityService.getPreson(parse);
       return getHttpResult(preson);
   }
}
