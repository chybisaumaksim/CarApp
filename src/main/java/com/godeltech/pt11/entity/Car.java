package com.godeltech.pt11.entity;

import com.godeltech.pt11.entity.enums.Colour;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "cars")
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    private String model;

    @Enumerated(EnumType.STRING)
    private Colour colour;

//
//    @JsonSetter
//    public Colour setColour(Colour colour) {
//        return Colour.valueOf(colour.toString().toUpperCase());
//    }
}
