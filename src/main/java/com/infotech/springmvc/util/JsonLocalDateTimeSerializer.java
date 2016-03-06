package com.infotech.springmvc.util;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.annotation.Resource;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LocalDateTime custom json serializer.
 *
 * @author Sevak Gharibian
 *
 */
@Component    
public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Resource
    private MessageSource messageSource;
    
    @Override
    public void serialize(LocalDateTime dateTime, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Locale locale = LocaleContextHolder.getLocale();
        String formattedDateTime = LocalDateTimeConverterFormatter.print(dateTime, locale, "yyyy/MM/dd HH:mm:ss");
        gen.writeString(formattedDateTime);
    }
}
