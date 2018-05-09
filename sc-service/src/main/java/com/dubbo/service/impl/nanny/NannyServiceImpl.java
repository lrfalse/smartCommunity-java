package com.dubbo.service.impl.nanny;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.HomeMakEntity;
import com.commons.entity.NannyEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.exception.ScException;
import com.commons.service.NannyService;
import com.commons.utils.CommonUtils;
import com.commons.utils.DateUtils;
import com.dubbo.mapper.nannyMapper.HomeMakMapper;
import com.dubbo.mapper.nannyMapper.NannyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
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

    @Autowired
    private HomeMakMapper homeMakMapper;

    /**
      * @Description(功能描述): 保姆详情
      * @author(作者): feihong
      * @date (开发日期):2018/5/9 12:01
      **/
    @Override
    public NannyEntity nannyDetail(String id) {
        ParamDto paramDto=new ParamDto();
        paramDto.put("id",id);
        NannyEntity selectOne = nannyMapper.queryEntity(paramDto);
        return selectOne;
    }

    /**
      * @Description(功能描述): 查询保姆列表
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 15:56
      **/

    @Override
    public List<NannyEntity> queryNanny(NannyEntity nannyEntity) {
        ParamDto paramDto = bulidParam(nannyEntity);
        if (nannyEntity.getTag().equals("0")){
            List<NannyEntity> entities = nannyMapper.queryNannyDesc(paramDto);
            return entities;
        }else {
            List<NannyEntity> entities = nannyMapper.queryNannyAsc(paramDto);
            return entities;
        }
    }

    /**
      * @Description(功能描述): 加入我们
      * @author(作者): feihong
      * @date (开发日期):2018/5/8 15:04
      **/
    @Override
    public int inJoin(HomeMakEntity homeMakEntity) {
        HomeMakEntity entity = new HomeMakEntity();
        entity.setMobPhone(homeMakEntity.getMobPhone());
        HomeMakEntity selectOne = homeMakMapper.selectOne(entity);
        if (CommonUtils.isNotEmpty(selectOne)){
            throw new ScException(AppServiceEnums.EXIST_JOIN_NANNY);
        }
        entity.setApplyTime(DateUtils.getDateStringForYMDHMS());
        int i = homeMakMapper.insert(homeMakEntity);
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
            paramDto.put("type",entity.getType());
            paramDto.put("communityId",entity.getCommunityId());
      return paramDto;
    }
}
