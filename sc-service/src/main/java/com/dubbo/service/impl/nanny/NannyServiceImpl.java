package com.dubbo.service.impl.nanny;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.NannyEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.NannyService;
import com.commons.utils.CommonUtils;
import com.dubbo.mapper.nannyMapper.NannyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Description:保姆服务实现
 * @Author:feihong
 * @Vsesion:v.10
 * @Create:2018/05/08 11:11:08
 */
@Service
public class NannyServiceImpl implements NannyService {

    private static final Logger logger=  LoggerFactory.getLogger(NannyServiceImpl.class);

    @Autowired
    private NannyMapper nannyMapper;

    /**
      * @Description(功能描述): 查询保姆列表
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 15:56
      **/

    @Override
    public List<NannyEntity> queryNanny(NannyEntity nannyEntity) {
        ParamDto paramDto = bulidParam(nannyEntity);
        List<NannyEntity> entities = nannyMapper.queryNanny(paramDto);
        return entities;
    }

    /**
      * @Description(功能描述): 加入我们
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 15:04
      **/
    @Override
    public int inJoin(NannyEntity nannyEntity) {
        NannyEntity entity = new NannyEntity();
        entity.setMobPhone(nannyEntity.getMobPhone());
        NannyEntity selectOne = nannyMapper.selectOne(entity);
        if (CommonUtils.isNotEmpty(selectOne)){
            throw new ScException(AppServiceEnums.EXIST_JOIN_NANNY);
        }
        int i = nannyMapper.insert(nannyEntity);
        return i;
    }

    /**
      * @Description(功能描述): 保姆查询条件对象构建
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 16:02
      **/
    public ParamDto bulidParam(NannyEntity entity){
        ParamDto paramDto = new ParamDto();
           paramDto.put("workTime",entity.getWorkTime());
        if (!CommonUtils.isEmpty(entity.getType())){
           if (entity.getType().equals("保姆")){
               paramDto.put("type",0);
           }else if (entity.getType().equals("月嫂")){
               paramDto.put("type",1);
           }else if (entity.getType().equals("育儿嫂")){
               paramDto.put("type",2);
           }else if (entity.getType().equals("护工")) {
               paramDto.put("type",3);
           }
        }else {
            paramDto.put("type",entity.getType());
        }
            paramDto.put("communityId",entity.getCommunityId());
      return paramDto;
    }
}
