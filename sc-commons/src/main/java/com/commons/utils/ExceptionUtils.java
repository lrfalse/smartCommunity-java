package com.commons.utils;

import com.commons.dto.HttpResults;
import com.commons.enums.AppServiceEnums;

/**
 * @Description(功能描述) :异常信息返回
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2017/12/18 9:12
 **/
public class ExceptionUtils {

	/**
	  * @Description(功能描述): 操作成功
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2017/12/18 9:17
	  **/
	public static HttpResults retSuccess(String data){
		HttpResults resultVo=new HttpResults();
		resultVo.setStatusCode(AppServiceEnums.SUCCESS.getCode());
		resultVo.setErrorMsg(AppServiceEnums.SUCCESS.getMsg());
		resultVo.setBody(data);
		return resultVo;
	}
	/**
	  * @Description(功能描述): 返回成功 ，返回data为空
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2017/12/18 9:17
	  **/
	public static HttpResults retSuccess(){
		return retSuccess(null);
	}

	/**
	  * @Description(功能描述): 返回系统错误信息
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2017/12/18 9:17
	  **/
	public  static HttpResults retException(String retCode,String retMsg){
		HttpResults resultVo=new HttpResults();
		resultVo.setStatusCode(retCode);
		resultVo.setErrorMsg(retMsg);
		return resultVo;
	}

	public  static HttpResults retException(){
		HttpResults resultVo=new HttpResults();
		resultVo.setStatusCode(AppServiceEnums.EXCEPTION.getCode());
		resultVo.setErrorMsg(AppServiceEnums.EXCEPTION.getMsg());
		return resultVo;
	}

}
