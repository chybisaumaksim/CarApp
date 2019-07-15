package com.godeltech.pt11.entity;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Digits(integer=3, fraction=0, message = "Не более 3-х знаков")
    private Long carId;

    @Size(min=2, max=20)
    @NotBlank
    private String model;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private Colour colour;
}
