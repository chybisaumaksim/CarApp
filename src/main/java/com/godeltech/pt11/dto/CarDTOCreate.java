package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CarDTOCreate {

    private String model;

    private Colour colour;
}
