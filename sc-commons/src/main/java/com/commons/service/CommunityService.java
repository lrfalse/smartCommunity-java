package com.commons.service;

import com.commons.dto.reDto.CommunityReDto;
import com.commons.entity.CommunityEntity;
import com.commons.entity.DistrictEntity;
import org.apache.ibatis.annotations.Param;

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
	List<CommunityEntity> chooseCommunity(CommunityEntity communityEntity);

    /**
      * @Description(功能描述): 颁发钥匙信息
      * @author(作者): feihong
      * @date (开发日期):2018-4-26 16:21
      **/
    List<String>  issuedKey(Integer community_id);

    /**
      * @Description(功能描述): 保存小区信息
      * @author(作者): feihong
      * @date(开发日期): 2018/5/2 9:48
      **/
	void batchSave(@Param("list") List<CommunityEntity> list);

	/**
	  * @Description(功能描述): 查询小区信息
	  * @author(作者): feihong
	  * @date(开发日期): 2018/5/2 9:50
	  **/
	List<String> queryLocation();
	List<CommunityEntity> query(CommunityEntity communityEntity);

	/**
	  * @Description(功能描述): 查询市
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/8 20:01
	  **/
	List<DistrictEntity> queryCity();

	/**
	  * @Description(功能描述): 小区绑定
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/9 16:14
	  **/
	int bindCommunity(CommunityReDto communityReDto);
}
