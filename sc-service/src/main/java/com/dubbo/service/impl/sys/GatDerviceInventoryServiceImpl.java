package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.entity.sys.GatDerviceInventoryEntity;
import com.commons.service.sys.GatDerviceInventoryService;
import com.dubbo.mapper.sys.BuildingMapper;
import com.dubbo.mapper.sys.GatDerviceInventoryMapper;
import com.dubbo.mapper.sys.RoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @Description(功能描述): 楼层
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:16
  **/
@Service
public class GatDerviceInventoryServiceImpl implements GatDerviceInventoryService {

	@Autowired
	private GatDerviceInventoryMapper gatDerviceInventoryMapper;


	public int saveGatDerviceInventory(GatDerviceInventoryEntity gatDerviceInventory) {
		return gatDerviceInventoryMapper.insert(gatDerviceInventory);
	}

	public int updateGatDerviceInventory(GatDerviceInventoryEntity gatDerviceInventory) {
		int result= gatDerviceInventoryMapper.updateByPrimaryKey(gatDerviceInventory);
		return result;
	}

    public PageInfo<GatDerviceInventoryEntity> findGatDerviceInventory(ParamDto paramDto) {
		PageHelper.startPage(paramDto.getPage(),paramDto.getRows());
        return new PageInfo<>( gatDerviceInventoryMapper.findGatDerviceInventory(paramDto));
    }
}
