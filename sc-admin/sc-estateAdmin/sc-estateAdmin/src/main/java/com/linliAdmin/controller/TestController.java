package com.linliAdmin.controller;

import com.commons.entity.DistrictEntity;
import com.commons.service.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@Controller
public class TestController {
	private final static Logger logger= LoggerFactory.getLogger(TestController.class);

	@Autowired
	private DistrictService districtService;

	@RequestMapping(value = "/test")
	public String test() {
		DistrictEntity dis= districtService.getDistrict(3);
		logger.info(dis.getName());
		return "test";
	}




}
