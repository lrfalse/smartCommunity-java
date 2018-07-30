package com.admin.controller.housingestate;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.RoomEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.RoomService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :房号
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/9 17:19
 **/
@RestController
public class RoomController extends BaseSysApi {

	@Autowired
	private RoomService roomService;
	/**
	  * @Description(功能描述): 房号
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:09
	  **/
	@PostMapping("/saveRoom")
	public HttpResults saveRoom(@RequestBody(required = false) RoomEntity room){
		if(CommonUtils.isNotEmpty(room)){
			ParamDto dto=new ParamDto();
			dto.put("buildingId_where", room.getBuildingId());
			dto.put("housingEstateId_where", room.getHousingEstateId());
			dto.put("name_where", room.getName());
			PageInfo counts= roomService.findRoom(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.ROOM_EXIST);
			}
			int result=roomService.saveRoom(room);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改楼层信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:12
	  **/
	@PostMapping("/updateRoom")
	public HttpResults updateRoom(@RequestBody RoomEntity room){
		if(CommonUtils.isNotEmpty(room)){
			int result=roomService.updateRoom(room);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/** 
	  * @Description(功能描述): 查询房号信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 18:45
	  **/ 
	@PostMapping("/findRoom")
	public HttpResults findRoom(@RequestBody(required=false) RoomEntity room) throws Exception {
		ParamDto dto=new ParamDto();
		if(CommonUtils.isNotEmpty(room)){
			dto.put("id_where", room.getId());
			dto.put("housingEstateId_where", room.getHousingEstateId());
			dto.put("buildingId_where", room.getBuildingId());
			dto.put("name_where", room.getName());
			dto.put("propertyId_where", room.getPropertyId());
			dto.setPage(room.getPage());
			dto.setRows(room.getRows());
		}
		PageInfo result= roomService.findRoom(dto);
		return getHttpResult(result);
	}


}
