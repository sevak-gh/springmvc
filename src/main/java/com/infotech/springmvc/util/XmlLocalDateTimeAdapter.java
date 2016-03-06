package com.infotech.springmvc.util;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * LocalDateTime custom xml adapter.
 *
 * @author Sevak Gharibian
 *
 */
@Component
public class XmlLocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Resource
    private MessageSource messageSource;

    @Override
    public LocalDateTime unmarshal(String formattedDateTime) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        LocalDateTime dateTime = LocalDateTimeConverterFormatter.parse(formattedDateTime, locale, "yyyy/MM/dd HH:mm:ss");
        return dateTime;
    }

    @Override
    public String marshal(LocalDateTime dateTime) throws Exception {        
        Locale locale = LocaleContextHolder.getLocale();
        String formattedDateTime = LocalDateTimeConverterFormatter.print(dateTime, locale, "yyyy/MM/dd HH:mm:ss");
        return formattedDateTime;
    }
}
