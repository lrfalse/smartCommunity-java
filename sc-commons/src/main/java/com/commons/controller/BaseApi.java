package com.commons.controller;

import com.commons.dto.HttpResults;
import com.commons.dto.IsJsonDTO;
import com.commons.utils.CommonUtils;
import com.commons.utils.JsonUtils;
import com.commons.utils.MD5Utils;
import com.commons.utils.encrypt.AESEncryptUtils;
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

	/**
	  * @Description(功能描述): 解析正文数据
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 12:07
	  **/
	public static IsJsonDTO receiveMsg(HttpServletRequest request) throws Exception {
		String tag = request.getParameter("tag");
		String body = request.getParameter("body");
		String key = request.getParameter("key");
		System.out.println("tagtagtagtagtagtagtagtagtag"+tag);
		logger.debug("----------------id:" + tag+"--------------");
		logger.debug("----------------body:" + body+"--------------");
		logger.debug("----------------key:" + key+"--------------");
		IsJsonDTO json = new IsJsonDTO();
		if (CommonUtils.isNotEmpty(tag)&& CommonUtils.isNotEmpty(key)) {
			if ("A".equals(tag) || "I".equals(tag)) {
				if(CommonUtils.isNotEmpty(body)){
					String decryptText=AESEncryptUtils.decrypt(body);
					boolean isJson= JsonUtils.isJson(decryptText);
					boolean isKey= MD5Utils.verifyMd5(key,decryptText) ;
					if(isJson&&isKey){
						json.setBodyJson(decryptText);
						json.setIsjson(true);
					}
				}else{
					logger.error("----------------密文为空----------------");
				}
			}
		}
		return json;
	}

	/**
	  * @Description(功能描述): 返回加密报文信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 17:38
	  **/
	public static HttpResults getHttpResult(Object data) throws Exception {
		HttpResults httpResult=new HttpResults();
		httpResult.setBody(AESEncryptUtils.encrypt(data));				//加密
		httpResult.setKey(MD5Utils.md5(JsonUtils.toJson(data)));		//添加签名
		return httpResult;
	}
}
