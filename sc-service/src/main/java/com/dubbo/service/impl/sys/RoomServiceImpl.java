package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.RoomEntity;
import com.commons.service.sys.RoomService;
import com.commons.vo.RoomVo;
import com.dubbo.mapper.sys.RoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
  * @Description(功能描述): 房号
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/14 10:16
  **/
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

	public int saveRoom(RoomEntity room) {
		room.setIsValid(0);
		room.setCreateDate(new Date());
		return roomMapper.insert(room);
	}

	public int updateRoom(RoomEntity room) {
		room.setUpdateDate(new Date());
		return roomMapper.updateByPrimaryKey(room);
	}

    public PageInfo<RoomVo> findRoom(ParamDto paramDto) {
        PageHelper.startPage(paramDto.getPage(),paramDto.getRows(),"createDate desc");
		return new PageInfo<>( roomMapper.findRoom(paramDto));
    }

}
