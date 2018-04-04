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
	NO_REQUEST_DATA("901","没有上送信息"),
	ERROR("902","用户登录失败，用户名或密码错误"),

	;
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
