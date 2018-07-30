package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.entity.sys.RoomEntity;
import com.commons.vo.RoomVo;
import com.github.pagehelper.PageInfo;

/** 
  * @Description(功能描述): 房号
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:15
  **/ 
public interface RoomService {

	int saveRoom(RoomEntity room);

	int updateRoom(RoomEntity room);

	PageInfo<RoomVo> findRoom(ParamDto paramDto);
}
