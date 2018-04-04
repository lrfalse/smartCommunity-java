package com.commons.dto;

import com.commons.enums.AppServiceEnums;
import lombok.Data;

import java.io.Serializable;

/**
  * @Description(功能描述): 返回请求结果
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:40
  **/
@Data
public class HttpResults implements Serializable {
	private static final long serialVersionUID = 5090902096348098928L;
	private String statusCode ;	//状态码
	private String statusMsg;	//状态消息
	private String key;			//md5值
	private String body;		//加密报文

	/**
	  * @Description(功能描述): 数据请求成功
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 16:56
	  **/
	public HttpResults(){
		this.statusCode= AppServiceEnums.SYS_SUCCESS.getCode();
		this.statusMsg=AppServiceEnums.SYS_SUCCESS.getMsg();
	}
}
