package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDTO {

    private Long carId;

    private String model;

    private Colour colour;
}
