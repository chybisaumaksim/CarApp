package com.godeltech.pt11.service;

import com.godeltech.pt11.converter.CarMapper;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.repository.CarRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    private Car car;
    private CarDTO carDTO;

    @BeforeEach
    public void setCar() {
        car = new Car(1L, "A5", Colour.GREEN);
        carDTO = new CarDTO(1L, "A5", Colour.GREEN);
    }

    @Test
    @Transactional(readOnly = true)
    public void getAllCars() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());
        Iterable<CarDTO> actual = carService.getAllCars();
        assertEquals(Collections.EMPTY_LIST, actual);
    }

    @Test
    public void deleteCar() {
        carService.deleteCar(anyLong());
        verify(carRepository, Mockito.times(1)).deleteById(anyLong());
    }

    @Test
    public void createCar() {
        when(carRepository.save(car)).thenReturn(car);
        CarDTO actual = carService.createCar(carMapper.fromEntity(car));
        assertEquals(carDTO, actual);
    }

    @Ignore
    @Test
    @Transactional(readOnly = true)
    public void getCar() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.ofNullable(car));
        when(carMapper.fromEntity(car)).thenReturn(carDTO);
        when(carMapper.toEntity(carDTO)).thenReturn(car);
        CarDTO actual = carService.getCar(1L);
        assertEquals(carDTO, actual);
    }

    @Test
    public void updateCar() {
        when(carRepository.save(car)).thenReturn(car);
        CarDTO actual = carService.updateCar(carMapper.fromEntity(car));
        assertEquals(carDTO, actual);
    }

    @Test
    @Transactional(readOnly = true)
    public void getCarByColour() {
        when(carRepository.findByColour(Colour.RED)).thenReturn(Collections.emptyList());
        Iterable<CarDTO> actual = carService.getCarByColour(Colour.RED);
        assertEquals(Collections.EMPTY_LIST, actual);
    }
}