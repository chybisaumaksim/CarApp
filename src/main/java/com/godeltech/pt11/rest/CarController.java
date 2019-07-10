package com.godeltech.pt11.rest;

import com.godeltech.pt11.deserializer.CarEnumConverter;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.rest.apidescriptions.*;
import com.godeltech.pt11.service.CarService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(value = "Cars")
public class CarController {

    private CarEnumConverter carEnumConverter;

    private CarService carService;

    @Autowired
    public CarController(CarEnumConverter carEnumConverter, CarService carService) {
        this.carEnumConverter = carEnumConverter;
        this.carService = carService;
    }

    @GetAllCarsApiDescription
    @GetMapping("cars")
    public Iterable<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteCarApiDescription
    @DeleteMapping("cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

    @GetCarApiDescription
    @GetMapping("cars/{id}")
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @CreateCarApiDescription
    @PostMapping("cars")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @UpdateCarApiDescription
    @PutMapping("cars")
    public CarDTO updateCar(@RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @FindCarByColourApiDescription
    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@PathVariable String colour) {
        return carService.getCarByColour(carEnumConverter.convert(colour));
    }
}
