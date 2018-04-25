package com.commons.service;

import com.commons.dto.reDto.CommunityReDto;
import com.commons.entity.CommunityEntity;

import java.util.List;

/**
 * @Description:小区接口
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-24 20:20:07
 */
public interface CommunityService {

    /**
      * @Description(功能描述):定位区域小区
      * @author(作者): feihong
      * @date (开发日期):2018-4-24 20:11
      **/
    List<CommunityEntity> locationCommunity(CommunityReDto communityReDto);

    /**
      * @Description(功能描述): 检索小区
      * @author(作者): feihong
      * @date (开发日期):2018-4-24 20:15:22
      **/
    CommunityEntity chooseCommunity(CommunityEntity communityEntity);
}
