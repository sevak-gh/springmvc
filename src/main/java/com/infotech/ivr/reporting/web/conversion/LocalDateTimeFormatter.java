package com.infotech.ivr.reporting.web.conversion;

import com.infotech.ivr.reporting.util.LocalDateTimeConverterFormatter;

import java.time.LocalDateTime;
import java.text.ParseException;
import java.util.Locale;
import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.core.convert.ConversionFailedException;

/**
 * converter/formatter for LocalDateTime.
 *
 * @author Sevak Gharibian
 *
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Resource
    private MessageSource messageSource;

    @Override
    public LocalDateTime parse(String formatted, Locale locale) throws ParseException {
        //String format = messageSource.getMessage("date.format", null, locale);
        return LocalDateTimeConverterFormatter.parse(formatted, locale, "yyyy/MM/dd HH:mm:ss");
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        //String format = messageSource.getMessage("date.format", null, locale);
        return LocalDateTimeConverterFormatter.print(dateTime, locale, "yyyy/MM/dd HH:mm:ss");
    }
}
