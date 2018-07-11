package com.dubbo.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.dto.dbDto.ParamDto;
import com.commons.entity.sys.AreasEntity;
import com.commons.entity.sys.CitysEntity;
import com.commons.entity.sys.ProvincesEntity;
import com.commons.service.sys.CitysService;
import com.dubbo.mapper.sys.CitysMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description(功能描述) :省份
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
@Service
public class CitysServiceImpl implements CitysService {
	@Autowired
	private CitysMapper citysMapper;	//省市

	/**
	 * @Description(功能描述): 查询省份
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:43
	 **/
	public PageInfo<ProvincesEntity> findProvinces(ParamDto paramDto){
		return new PageInfo<>(citysMapper.findProvinces(paramDto));
	}
	/**
	 * @Description(功能描述): 城市
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:52
	 **/
	public PageInfo<CitysEntity> findCitys(ParamDto paramDto){
		return new PageInfo<>(citysMapper.findCitys(paramDto));
	}
	/**
	 * @Description(功能描述): 区县
	 * @author(作者): lrfalse<wangliyou>
	 * @date(开发日期): 2018/7/10 16:52
	 **/
	public PageInfo<AreasEntity> findAreas(ParamDto paramDto){
		return new PageInfo<>(citysMapper.findAreas(paramDto));
	}
}
