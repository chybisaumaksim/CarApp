package com.godeltech.pt11.entity;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String model;

    @Enumerated(EnumType.STRING)
    private Colour colour;

    public Car(Long carId, String model, Colour colour) {
        this.carId = carId;
        this.model = model;
        this.colour = colour;
    }

    public Car(String model, Colour colour) {
        this.model = model;
        this.colour = colour;
    }

    public Car() {
    }
}
