package com.godeltech.pt11.rest;

import com.godeltech.pt11.deserializer.CarEnumConverter;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.rest.apidescriptions.*;
import com.godeltech.pt11.service.CarService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Api(value = "Cars")
@Validated
public class CarController {

    private final CarEnumConverter carEnumConverter;

    private final CarService carService;

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
    @PutMapping("cars/{id}")
    public CarDTO updateCar(@Valid @RequestBody CarDTO carDTO, @Valid @PathVariable @Min(1) Long id) {
        return carService.updateCar(carDTO, id);
    }

    @FindCarByColourApiDescription
    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@Valid @PathVariable Colour colour) {
        return carService.getCarByColour(colour);
    }
}
