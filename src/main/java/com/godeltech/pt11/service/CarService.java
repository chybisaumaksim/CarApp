package com.godeltech.pt11.service;

import com.godeltech.pt11.dto.Car;
import com.godeltech.pt11.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public void createCar(Car car) {
        carRepository.save(car);
    }

    public Car getCar(Long id) {
        return carRepository.getOne(id);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

//    public List<Car> getCarByColour(Colour colour) {
//        return carRepository.findByColour(colour);
//    }
}
