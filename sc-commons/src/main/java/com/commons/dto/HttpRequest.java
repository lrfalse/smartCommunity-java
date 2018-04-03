package com.commons.dto;

import java.io.Serializable;

/**
 * @Description(功能描述) :请求参数
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 21:46
 **/
public class HttpRequest implements Serializable {
	private static final long serialVersionUID = 5090902096348098928L;
	private String key;
	private String tag;
	private String body;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
