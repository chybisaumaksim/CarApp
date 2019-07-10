package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarMapper;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.exceptions.CarNotFoundException;
import com.godeltech.pt11.repository.CarRepository;
import com.godeltech.pt11.rest.CarController;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CarService {

    private static Logger logger = LoggerFactory.getLogger(CarService.class);

    private CarRepository carRepository;

    private CarMapper carMapper;

    public Iterable<CarDTO> getAllCars() {
        return Lists
                .newArrayList(carRepository.findAll()).stream()
                .map(car -> carMapper.fromEntity(car))
                .collect(Collectors.toList());
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarDTO createCar(CarDTO carDTO) {
        logger.info("Creating new Car");
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

    public CarDTO updateCar(CarDTO carDTO) {
        Long carId = carDTO.getCarId();
        if (carRepository.findById(carId).isPresent()) {
            return carMapper
                    .fromEntity(carRepository
                            .save(carMapper.toEntity(carDTO)));
        } else {
            logger.error("Car with " + carId + " not exists in database");
            throw new CarNotFoundException(carId);
        }
    }

    public Iterable<CarDTO> getCarByColour(Colour colour) {
        return carRepository.findByColour(colour).stream()
                .map((car -> carMapper.fromEntity(car)))
                .collect(Collectors.toList());
    }
}
