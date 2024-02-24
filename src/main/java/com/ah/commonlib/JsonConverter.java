package com.ah.commonlib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverter {
	
	private final ObjectMapper objectMapper;
	
	@Autowired
	public JsonConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		
	}

	public <T> T deserializeJson(Object jsonObject, Class<T> targetClass) {
		return objectMapper.convertValue(jsonObject, targetClass);
	}
}
