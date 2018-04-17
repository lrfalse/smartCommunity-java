package com.tools.service;

import com.commons.entity.DistrictEntity;
import com.tools.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
@Service
public class DistrictService {
	@Autowired
	private DistrictMapper districtMapper;	//省市

	/**
	  * @Description(功能描述): 省份单个添加
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/16 12:04
	  **/
	public int add(DistrictEntity district){
		return districtMapper.insert(district);
	}

	/**
	  * @Description(功能描述): 省份批量添加
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/16 12:04
	  **/
	public int addBatch(List<DistrictEntity> districts){
		return districtMapper.insertList(districts);

	}

}