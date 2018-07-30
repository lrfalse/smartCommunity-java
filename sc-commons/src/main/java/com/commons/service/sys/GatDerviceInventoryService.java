package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.GatDerviceInventoryEntity;
import com.github.pagehelper.PageInfo;

/**
  * @Description(功能描述): 中商设备库存表
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/23 19:12
  **/
public interface GatDerviceInventoryService {

	int saveGatDerviceInventory(GatDerviceInventoryEntity gatDerviceInventory);

	int updateGatDerviceInventory(GatDerviceInventoryEntity gatDerviceInventory);

	PageInfo<GatDerviceInventoryEntity> findGatDerviceInventory(ParamDto paramDto);
}
