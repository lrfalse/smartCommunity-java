package com.personalCenter.service;

import com.alibaba.fastjson.JSON;
import com.commons.dto.BindPhoneDto;
import com.commons.dto.IsJsonDTO;
import com.commons.entity.CommunityEntity;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.CommonUtils;
import com.personalCenter.mapper.CommunityMapper;
import com.personalCenter.mapper.LoginRegisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description:小区选择
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-20 14:14:58
 */
@Service
public class CommunityService {

    private static final Logger logger = LoggerFactory.getLogger(CommunityService.class);

    @Autowired
    private CommunityMapper CommunityMapper;

    @Autowired
    private LoginRegisterMapper loginRegisterMapper;

    /**
     * @Description(功能描述): 定位小区选择
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 14:22:33
     **/
    public Object communityquery(IsJsonDTO isJsonDTO) {
        CommunityEntity communityEntity = JSON.parseObject(isJsonDTO.getBodyJson(), CommunityEntity.class);
        if (CommonUtils.isEmpty(communityEntity)) {
            logger.info("userEntity:小区选择请求信息为空");
            return AppServiceEnums.SYS_DATA_ERROR.getCode();
        }
        List<CommunityEntity> communityEntityList = CommunityMapper.select(communityEntity);
        if (CommonUtils.isEmpty(communityEntityList)) {
            logger.info("小区信息为空");
        }
        return communityEntityList;
    }

    /**
     * @Description(功能描述): 选择小区
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 15:17:20
     **/
    public Object chooseCommunity(IsJsonDTO isJsonDTO) {
        CommunityEntity communityEntity = JSON.parseObject(isJsonDTO.getBodyJson(), CommunityEntity.class);
        if (CommonUtils.isEmpty(communityEntity)) {
            logger.info("userEntity:小区选择请求信息为空");
            return AppServiceEnums.SYS_DATA_ERROR.getCode();
        }
        CommunityEntity communityEntityList = CommunityMapper.selectOne(communityEntity);
        if (CommonUtils.isEmpty(communityEntityList)) {
            logger.info("没有你检索小区");
        }
        return communityEntityList;
    }

    /**
     * @Description(功能描述): 绑定手机号
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 16:21:21
     **/
    public BindPhoneDto bindPhone(IsJsonDTO isJsonDTO) {
        UserEntity userEntity = JSON.parseObject(isJsonDTO.getBodyJson(), UserEntity.class);
        if (CommonUtils.isEmpty(userEntity)){
            return buildBindPhoneEror();
        }
        UserEntity userEntity1 = loginRegisterMapper.phoneQuery(userEntity.getMobPhone());
        //手机号码注册过
        if (CommonUtils.isNotEmpty(userEntity1)) {
            if(userEntity.getToken().equals("Q")){
                int qy = loginRegisterMapper.updateQopenid(userEntity.getQopenId(), userEntity.getMobPhone());
                if (qy<0){
                    return buildBindPhoneEror();
                }
                if(userEntity1.getCommunityId()!=0){
                    return buildBindPhone();

                }else if (userEntity1.getCommunityId()==0){
                    return buildBindPhone1();
                }
            }else if(userEntity.getToken().equals("W")){
                int wy = loginRegisterMapper.updateWopenid(userEntity.getWopenId(), userEntity.getMobPhone());
                if(wy<0){
                    return buildBindPhoneEror();
                }
                if(userEntity1.getCommunityId()!=0){
                    return buildBindPhone();
                }else if (userEntity1.getCommunityId()==0){
                    return buildBindPhone1();
                }
            }
            //没有注册过
        }else {
            if (userEntity.getToken().equals("Q")){
                int qm = loginRegisterMapper.updateqphone(userEntity.getQopenId(), userEntity.getMobPhone());
                if (qm<0){
                    return buildBindPhoneEror();
                }
            }else if(userEntity.getToken().equals("W")){
                int wm = loginRegisterMapper.updatewphone(userEntity.getWopenId(), userEntity.getMobPhone());
                if (wm<0){
                    return buildBindPhoneEror();
                }
            }
            return buildBindPhone1();
        }
       return buildBindPhoneEror();
    }

    /**
      * @Description(功能描述): 手机号码绑定对象返回
      * @author(作者): feihong
      * @date (开发日期):2018-4-20 22:26:37
      **/
    public BindPhoneDto buildBindPhone(){
        BindPhoneDto bindPhoneDto = new BindPhoneDto();
        bindPhoneDto.setFtag("0");// 1:需要选择小区 0:已经选择
        bindPhoneDto.setToken("1");//1:绑定成功 0:绑定失败
        return bindPhoneDto;
    }

    /**
     * @Description(功能描述): 手机号码绑定对象返回
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 22:26:37
     **/
    public BindPhoneDto buildBindPhone1(){
        BindPhoneDto bindPhoneDto = new BindPhoneDto();
        bindPhoneDto.setFtag("1");
        bindPhoneDto.setToken("1");
        return bindPhoneDto;
    }

    /**
     * @Description(功能描述): 手机号码绑定对象返回
     * @author(作者): feihong
     * @date (开发日期):2018-4-20 22:26:37
     **/
    public BindPhoneDto buildBindPhoneEror(){
        BindPhoneDto bindPhoneDto = new BindPhoneDto();
        bindPhoneDto.setFtag("1");
        bindPhoneDto.setToken("0");
        return bindPhoneDto;
    }
}
