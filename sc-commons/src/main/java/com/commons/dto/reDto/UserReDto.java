package com.commons.dto.reDto;

import lombok.Data;

/**
 * @Description(功能描述) :注册请求dto
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/19 10:08
 **/
@Data
public class UserReDto {
	private String phone;	//手机号
	private String pwd;		//密码
	private String authCode;//验证码
}
