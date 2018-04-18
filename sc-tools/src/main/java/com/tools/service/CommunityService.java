package com.tools.service;

import com.commons.entity.CommunityEntity;
import com.tools.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:小区信息导入
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-18 10:10:57
 */
@Service
public class CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    /**
      * @Description(功能描述): 批量插入小区信息
      * @author(作者): feihong
      * @date (开发日期):2018-04-18 10:10:57
      **/
    public int addbatch(List<CommunityEntity> communityEntityList){
        return communityMapper.insertList(communityEntityList);
    }

}
