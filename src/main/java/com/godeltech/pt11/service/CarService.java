package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarMapper;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.exceptions.CarNotFoundException;
import com.godeltech.pt11.repository.CarRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public Iterable<CarDTO> getAllCars() {
        return Lists
                .newArrayList(carRepository.findAll()).stream()
                .map(car -> carMapper.fromEntity(car))
                .collect(Collectors.toList());
    }

    public void deleteCar(Long id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
        } else {
            throw new CarNotFoundException(id);
        }
    }

    public CarDTO createCar(CarDTO carDTO) {
        return carMapper
                .fromEntity(carRepository
                        .save(carMapper
                                .toEntity(carDTO)));
    }

    public CarDTO getCar(Long id) {
        return carMapper
                .fromEntity(carRepository
                        .findById(id)
                        .orElseThrow(() -> new CarNotFoundException(id)));
    }

    public CarDTO updateCar(CarDTO carDTO, Long id) {
        Car car = carMapper.toEntity(carDTO);
        car.setCarId(id);
        if (carRepository.findById(id).isPresent()) {
            return carMapper
                    .fromEntity(carRepository
                            .save(car));
        } else {
            throw new CarNotFoundException(id);
        }
    }

    public Iterable<CarDTO> getCarByColour(Colour colour) {
        return carRepository.findByColour(colour).stream()
                .map((car -> carMapper.fromEntity(car)))
                .collect(Collectors.toList());
    }
}
