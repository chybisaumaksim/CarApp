package com.godeltech.pt11.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.godeltech.pt11.deserializer.CarEnumConverter;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(value = "Cars")
public class CarController {

    @Autowired
    private CarEnumConverter carEnumConverter;

    @Autowired
    private CarService carService;

    @ApiOperation(value = "Get all cars")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Car.class)
    })
    @GetMapping("cars")
    public Iterable<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @ApiOperation(value = "Delete car by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete of car completed successful"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @DeleteMapping("cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

    @ApiOperation(value = "Get cars by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completed successful"),
            @ApiResponse(code = 404, message = "Car with current ID not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("cars/{id}")
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @ApiOperation(value = "Create car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car created successful"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping("cars")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }


    @ApiOperation(value = "Update car")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Car updated successful"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PutMapping("cars")
    public CarDTO updateCar(@RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }


    @ApiOperation(value = "Find all the cars by selected colour")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Car with current colour not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @JsonCreator
    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@PathVariable String colour) {
        return carService.getCarByColour(carEnumConverter.convert(colour));
    }
}
