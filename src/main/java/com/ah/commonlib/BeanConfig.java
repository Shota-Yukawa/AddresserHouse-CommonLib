package com.ah.commonlib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class BeanConfig {

	@Bean
	@Primary
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 厳密なマッピング
		modelMapper.getConfiguration().setFullTypeMatchingRequired(true); // マッピング条件として型もチェックする
		return modelMapper;
	}
	
	@Bean(name = "modelMapperSkipNull")
	ModelMapper modelMapperSkipNull() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true); // nullはマッピングしない
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 厳密なマッピング
		modelMapper.getConfiguration().setFullTypeMatchingRequired(true); // マッピング条件として型もチェックする
		return modelMapper;
	}

	@Bean
	ObjectMapper objectMapper() {
		// ObjectMapperのインスタンスを作成
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(new JavaTimeModule());

		// LocalDateTimeのシリアライザとデシリアライザをカスタマイズ
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
		simpleModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
		objectMapper.registerModule(simpleModule);

		return objectMapper;
	}
}
