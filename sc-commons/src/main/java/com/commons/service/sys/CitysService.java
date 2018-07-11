package com.commons.service.sys;

import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.AreasEntity;
import com.commons.entity.sys.CitysEntity;
import com.commons.entity.sys.ProvincesEntity;
import com.github.pagehelper.PageInfo;

/**
 * @Description(功能描述) :省份
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
public interface CitysService {

	/**
	 * @Description(功能描述): 查询省份
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:43
	 **/
	PageInfo<ProvincesEntity> findProvinces(ParamDto paramDto);
	/**
	 * @Description(功能描述): 城市
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:52
	 **/
	PageInfo<CitysEntity> findCitys(ParamDto paramDto);
	/**
	 * @Description(功能描述): 区县
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:52
	 **/
	PageInfo<AreasEntity> findAreas(ParamDto paramDto);

}
