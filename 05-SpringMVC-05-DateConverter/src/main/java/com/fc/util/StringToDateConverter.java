package com.fc.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        String pattern = "yyyy/MM/dd";

        if (source.contains("-")) {
            pattern = "yyyy-MM-dd";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        Date parse = null;
        try {
            parse = formatter.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }
}
