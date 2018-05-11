package com.admin.controller;

import com.commons.dto.HttpResults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description(功能描述) :用户信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/5/10 8:49
 **/
@RestController
public class UserController {

	/**
	  * @Description(功能描述): 用户登录
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/5/10 8:55
	  **/
	@PostMapping("/login")
	public HttpResults login(){

	}

}
