package com.personalCenter.controller.user;

import com.alibaba.fastjson.JSON;
import com.commons.controller.BaseApi;
import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.reDto.UserReDto;
import com.commons.entity.UserEntity;
import com.commons.enums.AppServiceEnums;
import com.commons.service.RedisService;
import com.commons.service.UserService;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.commons.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) :注册
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/19 10:11
 **/
@RestController
public class RegisterController extends BaseApi {

	@Autowired
	private UserService userService;		//用户
	@Autowired
	private RedisService redisService;		//redis缓存
	/**
	 * @Description(功能描述): 注册
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/19 10:03
	 **/
	@PostMapping(value = "/register")
	public HttpResults register(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=getIsJson(request);
		UserReDto registerReDto= JSON.parseObject(isJson.getBodyJson(),UserReDto.class);
		String phone=registerReDto.getMobPhone();
		String pwd=registerReDto.getPwd();
		String authCode=redisService.get(phone+"checkCode");		//取出缓存中的数据
		if(CommonUtils.isNotEmpty(phone,pwd)){
			//if(CommonUtils.isNotEmpty(authCode)){					//验证码不存在或者过期
				UserEntity user=new UserEntity();
				user.setMobPhone(phone);
				user.setPwd(MD5Utils.md5(pwd));
				if(userService.saveUser(user)>0){
					httpResults=getHttpResultOk();
				}
			//}else{
			//	httpResults.setStatusCode(AppServiceEnums.AUTHCODE_TIMEOUT);
		   //}
		}else{
			httpResults=getHttpResultError();
		}
		return httpResults;
	}

	/**
	  * @Description(功能描述): 验证短信验证码
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:13
	  **/
	@PostMapping(value = "/checkCode")
	public HttpResults checkCode(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=getIsJson(request);
		UserReDto registerReDto=JSON.parseObject(isJson.getBodyJson(),UserReDto.class);
		String phone=registerReDto.getMobPhone();
		String authCode=registerReDto.getAuthCode();
		if(CommonUtils.isNotEmpty(phone)&&CommonUtils.isNotEmpty(authCode)){
			boolean result=true;//SmsUtils.checkCode(phone,authCode);
			if(result){
				redisService.set(phone+"checkCode",authCode,3L);		//放入缓存
			}
			httpResults=getHttpResult(result);
		}else{
			httpResults=getHttpResultError();
		}
		return httpResults;
	}

	/**
	  * @Description(功能描述): 检验电话号码是否
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 10:31
	  **/
	@PostMapping(value = "/checkPhone")
	public HttpResults checkPhone(HttpServletRequest request) throws Exception {
		IsJsonDTO isJson=getIsJson(request);
		UserReDto registerReDto=JSON.parseObject(isJson.getBodyJson(),UserReDto.class);
		if(CommonUtils.isNotEmpty(registerReDto.getMobPhone())){
			UserEntity user=new UserEntity();
			user.setMobPhone(registerReDto.getMobPhone());
			user=userService.getUser(user);
			if(CommonUtils.isNotEmpty(user)){
				httpResults=getHttpResultOk();				//电话号码存在
			}else{
				httpResults=getHttpResultError();			//电话号码不存在
			}
		}else{
			httpResults=getHttpResultError();
		}
		return httpResults;
	}
	public static void main(String[] args) throws Exception {
		UserReDto userReDto=new UserReDto();
		userReDto.setMobPhone("18716393365");
		userReDto.setAuthCode("652978");
		System.out.println(JsonUtils.toJson(userReDto));
	}
}
