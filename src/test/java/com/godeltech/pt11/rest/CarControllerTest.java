package com.godeltech.pt11.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.pt11.PtApplication;
import com.godeltech.pt11.converter.CarMapper;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import com.godeltech.pt11.service.CarService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtApplication.class)
@TestPropertySource(locations = "classpath:application2.properties")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CarMapper carMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional(readOnly = true)
    public void getAllCars() throws Exception {
        String uri = "/cars";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void deleteCar() throws Exception {
        String uri = "/cars/1";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Transactional(readOnly = true)
    public void getCar() throws Exception {
        String uri = "/cars/1";
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void createCar() throws Exception {
        String uri = "/cars";
        Car car = Car
                .builder()
                .carId(2L)
                .model("A5")
                .colour(Colour.RED)
                .build();
        String carAsString = objectMapper.writeValueAsString(car);
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(carAsString))
                .andReturn();
        assertEquals(carMapper.fromEntity(car), carService.getCar(2L));
    }

    @Test
    public void updateCar() throws Exception {
        String uri = "/cars";
        Car car = Car
                .builder()
                .carId(1L)
                .model("A6")
                .colour(Colour.RED)
                .build();
        String carAsString = objectMapper.writeValueAsString(car);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(carAsString)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Transactional(readOnly = true)
    public void findCarByColour() throws Exception {
        String uri = "/cars/byColour/RED";
        Car car = Car
                .builder()
                .carId(1L)
                .model("A6")
                .colour(Colour.RED)
                .build();
        String carAsString = objectMapper.writeValueAsString(car);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(carAsString)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}