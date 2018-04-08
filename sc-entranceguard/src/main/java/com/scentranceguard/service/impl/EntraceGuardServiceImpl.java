package com.scentranceguard.service.impl;

import com.scentranceguard.dao.EntraceGuardDao;
import com.scentranceguard.from.FaceCheck;
import com.scentranceguard.service.EntraceGuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:门禁类
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018-04-08 13:13:41
 */
@Service
public class EntraceGuardServiceImpl implements EntraceGuardService {
    @Autowired
    EntraceGuardDao entraceGuardDao;
    @Override
    public void saveImage(FaceCheck faceCheck) {
        entraceGuardDao.saveimage(faceCheck);
    }
}
