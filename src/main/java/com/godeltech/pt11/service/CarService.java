package com.godeltech.pt11.service;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.dto.CarDTOCreate;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.exceptions.CarNotFoundException;
import com.godeltech.pt11.exceptions.NotConsistDataException;
import com.godeltech.pt11.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public Iterable<CarDTO> getAllCars() {
        return StreamSupport
                .stream(carRepository.findAll().spliterator(), false)
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteCar(Long id) {
        Car foundCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(foundCar);
    }

    public CarDTO createCar(CarDTOCreate carDTOCreate) {
        Car newCar = modelMapper.map(carDTOCreate, Car.class);
        carRepository.save(newCar);
        return modelMapper.map(newCar, CarDTO.class);
    }

    public CarDTO getCar(Long id) {
        Car foundCar = carRepository
                .findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        return modelMapper.map(foundCar, CarDTO.class);
    }

    public CarDTO updateCar(CarDTO carDTO, Long id) {
        if (!carDTO.getCarId().equals(id)) {
            throw new NotConsistDataException(carDTO.getCarId(), id);
        }
        Car car = modelMapper.map(carDTO, Car.class);
        carRepository.findById(id).orElseThrow(()->new CarNotFoundException(id));
        carRepository.save(car);
        return modelMapper.map(car, CarDTO.class);
    }

    public Iterable<CarDTO> getCarByColour(Colour colour) {
        return carRepository.findByColour(colour).stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }
}
