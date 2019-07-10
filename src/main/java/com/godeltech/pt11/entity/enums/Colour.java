package com.godeltech.pt11.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Colour {
    RED,
    BLUE,
    GREEN;

    @JsonCreator
    public static Colour of(String value) {
        for (Colour item : Colour.values()) {
            if (value.equals(item.name())) {
                return item;
            }
        }
        throw new EnumConstantNotPresentException(Colour.class, "Not such colour");
}
}
