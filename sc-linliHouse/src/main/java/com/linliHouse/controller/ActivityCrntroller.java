package com.linliHouse.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.anDto.ActivityListDto;
import com.commons.dto.anDto.CommentDto;
import com.commons.dto.anDto.CommentPageDto;
import com.commons.dto.anDto.CommentQueryDto;
import com.commons.dto.reDto.ActivityJoinDto;
import com.commons.entity.ActivityEntity;
import com.commons.entity.CommentEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.ActivityService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.Page;
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
public class ActivityCrntroller extends BaseApi{


    @Autowired
    private ActivityService activityService;
    /**
      * @Description(功能描述): 首页社区活动及列表
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 15:33
      **/

    @PostMapping("homeActivity")
    public HttpResults homeActivity(HttpServletRequest req) throws Exception {
        String communityID= JSON.parseObject(getIsJson(req).getBodyJson(), ActivityListDto.class).getActivityId();
        List<ActivityEntity> entities = activityService.queryActivity(communityID);
        return getHttpResult(entities);
    }

    /**
      * @Description(功能描述): 活动详情
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 17:31
      **/
    @PostMapping("activityDetails")
    public HttpResults activityList(HttpServletRequest req) throws Exception {
        JSONObject parse = (JSONObject)JSONObject.parse(getIsJson(req).getBodyJson());
        String o = (String)parse.get("activityId");
        ActivityEntity activityEntities = activityService.ActivityList(o);
        return getHttpResult(activityEntities);
    }

    /**
      * @Description(功能描述): 评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 19:13
      **/
    public HttpResults comment(HttpServletRequest req) throws Exception {
        CommentDto entity = JSON.parseObject(getIsJson(req).getBodyJson(), CommentDto.class);
        int i = activityService.addComment(entity);
        return getHttpResult(i);
    }

    /**
      * @Description(功能描述): 查看评论接口
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 20:36
      **/
    public HttpResults queryComment(HttpServletRequest req) throws Exception {
        CommentQueryDto commentQueryDto = JSON.parseObject(getIsJson(req).getBodyJson(), CommentQueryDto.class);
        if (CommonUtils.isEmpty(commentQueryDto.getPageNum())&&CommonUtils.isEmpty(commentQueryDto.getPageSize())&&CommonUtils.isEmpty(commentQueryDto.getActivityId())){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        Page<CommentEntity> commentEntities = activityService.queryComment(commentQueryDto);
        CommentPageDto commentPageDto =new CommentPageDto();
        commentPageDto.setList(commentEntities.getResult());
        commentPageDto.setTotal(commentEntities.getTotal());
        return getHttpResult(commentPageDto);
    }

    /**
      * @Description(功能描述): 参加活动人
      * @author(作者): feihong
      * @date (开发日期):2018-4-27 22:34
      **/
    public HttpResults joinActivity(HttpServletRequest req) throws Exception {
        ActivityJoinDto join = JSON.parseObject(getIsJson(req).getBodyJson(), ActivityJoinDto.class);
        return getHttpResultOk();
    }
}
