package com.commons.enums;

/**
 * @Description(功能描述) :短信验证码错误信息
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/17 18:46
 **/
public enum SmsEnums {
	SUCCESS("200","验证成功"),
	ERROR405("405","AppKey为空"),
	ERROR406("406","AppKey无效"),
	ERROR456("456","国家代码或手机号码为空"),
	ERROR457("457","手机号码格式错误"),
	ERROR466("466","请求校验的验证码为空"),
	ERROR467("467","请求校验验证码频繁（5分钟内同一个appkey的同一个号码最多只能校验三次"),
	ERROR468("468","验证码错误"),
	ERROR474("474","没有打开服务端验证开关"),
	;
	private String code;
	private String msg;

	public static String getMsg(String code){
		switch (code){
			case "200":
				return SUCCESS.msg;
			case "405":
				return ERROR405.msg;
			case "406":
				return ERROR406.msg;
			case "456":
				return ERROR456.msg;
			case "466":
				return ERROR466.msg;
			case "457":
				return ERROR457.msg;
			case "467":
				return ERROR467.msg;
			case "468":
				return ERROR468.msg;
			case "474":
				return ERROR474.msg;
		}
		return "未知错误";
	}
	SmsEnums(String code, String msg) {
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
