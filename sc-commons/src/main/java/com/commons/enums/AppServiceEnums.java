package com.commons.enums;

/**
  * @Description(功能描述): 定义所有提供app服务错误码
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:46
  **/
public enum AppServiceEnums {

	/**
	 * 系统错误
	 */
	SYS_SUCCESS("000","数据请求成功"),
	SYS_DATA_ERROR("100","数据错误"),
	SYS_EXCEPTION("500","系统内部异常"),

	/**
	 * 业务逻辑错误
	 */
	NULL_USER_DATA("900","登录超时，请重新登录"),
	ERROR_CODE("901","验证码错误"),
	ERROR("902","用户登录失败，用户名或密码错误"),
	AUTHCODE_TIMEOUT("903","短信验证码过期，请重新验证手机号"),
	USER_EXIST("904","用户信息已经存在"),
	PHONE_NOT_EXIST("905","手机号码不存再"),
	PHONE_IN_BIND("906","手机号码已绑定"),
	EXIST_JOIN("907","用户已经参加该活动"),
	NOT_JOIN_ACTIVITY("908","用户没有参加任何活动"),
	EXIST_JOIN_NANNY("909","您已经加入过");
	private String code;
	private String msg;

	AppServiceEnums(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
