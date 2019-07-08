package com.godeltech.pt11.rest;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Colour;
import com.godeltech.pt11.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(final CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public Iterable<CarDTO> getAll() {
        return carService.getAllCars();
    }

    @DeleteMapping("cars/{id}")
    public void delete(@PathVariable long id) {
        carService.deleteCar(id);
    }

    @GetMapping("cars/{id}")
    public CarDTO get(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping("cars")
    public CarDTO create(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @PutMapping("cars")
    public CarDTO update(@RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }

    @GetMapping("cars/byColour/{colour}")
    public Iterable<CarDTO> findByColour(@PathVariable String colour) {
        return carService.getCarByColour(Colour.valueOf(colour.toUpperCase()));
    }
}
