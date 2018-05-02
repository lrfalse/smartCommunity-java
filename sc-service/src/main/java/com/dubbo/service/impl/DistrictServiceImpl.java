package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.entity.DistrictEntity;
import com.commons.service.DistrictService;
import com.dubbo.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @Description(功能描述) :省份
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

	 
	 /** 
	   * @Description(功能描述): 批量添加省份
	   * @author(作者): lrfalse<wangliyou>
	   * @date(开发日期): 2018/5/2 9:44
	   **/ 
	public int addBatch(List<DistrictEntity> districts){
		return districtMapper.insertList(districts);
	}

}
