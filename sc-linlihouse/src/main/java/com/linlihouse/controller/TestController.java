package com.linlihouse.controller;

import com.commons.dto.HttpResults;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@RequestMapping("")
public class TestController {


	@RequestMapping(value = "/test")
	public HttpResults httpResultstest(HttpServletRequest request){

		return  new HttpResults();
	}

}
