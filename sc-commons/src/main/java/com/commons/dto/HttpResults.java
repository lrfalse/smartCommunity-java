package com.commons.dto;

/**
  * @Description(功能描述): 返回http请求结果
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/3/30 14:40
  **/
public class HttpResults {
	private String statusCode ;	//状态码为0，代表成功，其他都代表失败
	private String errorMsg;	//返回消息
	private String body;		//主题内容

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
