package com.linliHouse.service;

import com.commons.entity.District;
import com.linliHouse.mapper.DistrictMapper;
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

	public int add(District district){
		return districtMapper.insert(district);
	}
}
