package com.fc.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// 自定义的格式化器
// 泛型就是我们需要格式化的数据类型
public class CustomFormatter implements Formatter<Date> {
    private String pattern = "yyyy/MM/dd";

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if (text.contains("-")) {
            pattern = "yyyy-MM-dd";
        }

        return new SimpleDateFormat(pattern, locale).parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat(pattern, locale).format(object);
    }
}
