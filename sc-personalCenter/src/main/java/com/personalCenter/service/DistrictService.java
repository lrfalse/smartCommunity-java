package com.personalCenter.service;

import com.commons.entity.DistrictEntity;
import com.personalCenter.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
@Service
public class DistrictService {
	@Autowired
	private DistrictMapper districtMapper;

	/**
	  * @Description(功能描述): 新增单个省份
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/17 15:02
	  **/
	public int add(DistrictEntity district){
		return districtMapper.insert(district);
	}
}
