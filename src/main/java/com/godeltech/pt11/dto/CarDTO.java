package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;
import org.hibernate.action.spi.Executable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CarDTO {

    @Id
    @GeneratedValue
    private  Long carId;

    private String model;

    private Colour colour;

    public CarDTO(Long carId, String model, Colour colour) {
        this.carId = carId;
        this.model = model;
        this.colour = colour;
    }
}
