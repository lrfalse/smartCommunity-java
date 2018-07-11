package com.commons.controller;

import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.BasePageDto;
import com.commons.enums.AppServiceEnums;
import com.commons.enums.SysCodeEnums;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description(功能描述) :父api
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:12
 **/
@SuppressWarnings("Duplicates")
public class BaseSysApi {
	private static final Logger logger = LoggerFactory.getLogger(BaseSysApi.class);

	public  HttpResults httpResults;

	/**
	  * @Description(功能描述): 解析正文数据
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 12:07
	  **/
	public static IsJsonDTO receiveMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials","true");
		//告诉浏览器编码方式
		response.setHeader("Content-Type","text/html;charset=UTF-8" );
		request.getParameterNames();
		request.getParameterMap();
		String body = request.getParameter("body");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		logger.debug("----------------body:" + body+"--------------");
		IsJsonDTO json = new IsJsonDTO();
		if (CommonUtils.isNotEmpty(body)||CommonUtils.isNotEmpty(pwd)||CommonUtils.isNotEmpty(name)) {
			boolean isJson= JsonUtils.isJson(body);
			if(isJson){
				json.setBodyJson(body);
				json.setIsjson(true);
			}
		}
		return json;
	}

	/**
	  * @Description(功能描述): 获取isJsonDto对象
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 13:45
	  **/
	public IsJsonDTO getIsJson(HttpServletRequest request){
		return (IsJsonDTO)request.getAttribute("preHandleJsonDto");
	}

	/**
	  * @Description(功能描述): 返回json报文信息 默认成功
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 17:38
	  **/
	public static HttpResults getHttpResult(Object data) throws Exception {
		HttpResults httpResults=new HttpResults();
		httpResults.setBody(JsonUtils.toJson(data));					//json字符串
		httpResults.setKey(MD5Utils.md5(JsonUtils.toJson(data)));		//添加签名
		httpResults.setStatusCode(AppServiceEnums.SYS_SUCCESS.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_SUCCESS.getMsg());
		return httpResults;
	}
	/**
	 * @Description(功能描述): 返回加密报文信息 返回分页对象
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/19 10:50
	 **/
	public static HttpResults getHttpResult(PageInfo pageInfo) throws Exception {
		return getHttpResult(new BasePageDto(pageInfo));
	}
	/**
	  * @Description(功能描述): 只返回状态结果
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:50
	  **/
	public static HttpResults getHttpResultOk() {
		HttpResults httpResults=new HttpResults();
		httpResults.setStatusCode(AppServiceEnums.SYS_SUCCESS.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_SUCCESS.getMsg());
		return httpResults;
	}
	/**
	  * @Description(功能描述): 只返回错误结果
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:50
	  **/
	public static HttpResults getHttpResultError() {
		HttpResults httpResults=new HttpResults();
		httpResults.setStatusCode(AppServiceEnums.SYS_DATA_ERROR.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_DATA_ERROR.getMsg());
		return httpResults;
	}

	/**
	  * @Description(功能描述): 请求异常并返回错误信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date(开发日期): 2018/7/9 21:11
	  **/
	public static HttpResults getHttpResultError(SysCodeEnums sysCodeEnums) {
		HttpResults httpResults=new HttpResults();
		httpResults.setStatusCode(sysCodeEnums.getCode());
		httpResults.setStatusMsg(sysCodeEnums.getMsg());
		return httpResults;
	}
	/**
	  * @Description(功能描述): int状态封装
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 11:21
	 * @param result  : 大于0 成功状态  or  失败状态
	  **/
	public static HttpResults getHttpResult(int result)  {
		if(result>0){
			return getHttpResultOk();
		}else{
			return getHttpResultError();
		}
	}

	/**
	  * @Description(功能描述): boolean 状态封装
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 11:22
	 * @param result : true 成功状态   false  失败状态
	  **/
	public static HttpResults getHttpResult(boolean result) throws Exception {
		if(result){
			return getHttpResultOk();
		}else{
			return getHttpResultError();
		}
	}



}
