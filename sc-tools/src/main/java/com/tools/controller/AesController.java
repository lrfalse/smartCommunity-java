package com.tools.controller;

import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.JsonUtils;
import com.commons.utils.encrypt.AESEncryptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
  * @Description(功能描述): 加解密
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/27 15:01
  **/
@Controller
@RequestMapping("/")
public class AesController extends  BaseApi{

	@GetMapping(value = "/aes")
	public String index() {
		return "aes";
	}

	/**
	 * @Description(功能描述): 加密
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/27 16:39
	 **/
	@ResponseBody
	@PostMapping(value = "/encrypt")
	public HttpResults encrypt(HttpServletRequest request ) throws Exception {
		String jsonBody = request.getParameter("jsonBody");
		boolean isJson= JsonUtils.isJson(jsonBody);
		if(isJson){
			httpResults=getHttpResult(jsonBody);
		}else{
			httpResults=getHttpResultError();
		}
		return httpResults;
	}

	/**
	 * @Description(功能描述): 解密
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/27 16:33
	 **/
	@ResponseBody
	@PostMapping(value = "/decrypt")
	public HttpResults decrypt(HttpServletRequest request ) throws Exception {
		String decryptText = request.getParameter("decryptText");
		decryptText= AESEncryptUtils.decrypt(decryptText);
		boolean isJson= JsonUtils.isJson(decryptText);
		if(isJson){
			httpResults.setStatusCode(AppServiceEnums.SYS_SUCCESS);
			httpResults.setBody(decryptText);
		}else{
			httpResults=getHttpResultError();
		}
		return httpResults;
	}



}
