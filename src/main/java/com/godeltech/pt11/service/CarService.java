package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarMapper;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public Iterable<CarDTO> getAllCars() {
        List<CarDTO> carDTOList = new ArrayList<>();
        carRepository.
                findAll().
                forEach(car -> carDTOList.add(carMapper.fromEntity(car)));
        return carDTOList;
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarDTO createCar(CarDTO carDTO) {
        return carMapper.fromEntity(carRepository.save(carMapper.toEntity(carDTO)));
    }

    public CarDTO getCar(Long id) {
        return carMapper.fromEntity(carRepository.findById(id).orElse(new Car()));
    }

    public CarDTO updateCar(CarDTO carDTO) {
        return carMapper.fromEntity(carRepository.save(carMapper.toEntity(carDTO)));
    }

    public List<CarDTO> getCarByColour(Colour colour) {
        List<CarDTO> carDTOList = new ArrayList<>();
        carRepository.
                findByColour(colour).
                forEach(car -> carDTOList.add(carMapper.fromEntity(car)));
        return carDTOList;
    }
}
