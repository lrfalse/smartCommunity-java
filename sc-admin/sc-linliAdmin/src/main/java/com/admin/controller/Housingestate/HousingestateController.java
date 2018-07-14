package com.admin.controller.Housingestate;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.HousingestateEntity;
import com.commons.enums.SysCodeEnums;
import com.commons.service.sys.HousingestateService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** 
  * @Description(功能描述): 小区信息
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/11 15:15
  **/ 
@RestController
public class HousingestateController extends BaseSysApi {

	@Autowired
	private HousingestateService housingestateService;



	/**
	  * @Description(功能描述): 保存小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:16
	  **/
	@PostMapping("/saveHousingestate")
	public HttpResults saveHousingestate(@RequestBody HousingestateEntity housingestate){
		if(CommonUtils.isNotEmpty(housingestate)){
			ParamDto dto=new ParamDto();
			dto.put("name_where", housingestate.getName());
			dto.put("areasId_where", housingestate.getAreasId());
			PageInfo counts= housingestateService.findHousingestate(dto);
			if(counts.getTotal()>0){
				return getHttpResultError(SysCodeEnums.HOUSINGESTATE_EXIST);
			}
			int result=housingestateService.saveHousingestate(housingestate);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 修改小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:15
	  **/
	@PostMapping("/updateHousingestate")
	public HttpResults updatHousingestate(@RequestBody HousingestateEntity housingestate){
		if(CommonUtils.isNotEmpty(housingestate)&&CommonUtils.isNotEmpty(housingestate.getId())){
			int result=housingestateService.updateHousingestate(housingestate);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}

	/**
	  * @Description(功能描述): 查询小区信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 15:06
	  **/
	@PostMapping("/findHousingestate")
	public HttpResults findHousingestate(@RequestBody HousingestateEntity housingestate) throws Exception {
		if(CommonUtils.isNotEmpty(housingestate)){
			ParamDto dto=new ParamDto();
			dto.put("propertyId_where", housingestate.getPropertyId());
			dto.put("name_where", housingestate.getName());
			dto.put("isValid_where", housingestate.getIsValid());
			dto.setPage(housingestate.getPage());
			dto.setRows(housingestate.getRows());
			PageInfo result= housingestateService.findHousingestate(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
}
