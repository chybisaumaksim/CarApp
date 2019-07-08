package com.godeltech.pt11.dto;

import com.godeltech.pt11.entity.Colour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public class CarDTO {

    @Id
    @GeneratedValue
    private Long carId;

    private String model;

    private Colour colour;

    public CarDTO(Long carId, String model, Colour colour) {
        this.carId = carId;
        this.model = model;
        this.colour = colour;
    }
}
