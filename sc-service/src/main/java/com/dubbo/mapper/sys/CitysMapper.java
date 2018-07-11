package com.dubbo.mapper.sys;

import com.commons.config.MyMapper;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.AreasEntity;
import com.commons.entity.sys.CitysEntity;
import com.commons.entity.sys.ProvincesEntity;

import java.util.List;

/** 
  * @Description(功能描述): 省市区
  * @author(作者): lrfalse<wangliyou>
  * @date(开发日期): 2018/7/10 16:43
  **/ 
public interface CitysMapper extends MyMapper<CitysEntity> {

    

	/** 
	  * @Description(功能描述): 查询省份
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:43
	  **/ 
    List<ProvincesEntity> findProvinces(ParamDto paramDto);
    /**
      * @Description(功能描述): 城市
      * @author(作者): lrfalse<wangliyou>
      * @date(开发日期): 2018/7/10 16:52
      **/
	List<CitysEntity> findCitys(ParamDto paramDto);
	/**
	  * @Description(功能描述): 区县
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/10 16:52
	  **/
	List<AreasEntity> findAreas(ParamDto paramDto);
}
