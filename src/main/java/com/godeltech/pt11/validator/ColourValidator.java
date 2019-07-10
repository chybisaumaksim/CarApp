package com.godeltech.pt11.validator;

import com.godeltech.pt11.entity.enums.Colour;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public interface ColourValidator extends ConstraintValidator<Colour, String> {

    List<String> colours = Arrays.asList("RED", "BLUE", "GREEN");

    @Override
    default boolean isValid(String colour, ConstraintValidatorContext constraintValidatorContext) {
        return colours.contains(colour.toUpperCase());
    }
}
