package com.commons.service;

import com.commons.entity.DistrictEntity;

import java.util.List;

/**
 * @Description(功能描述) :省份
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/12 10:10
 **/
public interface DistrictService {
	DistrictEntity getDistrict(int id);

	/**
	  * @Description(功能描述): 批量添加省份
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/2 9:44
	  **/
	public int addBatch(List<DistrictEntity> districts);

}
