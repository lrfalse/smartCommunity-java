package com.admin.controller.Housingestate;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.BuildingService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :楼层
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/7/9 17:19
 **/
@RestController
public class BuildingController extends BaseSysApi {

	@Autowired
	private BuildingService buildingService;
	/**
	  * @Description(功能描述): 保存楼层信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:09
	  **/
	@PostMapping("/saveBuilding")
	public HttpResults saveBuilding(@RequestBody BuildingEntity building){
		if(CommonUtils.isNotEmpty(building)){
			ParamDto dto=new ParamDto();
			dto.put("propertyId_where", building.getPropertyId());
			dto.put("housingEstateId_where", building.getHousingEstateId());
			dto.put("name_where", building.getName());
			PageInfo counts= buildingService.findBuilding(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.PROPERCOMPANY_EXIST);
			}
			int result=buildingService.saveBuilding(building);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改楼层信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:12
	  **/
	@PostMapping("/updateBuilding")
	public HttpResults updateBuilding(@RequestBody BuildingEntity building){
		if(CommonUtils.isNotEmpty(building)){
			int result=buildingService.updateBuilding(building);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/** 
	  * @Description(功能描述): 查询物业公司
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 18:45
	  **/ 
	@PostMapping("/findBuilding")
	public HttpResults findBuilding(@RequestBody BuildingEntity building) throws Exception {
		if(CommonUtils.isNotEmpty(building)){
			ParamDto dto=new ParamDto();
			dto.put("propertyId_where", building.getPropertyId());
			dto.put("housingEstateId_where", building.getHousingEstateId());
			dto.put("name_where", building.getName());
			dto.setPage(building.getPage());
			dto.setRows(building.getRows());
			PageInfo result= buildingService.findBuilding(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
}
