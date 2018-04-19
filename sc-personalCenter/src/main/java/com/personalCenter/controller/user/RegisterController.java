package com.personalCenter.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.reDto.UserReDto;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.commons.utils.SmsUtils;
import com.personalCenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) :
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/19 10:11
 **/
@RestController
public class RegisterController extends BaseApi {

	@Autowired
	private UserService userService;
	/**
	 * @Description(功能描述): 注册
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/19 10:03
	 **/
	@PostMapping(value = "/register")
	public HttpResults httpResultstest(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=(IsJsonDTO)request.getAttribute("preHandleJsonDto");
		UserReDto registerReDto= JSON.parseObject(isJson.getBodyJson(),UserReDto.class);
		UserEntity user=new UserEntity();
		user.setMobPhone(registerReDto.getPhone());
		user.setPwd(MD5Utils.md5(registerReDto.getPwd()));
		int result=userService.addUser(user);
		//TODO 返回小区信息
		return getHttpResult(result);
	}

	/**
	  * @Description(功能描述): 验证短信验证码
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:13
	  **/
	@PostMapping(value = "/checkCode")
	public HttpResults checkCode(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=(IsJsonDTO)request.getAttribute("preHandleJsonDto");
		UserReDto registerReDto=JSON.parseObject(isJson.getBodyJson(),UserReDto.class);
		boolean result=SmsUtils.checkCode(registerReDto.getPhone(),registerReDto.getAuthCode());
		HttpResults httpResults=getHttpResult(result);
		if(!result){
			httpResults.setStatusCode(AppServiceEnums.ERROR_CODE.getCode());
			httpResults.setStatusMsg(AppServiceEnums.ERROR_CODE.getMsg());
		}
		return httpResults;
	}
	public static void main(String[] args) throws Exception {
		UserReDto userReDto=new UserReDto();
		userReDto.setPhone("18716393365");
		userReDto.setAuthCode("652978");
		System.out.println(JsonUtils.toJson(userReDto));
	}
}
