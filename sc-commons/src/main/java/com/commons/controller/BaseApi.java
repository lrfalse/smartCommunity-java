package com.commons.controller;

import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.dto.anDto.BasePageDto;
import com.commons.enums.AppServiceEnums;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.commons.utils.encrypt.AESEncryptUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description(功能描述) :父api
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/2 22:12
 **/
public class BaseApi {
	private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

	public static HttpResults httpResults;

	static {
		if(CommonUtils.isEmpty(httpResults)){
			httpResults=new HttpResults();
		}
	}

	/**
	  * @Description(功能描述): 解析正文数据
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 12:07
	  **/
	public static IsJsonDTO receiveMsg(HttpServletRequest request) throws Exception {
		String tag = request.getParameter("tag");
		String body = request.getParameter("body");
		String key = request.getParameter("key");
		logger.debug("----------------tag:" + tag+"--------------");
		logger.debug("----------------body:" + body+"--------------");
		logger.debug("----------------key:" + key+"--------------");
		IsJsonDTO json = new IsJsonDTO();
		if (CommonUtils.isNotEmpty(tag)&& CommonUtils.isNotEmpty(key)&& CommonUtils.isNotEmpty(body)) {
			if ("A".equals(tag) || "I".equals(tag)) {
//				if(CommonUtils.isNotEmpty(body)){
					String decryptText=AESEncryptUtils.decrypt(body);
					boolean isJson= JsonUtils.isJson(decryptText);
					boolean isKey= MD5Utils.verifyMd5(key,decryptText) ;
					if(isJson&&isKey){
						json.setBodyJson(decryptText);
						json.setIsjson(true);
						json.setTag(tag);
					}
//				}else{
//					logger.error("----------------密文为空----------------");
//				}
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
	  * @Description(功能描述): 返回加密报文信息 默认成功
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 17:38
	  **/
	public static HttpResults getHttpResult(Object data) throws Exception {
		httpResults.setBody(AESEncryptUtils.encrypt(data));				//加密
		httpResults.setKey(MD5Utils.md5(JsonUtils.toJson(data)));		//添加签名
		httpResults.setStatusCode(AppServiceEnums.SYS_SUCCESS.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_SUCCESS.getMsg());
		return httpResults;
	}
	/**
	 * @Description(功能描述) : 返回加密报文信息 返回分页对象
	 * @Author(作者) : xly<xielinyang>
	 * @Date(开发日期) : 2018/4/28 10:44
	 */
	public static HttpResults getHttpResult(PageInfo pageInfo) throws Exception {
		httpResults=getHttpResult(new BasePageDto(pageInfo));
		return httpResults;
	}
	/**
	  * @Description(功能描述): 只返回状态结果
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:50
	  **/
	public static HttpResults getHttpResultOk() throws Exception {
		httpResults.setStatusCode(AppServiceEnums.SYS_SUCCESS.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_SUCCESS.getMsg());
		return httpResults;
	}
	/**
	  * @Description(功能描述): 只返回错误结果
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 10:50
	  **/
	public static HttpResults getHttpResultError() throws Exception {
		httpResults.setStatusCode(AppServiceEnums.SYS_DATA_ERROR.getCode());
		httpResults.setStatusMsg(AppServiceEnums.SYS_DATA_ERROR.getMsg());
		return httpResults;
	}
	/**
	  * @Description(功能描述): int状态封装
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/19 11:21
	 * @param result  : 大于0 成功状态  or  失败状态
	  **/
	public static HttpResults getHttpResult(int result) throws Exception {
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
