package com.commons.service;


import com.commons.entity.HomeMakEntity;
import com.commons.entity.NannyEntity;

import java.util.List;

/**
 * @Description:保姆服务
 * @Author:feihong Vsersion:v.10
 * @Create:2018/05/08 11:11:06
 */
public interface NannyService {

    /**
      * @Description(功能描述): 加入我们
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 11:56
      **/
    int inJoin(HomeMakEntity homeMakEntity);

    /**
      * @Description(功能描述): 查询保姆列表
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 15:55
      **/
    List<NannyEntity> queryNanny(NannyEntity nannyEntity);

    /**
      * @Description(功能描述): 保姆详情
      * @author(作者): feihong
      * @date (开发日期):2018/5/9 11:59
      **/
    NannyEntity nannyDetail(String id);
}
