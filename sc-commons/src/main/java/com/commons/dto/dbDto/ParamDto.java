package com.commons.dto.dbDto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description(功能描述) :封装查询条件
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/25 21:25
 **/
public class ParamDto<K, V> extends HashMap<K, V> implements Serializable {

	public ParamDto() {
	}

	public ParamDto(K key, V value) {
		put(key, value);
	}
}
