package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
public class CarDTOCreate {

    @NotBlank
    @Length(max = 20)
    private String model;

    private Colour colour;
}
