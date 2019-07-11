package com.godeltech.pt11.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        Object timestamp = errorAttributes.get("timestamp");
        if (timestamp == null) {
            errorAttributes.put("timestamp", dateFormat.format(LocalDateTime.now()));
        } else {
            errorAttributes.put("timestamp", dateFormat.format(LocalDateTime.now()));
            errorAttributes.put("message", "invalid value");
            errorAttributes.put("status", "400");
        }
        log.error(errorAttributes.values().toString());
        return errorAttributes;
    }
}

