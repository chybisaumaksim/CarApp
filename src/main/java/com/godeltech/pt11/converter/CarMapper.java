package com.godeltech.pt11.converter;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    private ModelMapper modelMapper;

    public CarMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Car toEntity(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    public CarDTO fromEntity(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }
}
