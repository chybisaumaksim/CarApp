package com.godeltech.pt11.service;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.dto.CarDTOCreate;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.repository.CarRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car;
    private CarDTO carDTO;
    private CarDTOCreate carDTOCreate;

    @BeforeEach
    public void setUp() {
        carDTO = CarDTO
                .builder()
                .carId(1L)
                .model("A5")
                .colour(Colour.GREEN)
                .build();
        carDTOCreate = CarDTOCreate
                .builder()
                .model("A5")
                .colour(Colour.GREEN)
                .build();
        car = Car
                .builder()
                .carId(1L)
                .model("A5")
                .colour(Colour.GREEN)
                .build();
    }

    @Test
    public void getAllCarsShouldReturnEmptyList() {
        when(carRepository.findAll()).thenReturn(Collections.emptyList());
        Iterable<CarDTO> actual = carService.getAllCars();
        assertEquals(Collections.EMPTY_LIST, actual);
    }

    @Test
    public void getAllCarsShouldReturnIterableCars() {
        Car car = Car
                .builder()
                .carId(1L)
                .model("A5")
                .colour(Colour.GREEN)
                .build();
        Car carTwo = Car
                .builder()
                .carId(1L)
                .model("A5")
                .colour(Colour.GREEN)
                .build();
        // given
        List<Car> cars = List.of(car, carTwo);
        when(carRepository.findAll()).thenReturn(cars);
        // when
        Iterable<CarDTO> actual = carService.getAllCars();
        // then
        assertEquals(cars, actual);
    }

    @Test
    public void deleteCar() {
        carService.deleteCar(anyLong());
        verify(carRepository).deleteById(anyLong());
    }

//    @Test
//    public void createCar() {
//        when(carRepository.save(car)).thenReturn(carDTOCreate);
//        CarDTO actual = carService.createCar(carMapper.fromEntity(car));
//        assertEquals(carDTOCreate, actual);
//    }

    @Test
    public void getCar() {
        when(carRepository.findById(anyLong()))
                .thenReturn(Optional.of(
                        Optional.ofNullable(car).
                                orElse(Car
                                        .builder()
                                        .carId(1L)
                                        .model("A6")
                                        .colour(Colour.RED)
                                        .build())));
        CarDTO actual = carService.getCar(1L);
        assertEquals(carDTO, actual);
    }

//    @Ignore
//    @Test
//    public void updateCar() {
//        Car car = Car
//                .builder()
//                .carId(1L)
//                .model("A5")
//                .colour(Colour.GREEN)
//                .build();
//        CarDTO carDTO = CarDTO
//                .builder()
//                .carId(1L)
//                .model("A5")
//                .colour(Colour.GREEN)
//                .build();
//        when(carRepository.save(car)).thenReturn(car);
//        when(carMapper.fromEntity(car)).thenReturn(carDTO);
//        CarDTO actual = carService.updateCar(carDTO, 1L);
//        assertEquals(carDTO, actual);
//    }

    @Test
    public void getCarByColour() {
        when(carRepository.findByColour(Colour.RED)).thenReturn(Collections.emptyList());
        Iterable<CarDTO> actual = carService.getCarByColour(Colour.RED);
        assertEquals(Collections.EMPTY_LIST, actual);
    }
}