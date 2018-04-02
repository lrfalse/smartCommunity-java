package com.commons.dto;

import java.io.Serializable;

/**
  * @Description(功能描述): 返回http请求结果
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:40
  **/
public class HttpResults implements Serializable {
	private static final long serialVersionUID = 5090902096348098928L;
	private String statusCode ;	//状态码为
	private String statusMsg;	//返回消息
	private String key;			//加密数据
	private String body;		//主体内容

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
