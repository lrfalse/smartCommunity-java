package com.dubbo.service.impl;

import com.commons.entity.DistrictEntity;
import com.dubbo.mapper.DistrictMapper;
import com.dubbo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
@Service
public class DistrictServiceImpl  implements DistrictService {
	@Autowired
	private DistrictMapper districtMapper;	//省市

	 public DistrictEntity getDistrict(int id){
	 	return districtMapper.selectByPrimaryKey(id);
	 }

}
