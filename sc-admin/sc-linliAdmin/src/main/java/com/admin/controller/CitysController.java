package com.admin.controller;

import com.commons.controller.BaseSysApi;
import com.commons.dto.HttpResults;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.AreasEntity;
import com.commons.entity.sys.CitysEntity;
import com.commons.service.sys.CitysService;
import com.commons.utils.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
  * @Description(功能描述): 省市
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 16:16
  **/
@RestController
public class CitysController extends BaseSysApi {

	@Autowired
	private CitysService citysService;


	/**
	  * @Description(功能描述): 获取省份
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 15:06
	  **/
	@PostMapping("/findProvinces")
	public HttpResults findProvinces() throws Exception {
		PageInfo result= citysService.findProvinces(null);
		return getHttpResult(result);
	}
	/**
	  * @Description(功能描述): 查询城市
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 17:01
	  **/
	@PostMapping("/findCitys")
	public HttpResults findCitys(@RequestBody CitysEntity city) throws Exception {
		if(CommonUtils.isNotEmpty(city)&&CommonUtils.isNotEmpty(city.getProvincesId())){
			ParamDto dto=new ParamDto();
			dto.put("provincesId_where", city.getProvincesId());
			dto.put("isValid_where", 0);
			PageInfo result= citysService.findCitys(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
	/**
	  * @Description(功能描述): 查询区县
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 17:01
	  **/
	@PostMapping("/findAreas")
	public HttpResults findAreas(@RequestBody AreasEntity city) throws Exception {
		if(CommonUtils.isNotEmpty(city)&&CommonUtils.isNotEmpty(city.getCityId())){
			ParamDto dto=new ParamDto();
			dto.put("cityId_where", city.getCityId());
			dto.put("isValid_where", 0);
			PageInfo result= citysService.findAreas(dto);
			return getHttpResult(result);
		}
		return getHttpResultError();
	}
}
