package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.anDto.LoginDTO;
import com.commons.dto.dbDto.ParamDto;
import com.commons.dto.reDto.CommunityReDto;
import com.commons.entity.CommunityEntity;
import com.commons.entity.DistrictEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.CommunityService;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.CommunityMapper;
import com.dubbo.mapper.DistrictMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.AAShapePipe;

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
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private UserService userService;
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

    /**
      * @Description(功能描述): 绑定小区
      * @author(作者): feihong
      * @date (开发日期):2018/5/9 16:18
      **/
    public int bindCommunity(CommunityReDto communityReDto) {
        LoginDTO redisUser = userService.getRedisUser(communityReDto.getToken());
        if (CommonUtils.isEmpty(redisUser)){
            throw new ScException(AppServiceEnums.NULL_USER_DATA);
        }
        ParamDto paramDto = new ParamDto();
        paramDto.put("communtyId",communityReDto.getCommunityId());
        paramDto.put("id",redisUser.getUserId());
        int i = communityMapper.bindCommuntity(paramDto);
        return i;
    }

    /**
	  * @Description(功能描述): 查询市
	  * @author(作者): feihong
	  * @date (开发日期):2018/5/8 20:03
	  **/
    @Override
    public List<DistrictEntity> queryCity() {
        ParamDto paramDto = new ParamDto();
        paramDto.put("city","city");
        List<DistrictEntity> entities = districtMapper.queryCity(paramDto);
        return entities;
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
    public  List<CommunityEntity> chooseCommunit(CommunityEntity communityEntity) {
        ParamDto paramDto = new ParamDto();
        paramDto.put("name",communityEntity.getName());
        PageHelper.startPage(communityEntity.getPage(),50);
        List<CommunityEntity> entity = communityMapper.query(paramDto);
        return entity;
    }

}
