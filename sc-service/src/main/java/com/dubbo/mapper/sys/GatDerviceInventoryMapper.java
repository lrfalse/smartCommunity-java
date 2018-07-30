package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.GatDerviceInventoryEntity;

import java.util.List;

/**
  * @Description(功能描述): 中商设备库存表
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/23 17:10
  **/
public interface GatDerviceInventoryMapper extends MyMapper<GatDerviceInventoryEntity> {


	List<GatDerviceInventoryEntity> findGatDerviceInventory(ParamDto paramDto);
}
