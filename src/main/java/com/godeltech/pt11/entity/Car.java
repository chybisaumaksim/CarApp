package com.godeltech.pt11.entity;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name="colour")
    private Colour colour;
}
