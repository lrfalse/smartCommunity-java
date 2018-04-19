package com.dubbo.controller;

import com.commons.controller.BaseApi;
import com.commons.entity.DistrictEntity;
import com.dubbo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    /**
     * @Description(功能描述): 新增省市区
     * @author(作者): lrfalse<wangliyou>
     * @date (开发日期): 2018/4/3 18:17
     **/
    @RequestMapping(value = "/getDistrictById")
    public DistrictEntity addDistrict() throws Exception {
		return districtService.getDistrict(3);
    }

}
