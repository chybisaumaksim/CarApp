package com.godeltech.pt11.converter;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public Car toEntity(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    public CarDTO fromEntity(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }


}
