package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CarDTO {

    @Id
    @GeneratedValue
    private Long carId;

    private String model;

    private Colour colour;
}
