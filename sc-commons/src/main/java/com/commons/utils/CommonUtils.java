package com.commons.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description(功能描述) :公共工具类
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/3 10:57
 **/
public class CommonUtils {


	/**
	  * @Description(功能描述): 多个对象一起判断，其中一个为空则返回false，反之true
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 20:51
	  **/
	public static boolean isNotEmpty(Object... objs) {
		for(Object object:objs){
			if(isEmpty(object)){
				return false;
			}
		}
		return true;
	}
	/**
	  * @Description(功能描述): 判断参数是否不为空
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 10:58
	  **/
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if ("".equals(pObj)) {
			return false;
		}
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	  * @Description(功能描述): 多个对象判断，其中一个为空则返回true，反之false
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/24 20:52
	  **/
	public static boolean isEmpty(Object... objs) {
		for(Object object:objs){
			if(isEmpty(object)){
				return true;
			}
		}
		return false;
	}
	/**
	  * @Description(功能描述): 判断参数是否为空
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 10:58
	  **/
	public static boolean isEmpty(Object pObj) {
		return !isNotEmpty(pObj);
	}
}
