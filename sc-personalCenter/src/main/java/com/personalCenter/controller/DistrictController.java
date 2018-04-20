package com.personalCenter.controller;

import com.commons.controller.BaseApi;
import com.commons.entity.DistrictEntity;
import com.commons.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@RestController
@RequestMapping("/")
public class DistrictController extends BaseApi{


	@Autowired
	DistrictService districtService;	//地区dubbo测试用例


	@GetMapping("/dubboTest")
	public DistrictEntity test(){
		return districtService.getDistrict(3);

	}
}
