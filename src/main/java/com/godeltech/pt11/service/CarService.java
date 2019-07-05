package com.godeltech.pt11.service;

import com.godeltech.pt11.dto.Car;
import com.godeltech.pt11.dto.Colour;
import com.godeltech.pt11.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public Iterable<Car> getCarByColour(Colour colour) {
        return carRepository.findByColour(colour);
    }
}
