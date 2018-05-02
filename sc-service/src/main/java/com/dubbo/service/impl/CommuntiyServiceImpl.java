package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.CommunityReDto;
import com.commons.entity.CommunityEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.CommunityService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.CommunityMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:小区选择接口
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-24 20:20:08
 */
@Service
public class CommuntiyServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;


    /**
      * @Description(功能描述): 颁发钥匙信息
      * @author(作者): feihong
      * @date (开发日期):2018-4-26 16:24
      **/
    @Override
    public List<String> issuedKey(Integer community_id) {
        if (CommonUtils.isEmpty(community_id)){
            throw new ScException(AppServiceEnums.SYS_DATA_ERROR);
        }
        ParamDto paramDto = new ParamDto("community_id",community_id);
        List<String> list = communityMapper.issuedKey(paramDto);
        return list;
    }

	/**
	  * @Description(功能描述): 保存小区信息
	  * @author(作者): feihong
	  * @date(开发日期): 2018/5/2 9:48
	  **/
	public void batchSave(List<CommunityEntity> list) {
		communityMapper.insertList(list);
	}

	/**
	 * @Description(功能描述): 保存小区信息
	 * @author(作者): feihong
	 * @date(开发日期): 2018/5/2 9:48
	 **/
	public List<String> queryLocation() {
		return communityMapper.queryLocation();
	}
	public List<CommunityEntity> query(CommunityEntity communityEntity){
		return communityMapper.select(communityEntity);
	}

	/**
      * @Description(功能描述): 定位区域小区
      * @author(作者): feihong
      * @date (开发日期):2018-4-24 20:17:23
      **/
    @Override
    public List<CommunityEntity> locationCommunity(CommunityReDto communityReDto) {
        CommunityEntity communityEntity = new CommunityEntity();
        communityEntity.setAdcode(communityReDto.getAdcode());
        communityEntity.setPname(communityReDto.getPname());
        PageHelper.startPage(communityReDto.getPagemum(), 50);
        List<CommunityEntity> entities = communityMapper.select(communityEntity);
        return entities;
    }

    /**
      * @Description(功能描述): 检索小区
      * @author(作者): feihong
      * @date (开发日期):2018-4-24 20:18:24
      **/
    @Override
    public CommunityEntity chooseCommunity(CommunityEntity communityEntity) {
        CommunityEntity entity = communityMapper.selectOne(communityEntity);
        return entity;
    }
}
