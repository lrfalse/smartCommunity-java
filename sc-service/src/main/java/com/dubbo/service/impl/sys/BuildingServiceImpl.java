package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.service.sys.BuildingService;
import com.dubbo.mapper.sys.BuildingMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @Description(功能描述): 楼层
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:16
  **/
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

	public int saveBuilding(BuildingEntity building){
		return buildingMapper.insert(building);
	}

	public int updateBuilding(BuildingEntity building){
		return buildingMapper.updateByPrimaryKey(building);
	}

    public PageInfo<BuildingEntity> findBuilding(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(),paramDto.getRows());
        return new PageInfo<>( buildingMapper.findBuilding(paramDto));
    }


}
