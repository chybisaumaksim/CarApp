package com.godeltech.pt11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String model;

    @Enumerated(EnumType.STRING)
    private Colour colour;

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

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
