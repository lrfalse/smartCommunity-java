package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.entity.sys.HousingestateEntity;

import java.util.List;

/**
  * @Description(功能描述): 楼层
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:17
  **/
public interface BuildingMapper extends MyMapper<BuildingEntity> {


	List<BuildingEntity> findBuilding(ParamDto paramDto);
	int updateBuildingByParam(ParamDto paramDto);
}
