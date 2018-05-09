package com.personalCenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.reDto.CommentReDto;
import com.commons.dto.reDto.CommunityReDto;
import com.commons.entity.CommunityEntity;
import com.commons.entity.DistrictEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.CommunityService;
import com.commons.utils.CommonUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:小区选择
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-20 15:15:02
 */
@RestController
public class CommunityController extends BaseApi{


    @Autowired
    private CommunityService communityService;
    /**
     * @Description(功能描述): 小区定位选择
     * @author(作者): feihong
     * @date (开发日期):2018-4-19 18:23:33
     **/
    @PostMapping("/locationcommunity")
    public HttpResults positionCommunity(HttpServletRequest req) throws Exception {
        IsJsonDTO jsonDto = (IsJsonDTO) req.getAttribute("preHandleJsonDto");
        CommunityReDto entity = JSON.parseObject(jsonDto.getBodyJson(), CommunityReDto.class);
        if (CommonUtils.isEmpty(entity.getPname()) && CommonUtils.isEmpty(entity.getAdcode())) {
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        } else {
            List<CommunityEntity> entities = communityService.locationCommunity(entity);
            System.out.println(entities.size());
            return getHttpResult(entities);
        }
    }
    /**
     * @Description(功能描述): 小区检索
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 15:15:23
     **/
    @PostMapping("/choosecommunity")
    public HttpResults chooseCommunity(HttpServletRequest req)throws Exception {
        IsJsonDTO jsonDto = (IsJsonDTO) req.getAttribute("preHandleJsonDto");
        CommunityEntity entity = JSON.parseObject(jsonDto.getBodyJson(), CommunityEntity.class);
        if (CommonUtils.isEmpty(entity.getName()) && CommonUtils.isEmpty(entity.getAdcode()) &&CommonUtils.isEmpty(entity.getPname())) {
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        } else {
            CommunityEntity community = communityService.chooseCommunity(entity);
            return getHttpResult(community);
        }
    }

    /**
      * @Description(功能描述): 分配钥匙
      * @author(作者): feihong
      * @date (开发日期):2018-4-26 16;12
      **/
    public HttpResults  issuedKey(HttpServletRequest req) throws Exception {
        JSONObject jsonObject = JSON.parseObject(getIsJson(req).getBodyJson());
        String communtiy_id = (String)jsonObject.get("communtiy_id");
        return getHttpResultOk();
    }

    /**
      * @Description(功能描述): 查询市
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 19:58
      **/
    @PostMapping("queryCity")
    public HttpResults queryCity(HttpServletRequest req) throws Exception {
        List<DistrictEntity> entities = communityService.queryCity();
        return getHttpResult(entities);
    }

    /**
      * @Description(功能描述): 绑定小区
      * @author(作者): feihong
      * @date (开发日期):2018/5/9 16:06
      **/
    @PostMapping("bindCommunity")
    public HttpResults bindCommunity(HttpServletRequest req) throws Exception{
        CommunityReDto reDto = JSON.parseObject(getIsJson(req).getBodyJson(), CommunityReDto.class);
        int i = communityService.bindCommunity(reDto);
        return getHttpResult(i);

    }
}
