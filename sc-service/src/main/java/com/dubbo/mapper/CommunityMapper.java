package com.dubbo.mapper;

import com.commons.config.MyMapper;
import com.commons.entity.CommunityEntity;

import java.util.List;

/**
 * @Description:小区信息mapper
 * @Author:feihong Vsersion:v.10
 * @Create:2018-04-24 20:20:27
 */
public interface CommunityMapper extends MyMapper<CommunityEntity> {

    /**
     * @Description(功能描述): 颁发钥匙
     * @author(作者): feihong
     * @date (开发日期):2018-4-26 16:26
     **/
    List<String> issuedKey(int commmunity);
}
