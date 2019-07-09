package com.godeltech.pt11.rest;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("cars")
    public Iterable<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @DeleteMapping("cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

    @GetMapping("cars/{id}")
    public CarDTO getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping("cars")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @PutMapping("cars")
    public CarDTO updateCar(@RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findCarByColour(@PathVariable String colour) {
        return carService.getCarByColour(Colour.valueOf(colour.toUpperCase()));
    }
}
