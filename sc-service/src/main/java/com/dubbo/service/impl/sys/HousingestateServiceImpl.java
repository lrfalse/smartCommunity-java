package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.HousingestateEntity;
import com.commons.entity.sys.RoomEntity;
import com.commons.service.sys.HousingestateService;
import com.dubbo.mapper.sys.BuildingMapper;
import com.dubbo.mapper.sys.HousingestateMapper;
import com.dubbo.mapper.sys.RoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 14:54
  **/
@Service
public class HousingestateServiceImpl implements HousingestateService {

    @Autowired
	private HousingestateMapper housingestateMapper;
	@Autowired
	private RoomMapper roomMapper;
	@Autowired
	private BuildingMapper buildingMapper;

	public int saveHousingestate(HousingestateEntity housingestate){
		housingestate.setCreateDate(new Date());
		return housingestateMapper.insert(housingestate);
	}

	public int updateHousingestate(HousingestateEntity housingestate){
		int reuslt= housingestateMapper.updateByPrimaryKey(housingestate);
		ParamDto paramDto=new ParamDto();
		if(reuslt>0){											//
			RoomEntity updateRoom=new RoomEntity();
			updateRoom.setHousingEstateId(housingestate.getId());
			paramDto.put("housingEstateName",housingestate.getName() );
			paramDto.put("housingEstateId_where",housingestate.getId() );
		}
		roomMapper.updateRoomByParam(paramDto);					//更新房间
		buildingMapper.updateBuildingByParam(paramDto);			//更新楼栋
		return reuslt;
	}

    /**
      * @Description(功能描述): 查询小区信息
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/10 14:54
      **/
    public PageInfo<HousingestateEntity> findHousingestate(ParamDto paramDto) {
		PageHelper.startPage(paramDto.getPage(),paramDto.getRows(),"createDate desc");
        return new PageInfo<>( housingestateMapper.findHousingestate(paramDto));
    }


}
