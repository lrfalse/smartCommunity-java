package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.github.pagehelper.PageInfo;

/** 
  * @Description(功能描述): 楼层
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:15
  **/ 
public interface BuildingService {

	int saveBuilding(BuildingEntity building);

	int updateBuilding(BuildingEntity building);

	PageInfo<BuildingEntity> findBuilding(ParamDto paramDto);
}
