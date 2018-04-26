package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.entity.FaceEntity;
import com.commons.service.FaceRegisterService;
import com.dubbo.mapper.FaceRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:人脸提交实现
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-26 11:11:16
 */
@Service
public class FaceRegisterImpl implements FaceRegisterService {

    @Autowired
    private FaceRegisterMapper faceRegisterMapper;

    public int addFace(FaceEntity faceEntity){
      return faceRegisterMapper.insert(faceEntity);
    }
}
