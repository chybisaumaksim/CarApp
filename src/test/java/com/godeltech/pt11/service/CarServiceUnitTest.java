package com.godeltech.pt11.service;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.dto.CarDTOCreate;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.exceptions.CarNotFoundException;
import com.godeltech.pt11.exceptions.NotConsistDataException;
import com.godeltech.pt11.repository.CarRepository;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceUnitTest{

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarService carService;

    private Car car;
    private CarDTO carDTO;
    private CarDTOCreate carDTOCreate;
    private Car carTwo;

    @Before
    public void setUp() {
        carDTO = CarDTO
                .builder()
                .id(1L)
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
                .id(1L)
                .model("A5")
                .colour(Colour.GREEN)
                .build();
        carTwo = Car
                .builder()
                .id(1L)
                .model("A6")
                .colour(Colour.RED)
                .build();
    }

    @Test
    public void getAllCarsReturnsEmptyList() {
        // given
        when(carRepository.findAll()).thenReturn(Collections.emptyList());
        // when
        Iterable<CarDTO> actual = carService.getAllCars();
        // then
        assertEquals(Collections.EMPTY_LIST, actual);
        verify(carRepository).findAll();
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void getAllCarsShouldReturnListOfCars() {
        // given
        ArrayList<Car> cars = Lists.newArrayList(car, car);
        when(carRepository.findAll()).thenReturn(cars);
        when(modelMapper.map(carDTO, Car.class)).thenReturn(car);
        // when
        Iterable<Car> actual = StreamSupport
                .stream(carService.getAllCars().spliterator(), false)
                .map(car -> modelMapper.map(carDTO, Car.class))
                .collect(Collectors.toList());
        // then
        assertEquals(cars, actual);
        verify(carRepository).findAll();
        verify(modelMapper, times(2)).map(car, CarDTO.class);
        verify(modelMapper, times(2)).map(carDTO, Car.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }


    @Test(expected = CarNotFoundException.class)
    public void deleteCarShouldRunException() {
        // when
        carService.deleteCar(anyLong());
        // then
        verify(carRepository).deleteById(anyLong());
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void deleteCarShouldDeleteCarById() {
        // given
        Long id = 1L;
        // when
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        // then
        carService.deleteCar(id);
        // then
        verify(carRepository).findById(id);
        verify(carRepository).delete(car);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void createCarShouldCreateNewCar() {
        // given
        when(modelMapper.map(carDTOCreate, Car.class)).thenReturn(car);
        when(modelMapper.map(car, CarDTO.class)).thenReturn(carDTO);
        when(carRepository.save(car)).thenReturn(car);
        // when
        CarDTO actual = carService.createCar(carDTOCreate);
        // then
        assertEquals(carDTO, actual);
        verify(carRepository).save(car);
        verify(modelMapper, times(1)).map(car, CarDTO.class);
        verify(modelMapper, times(1)).map(carDTOCreate, Car.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void getOneCarById() {
        // given
        Long id = 1L;
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        when(modelMapper.map(car, CarDTO.class)).thenReturn(carDTO);
        // when
        CarDTO actual = carService.getCar(1L);
        // then
        assertEquals(carDTO, actual);
        verify(carRepository).findById(1L);
        verify(modelMapper).map(car, CarDTO.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test(expected = NotConsistDataException.class)
    public void updateCarThrowNotConsistDataException() {
        // given
        Long id = 2L;
        // when
        carService.updateCar(carDTO, id);
        // then
        verify(carRepository).save(car);
        verify(modelMapper, times(1)).map(carDTO, Car.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test(expected = CarNotFoundException.class)
    public void updateCarThrowCarNotFoundException() {
        // given
        Long id = 1L;
        // when
        carService.updateCar(carDTO, id);
        // then
        verify(carRepository).save(car);
        verify(modelMapper).map(carDTO, Car.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void updateCarShouldRunPositive() {
        // given
        Long id = 1L;
        when(carRepository.findById(id)).thenReturn(Optional.ofNullable(car));
        when(modelMapper.map(carDTO, Car.class)).thenReturn(car);
        when(modelMapper.map(car, CarDTO.class)).thenReturn(carDTO);
        when(carRepository.save(car)).thenReturn(carTwo);
        // when
        CarDTO actual = carService.updateCar(carDTO, id);
        // then
        assertEquals(carDTO, actual);
        verify(carRepository).save(car);
        verify(carRepository).findById(id);
        verify(modelMapper).map(carDTO, Car.class);
        verify(modelMapper).map(car, CarDTO.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void getCarByColourReturnsEmptyList() {
        // given
        when(carRepository.findByColour(Colour.RED)).thenReturn(Collections.emptyList());
        // when
        Iterable<CarDTO> actual = carService.getCarByColour(Colour.RED);
        // then
        assertEquals(Collections.EMPTY_LIST, actual);
        verify(carRepository).findByColour(Colour.RED);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }

    @Test
    public void getCarByColourShouldReturnListOfRedCars() {
        // given
        List<Car> cars = Lists.newArrayList(car, car);
        List<CarDTO> expected = Lists.newArrayList(carDTO, carDTO);
        when(carRepository.findByColour(Colour.GREEN)).thenReturn(cars);
        when(modelMapper.map(car, CarDTO.class)).thenReturn(carDTO);
        // when
        Iterable<CarDTO> actual = carService.getCarByColour(Colour.GREEN);
        // then
        assertEquals(expected, actual);
        verify(carRepository).findByColour(Colour.GREEN);
        verify(modelMapper, times(2)).map(car, CarDTO.class);
        verifyNoMoreInteractions(modelMapper, carRepository);
    }
}