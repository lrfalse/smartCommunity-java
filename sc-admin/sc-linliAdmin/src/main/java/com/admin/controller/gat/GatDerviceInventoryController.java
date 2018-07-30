package com.admin.controller.gat;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.BuildingEntity;
import com.commons.entity.sys.GatDerviceInventoryEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.BuildingService;
import com.commons.service.sys.GatDerviceInventoryService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
  * @Description(功能描述): 中商设备库存
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/23 19:15
  **/
@RestController
public class GatDerviceInventoryController extends BaseSysApi {

	@Autowired
	private GatDerviceInventoryService gatDerviceInventoryService;
	/**
	  * @Description(功能描述): 添加库存
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/23 19:26
	  **/
	@PostMapping("/saveGatDerviceInventory")
	public HttpResults saveGatDerviceInventory(@RequestBody GatDerviceInventoryEntity gatDerviceInventory){
		if(CommonUtils.isNotEmpty(gatDerviceInventory)){
			ParamDto dto=new ParamDto();
			dto.put("derviceNum_where", gatDerviceInventory.getDerviceNum());
			PageInfo counts= gatDerviceInventoryService.findGatDerviceInventory(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.GAT_DERVICE_ERROR);
			}
			int result=gatDerviceInventoryService.saveGatDerviceInventory(gatDerviceInventory);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改库存信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/14 11:12
	  **/
	@PostMapping("/updateGatDerviceInventory")
	public HttpResults updateGatDerviceInventory(@RequestBody GatDerviceInventoryEntity gatDerviceInventory){
		if(CommonUtils.isNotEmpty(gatDerviceInventory)){
			int result=gatDerviceInventoryService.updateGatDerviceInventory(gatDerviceInventory);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 查询中商设备库存
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/23 19:17
	  **/
	@PostMapping("/findGatDerviceInventory")
	public HttpResults findGatDerviceInventory(@RequestBody(required=false) GatDerviceInventoryEntity gatDerviceInventory) throws Exception {
		ParamDto dto=new ParamDto();
		if(CommonUtils.isNotEmpty(gatDerviceInventory)){
			dto.setPage(gatDerviceInventory.getPage());
			dto.setRows(gatDerviceInventory.getRows());
		}
		PageInfo result= gatDerviceInventoryService.findGatDerviceInventory(dto);
		return getHttpResult(result);
	}


}
