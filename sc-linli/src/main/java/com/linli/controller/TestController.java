package com.linli.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.TestDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description(功能描述) :测试
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:06
 **/
@Controller
public class TestController extends BaseApi{


	/**
	  * @Description(功能描述): 测试demo
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 18:17
	  **/
	@ResponseBody
	@RequestMapping(value = "/test")
	public HttpResults httpResultstest(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=(IsJsonDTO)request.getAttribute("preHandleJsonDto");
		HttpResults test=getHttpResult(new TestDto());
		return test;
	}
	@RequestMapping(value = "/index")
	public String index() {
		return "test";
	}




}
