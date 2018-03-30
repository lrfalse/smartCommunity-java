package com.commons.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	@SuppressWarnings("unchecked")
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
}
