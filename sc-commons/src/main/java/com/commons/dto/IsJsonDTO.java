package com.commons.dto;

import lombok.Data;

/**
  * @Description(功能描述): 验证是否是json对象
  * @author(作者): lrfalse<wangliyou>
  * @date (开发日期): 2018/4/3 9:48
  **/
@Data
public class IsJsonDTO {
	private String tag;        			//终端类型(I:苹果,A:安卓)
	private boolean isjson = false;		//是否json
	private String bodyJson;			//json字符串
}