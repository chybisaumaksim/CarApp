package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDTO {

    private Long carId;

    private String model;

    private Colour colour;
}
