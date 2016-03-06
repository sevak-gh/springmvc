package com.infotech.springmvc.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.ParseException;
import java.util.Locale;
import java.util.Date;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

/**
 * convert/format LocalDateTime.
 *
 * @author Sevak Gharibian
 *
 */
public class LocalDateTimeConverterFormatter {

    public static LocalDateTime parse(String formatted, Locale locale, String format) throws ParseException {
        if (locale.getLanguage().equalsIgnoreCase("fa")) { // locale = persian
            String[] tokens = formatted.split("/| |:");
            if ((tokens == null) || (tokens.length != 6)) {
                throw new ParseException("LocalDateTime in invalid format", 0);
            }
            Calendar calendar = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
            calendar.set(Calendar.YEAR, Integer.parseInt(tokens[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(tokens[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tokens[2]));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tokens[3]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(tokens[4]));
            calendar.set(Calendar.SECOND, Integer.parseInt(tokens[5]));
            Date dt = calendar.getTime();
            return LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault());
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(formatted, formatter);
        }
    }

    public static String print(LocalDateTime dateTime, Locale locale, String format) {
        if (dateTime == null) {
            return "";
        }    
        if (locale.getLanguage().equalsIgnoreCase("fa")) {   // locale == "persian" 
            Calendar calendar = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
            Date dt = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()); 
            calendar.setTime(dt);
            return String.format("%d/%d/%d %d:%d:%d\n", calendar.get(Calendar.YEAR),
                                                        calendar.get(Calendar.MONTH),
                                                        calendar.get(Calendar.DAY_OF_MONTH),
                                                        calendar.get(Calendar.HOUR_OF_DAY),
                                                        calendar.get(Calendar.MINUTE),
                                                        calendar.get(Calendar.SECOND));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return formatter.format(dateTime);
        }
    }
}
