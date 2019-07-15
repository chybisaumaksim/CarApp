package com.godeltech.pt11.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.pt11.PtApplication;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.entity.Car;
import com.godeltech.pt11.entity.enums.Colour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;

import static com.godeltech.pt11.entity.enums.Colour.GREEN;
import static com.godeltech.pt11.entity.enums.Colour.RED;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PtApplication.class)
public class CarControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:";

    private String contextPath = "/pt11";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAllCars() {
        ResponseEntity<List<Car>> carsResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Car>>() {
                        });
        Assert.assertEquals(HttpStatus.OK, carsResponseEntity.getStatusCode());
    }

    @Test
    public void createCar() throws JsonProcessingException {
        Car car=new Car(1L, "A10", RED);
        ResponseEntity<CarDTO> carResponseEntity = restTemplate.exchange(
            RequestEntity.post(URI.create(baseUrl + port + contextPath + "/cars"))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(mapper.writeValueAsString(car)), CarDTO.class);
        Assert.assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());
}

    @Test
    public void findByColour() {
        final Colour colour = GREEN;
        ResponseEntity<List<Car>> carResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/byColour/" + colour, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });
        Assert.assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());
    }

    @Test
    public void findById() {
        ResponseEntity<CarDTO> carResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/" + 1L, HttpMethod.GET, null, new ParameterizedTypeReference<CarDTO>() {
                });
        Assert.assertEquals(HttpStatus.OK, carResponseEntity.getStatusCode());
    }


//    @Test
//    public void update() throws JsonProcessingException {
//        CarDTO carDto = new CarDTO(1L, "A4", GREEN);
//        ResponseEntity<CarDTO> employeeResponseEntity = restTemplate.exchange(
//                RequestEntity.put(URI.create(baseUrl + port + contextPath + "/cars/1"))
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .body(mapper.writeValueAsString(carDto)), CarDTO.class);
//        Assert.assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
//    }

    @AfterAll
    @Test
    public void deleteEmployee() {
        final Long id = 2L;
        ResponseEntity<Car> employeeResponseEntity = restTemplate
                .exchange(baseUrl + port + contextPath + "/cars/" + id, HttpMethod.DELETE, null, new ParameterizedTypeReference<Car>() {
                });
        Assert.assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
    }
}