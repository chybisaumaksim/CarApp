package com.godeltech.pt11.converter;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class CarMapperTest {

    @Mock
    private CarMapper carMapper;

    private Car car;
    private CarDTO carDTO;

    @BeforeAll
    public void setUp() {
        car = new Car(1L, "A5", Colour.GREEN);
        carDTO = new CarDTO(1L, "A5", Colour.GREEN);
    }

    @Test
    public void toEntity() {
        when(carMapper.fromEntity(car)).thenReturn(carDTO);
        CarDTO actual = carMapper.fromEntity(car);
        assertEquals(actual, carDTO);
    }

    @Test
    public void fromEntity() {
        when(carMapper.toEntity(carDTO)).thenReturn(car);
        Car actual = carMapper.toEntity(carDTO);
        assertEquals(actual, car);
    }
}