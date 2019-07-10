package com.godeltech.pt11.rest;

import com.godeltech.pt11.deserializer.CarEnumConverter;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.rest.apidescriptions.*;
import com.godeltech.pt11.service.CarService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequiredArgsConstructor
@RestController
@Api(value = "Cars")
@Validated
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
    public void deleteCar(@Valid @PathVariable @Min(1) long id) {
        carService.deleteCar(id);
    }

    @GetCarApiDescription
    @GetMapping("cars/{id}")
    public CarDTO getCar(@Valid @PathVariable @Min(1) Long id) {
        return carService.getCar(id);
    }

    @CreateCarApiDescription
    @PostMapping("cars")
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @UpdateCarApiDescription
    @PutMapping("cars")
    public CarDTO updateCar(@Valid @RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @FindCarByColourApiDescription
    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@Valid @PathVariable String colour) {
        return carService.getCarByColour(carEnumConverter.convert(colour));
    }
}
