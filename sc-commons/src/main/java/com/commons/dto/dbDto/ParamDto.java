package com.commons.dto.dbDto;

import com.commons.entity.BaseIdEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description(功能描述) :封装查询条件
 * @author(作者) :lrfalse<wangliyou>
 * @date (开发日期) :2018/4/25 21:25
 **/
@Data
public class ParamDto<K, V> extends HashMap<K, V> implements Serializable {

	private Integer page = 1;

	private Integer rows = 10;
	public ParamDto() {
	}

	public ParamDto(K key, V value) {
		put(key, value);
	}

	public ParamDto(BaseIdEntity baseIdEntity) {
		this.page = baseIdEntity.getPage();
		this.rows = baseIdEntity.getRows();
	}
}
