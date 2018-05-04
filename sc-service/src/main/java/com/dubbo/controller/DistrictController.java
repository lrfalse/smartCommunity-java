package com.dubbo.controller;

import com.commons.controller.BaseApi;
import com.commons.entity.DistrictEntity;
import com.commons.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
  * @Description(功能描述): dubbo验证
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/19 19:15
  **/
@RestController
@RequestMapping("/")
public class DistrictController extends BaseApi {

 	@Autowired
    private DistrictService districtService;

 	@Autowired
 	private Environment env;

    @RequestMapping(value = "/getDistrictById")
    public DistrictEntity addDistrict() throws Exception {
		String userImgUrl=env.getProperty("sc.user.img.url");
		return districtService.getDistrict(3);
    }

}
