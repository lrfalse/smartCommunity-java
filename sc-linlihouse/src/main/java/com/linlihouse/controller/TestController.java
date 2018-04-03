package com.linlihouse.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.TestDto;
import com.commons.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@RestController
@RequestMapping("/")
public class TestController extends BaseApi{


	/**
	  * @Description(功能描述): 测试demo
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 18:17
	  **/
	@RequestMapping(value = "/test")
	public HttpResults httpResultstest(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=(IsJsonDTO)request.getAttribute("preHandleJsonDto");
		HttpResults test=getHttpResult(new TestDto());
		return test;
	}
}
