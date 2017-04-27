package com.my.util;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomTimeDeserializer extends JsonDeserializer<Timestamp> {
	@Override
	public Timestamp deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String sDate = p.getText();
		Timestamp date = Timestamp.valueOf(sDate);
		return date;
	}
}
