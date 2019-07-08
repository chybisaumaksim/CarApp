package com.godeltech.pt11.converter;

import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.Colour;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CarMapperTest {

    private CarMapper carMapper;

   public CarMapperTest() {
        carMapper = new CarMapper();
    }

    private Car car = new Car(1L, "A5", Colour.GREEN);
    private CarDTO carDTO = new CarDTO(1L, "A5", Colour.GREEN);

    @Test
    public void toEntity() {
        CarDTO actual = carMapper.fromEntity(car);
        assertEquals(actual, carDTO);
    }

    @Test
    public void fromEntity() {
        Car actual = carMapper.toEntity(carDTO);
        assertEquals(actual, car);
    }
}