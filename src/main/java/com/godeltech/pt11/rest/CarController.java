package com.godeltech.pt11.rest;

import com.godeltech.pt11.dto.Car;
import com.godeltech.pt11.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteMapping("cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

    @GetMapping("cars/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping("cars")
    public Car create(@RequestBody Car car) {
        carService.createCar(car);
        return car;
    }

    @PutMapping("cars")
    public void updateEmployee(@RequestBody Car car) {
        carService.updateCar(car);
    }

//    @GetMapping("cars/byColor/{color}")
//    public List<Car> findCarByColour(@PathVariable Colour colour) {
//        return carService.getCarByColour(colour);
//    }
}
