package com.godeltech.pt11.deserializer;

import com.godeltech.pt11.entity.enums.Colour;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarEnumConverter implements Converter<String, Colour> {

    @Override
    public Colour convert(String colour) {
        return Colour.valueOf(colour.toUpperCase());
    }
}
