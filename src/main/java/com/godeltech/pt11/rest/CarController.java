package com.godeltech.pt11.rest;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.dto.CarDTOCreate;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.rest.apidescriptions.*;
import com.godeltech.pt11.service.CarService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@RestController
@Api(value = "Cars")
@Validated
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetAllCarsApiDescription
    @GetMapping("cars")
    public Iterable<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteCarApiDescription
    @DeleteMapping("cars/{id}")
    public void deleteCar(@PathVariable @Positive long id) {
        carService.deleteCar(id);
    }

    @GetCarApiDescription
    @GetMapping("cars/{id}")
    public CarDTO getCar(@PathVariable @Positive long id) {
        return carService.getCar(id);
    }

    @CreateCarApiDescription
    @PostMapping("cars")
    public CarDTO createCar(@RequestBody CarDTOCreate carDTOCreate) {
        return carService.createCar(carDTOCreate);
    }

    @UpdateCarApiDescription
    @PutMapping("cars/{id}")
    public CarDTO updateCar(@RequestBody CarDTO carDTO, @PathVariable @Positive long id) {
        return carService.updateCar(carDTO, id);
    }

    @FindCarByColourApiDescription
    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@PathVariable Colour colour) {
        return carService.getCarByColour(colour);
    }
}
