package com.commons.enums;

/**
  * @Description(功能描述): 定义所有提供app服务错误码
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:46
  **/
public enum SysCodeEnums {

	/**
	 * 系统错误
	 */
	SYS_SUCCESS("000","数据请求成功"),
	SYS_DATA_ERROR("100","数据错误"),
	SYS_EXCEPTION("500","系统内部异常"),


	//业务公司板块
	LOGIN_ERROR("900","用户登录失败，用户名或密码错误"),
	PROPERCOMPANY_EXIST("910","物业公司信息已存在，请忽重复添加"),
	HOUSINGESTATE_EXIST("911","小区信息已存在，请忽重复添加"),
	ROOM_EXIST("912","小区房号信息已存在，请忽重复添加"),
	GAT_DERVICE_ERROR("913","当前设备串号已添加，请忽重复添加");


	private String code;
	private String msg;

	SysCodeEnums(String code, String msg) {
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
