package com.linliHouse.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.TestDto;
import com.commons.entity.District;
import com.commons.utils.RandomUtil;
import com.linliHouse.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@Controller
@RequestMapping("/")
public class DistrictController extends BaseApi{

	@Autowired
	private DistrictService districtService;
	/**
	  * @Description(功能描述): 新增省市区
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 18:17
	  **/
	@ResponseBody
	@RequestMapping(value = "/addDistrict")
	public HttpResults addDistrict() throws Exception {
		District district=new District();
		district.setAdcode(Integer.parseInt(RandomUtil.getRandomNum()));
		district.setLevel("aa");
		district.setLocation(Integer.parseInt(RandomUtil.getRandomNum(4))+","+Integer.parseInt(RandomUtil.getRandomNum(4)));
		district.setName("重庆"+Integer.parseInt(RandomUtil.getRandomNum()));
		district.setPcode("000");
		districtService.add(district);
		HttpResults test=getHttpResult(new TestDto());
		return test;
	}
}
