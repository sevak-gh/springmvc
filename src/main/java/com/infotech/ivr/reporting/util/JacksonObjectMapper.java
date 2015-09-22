package com.infotech.ivr.reporting.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * customized json mapper.
 *
 * @author Sevak Gharibian
 *
 */
public class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new JsonLocalDateTimeSerializer());
        registerModule(module);
    }
}
