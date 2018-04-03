package com.commons.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	  * @Description(功能描述): json数据转map
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 12:08
	  **/
	public static Map<String, Object> objectToMap(Object o){
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		Map<String, Object> map = null;
		try {
			String  jsonstr = mapper.writeValueAsString(o);
			map = mapper.readValue(jsonstr, Map.class);
		} catch (JsonProcessingException e) {
			map = null;
		} catch (IOException e) {
			map = null;
		}
		return map;
	}

	/**
	  * @Description(功能描述): 判断是否json格式
	  * @author(作者): lrfalse<wangliyou>
	  * @date (开发日期): 2018/4/3 14:33
	  **/
	public static boolean isJson(String json) throws JsonParseException {
		if (StringUtils.isBlank(json)) {
			return false;
		}
		boolean isjson=new JsonValidator().validate(json);
		if(isjson){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @Description(功能描述): 对象转成string字符串
	 * @author(作者): lrfalse<wangliyou>
	 * @date (开发日期): 2018/4/3 14:26
	 **/
	public static String toJson(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Class<?> clz = object.getClass();
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			Class fieldType = field.getType();
			if (fieldType.isPrimitive())
				continue;
			field.setAccessible(true);
			if (field.getType().toString().toString().equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
				String val = (String) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是Integer
			if (field.getType().toString().equals("int")) {
				System.out.println("field.getType().toString()==" + field.getType());
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
				Integer val = (Integer) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是Double
			if (field.getType().toString().equals("Double")) {
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
				Double val = (Double) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是Boolean 是封装类
			if (field.getType().toString().equals("Boolean")) {
				Method m = (Method) object.getClass().getMethod(field.getName());
				Boolean val = (Boolean) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
			// 反射找不到getter的具体名
			if (field.getType().toString().equals("boolean")) {
				Method m = (Method) object.getClass().getMethod(field.getName());
				Boolean val = (Boolean) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是Date
			if (field.getType().toString().equals("Date")) {
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
				Date val = (Date) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
			// 如果类型是Short
			if (field.getType().toString().equals("Short")) {
				Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
				Short val = (Short) m.invoke(object);
				if (val == null) {
					field.set(object, "");
				}
			}
		}
		return objectMapper.writeValueAsString(object);
	}
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
