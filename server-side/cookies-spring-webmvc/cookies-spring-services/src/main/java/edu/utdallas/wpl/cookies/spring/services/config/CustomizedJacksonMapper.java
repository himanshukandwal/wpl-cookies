package edu.utdallas.wpl.cookies.spring.services.config;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class CustomizedJacksonMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomizedJacksonMapper() {
		super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		super.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, false);
		super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		super.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
		super.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
	}

}
