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
	  * @Description(功能描述): 判断参数是否为空
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 10:58
	  **/
	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if ("".equals(pObj)) {
			return true;
		}
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}
}
