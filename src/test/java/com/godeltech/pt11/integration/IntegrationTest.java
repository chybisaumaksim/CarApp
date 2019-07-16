package com.godeltech.pt11.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godeltech.pt11.dto.CarDTO;
import com.godeltech.pt11.dto.CarDTOCreate;
import com.godeltech.pt11.entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.godeltech.pt11.entity.enums.Colour.GREEN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private Car car;
    private CarDTO carDTO;
    private CarDTOCreate carDTOCreate;

    @Before
    public void setUp() {
        carDTO = CarDTO
                .builder()
                .id(1L)
                .model("A5")
                .colour(GREEN)
                .build();
        carDTOCreate = CarDTOCreate
                .builder()
                .model("A5")
                .colour(GREEN)
                .build();
        car = Car
                .builder()
                .id(1L)
                .model("A5")
                .colour(GREEN)
                .build();
    }

    @Test
    public void getCarByIdReturnsNotFoundException() throws Exception {
        mockMvc.perform(get("/cars/124"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error")
                        .value(" Car with id not found : 124"));
    }

    @Test
    public void getCarByIdReturnsBadRequestMessage() throws Exception {
        mockMvc.perform(get("/cars/-124"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error")
                        .value("getCar.id: must be greater than 0"));
    }

    @Test
    public void getAllCarsReturnsNotFoundException() throws Exception {
        mockMvc.perform(get("/cars/124"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error")
                        .value(" Car with id not found : 124"));
    }

    @Test
    public void deleteCarReturnsNotFoundException() throws Exception {
        mockMvc.perform(get("/cars/22"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error")
                        .value(" Car with id not found : 22"));
    }

    @Test
    public void deleteCarReturnsBadRequestMessage() throws Exception {
        mockMvc.perform(get("/cars/-22"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error")
                        .value("getCar.id: must be greater than 0"));
    }

    @Test
    public void createCarCreatesNewCar() throws Exception {
        mockMvc.perform(post("/cars")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(carDTOCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("model").value("A5"))
                .andExpect(jsonPath("colour").value("GREEN"));
    }

    @Test
    public void updateCarReturnsNotFoundException() throws Exception {
        carDTO.setId(888L);
        mockMvc.perform(put("/cars/888")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(carDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("error")
                        .value(" Car with id not found : 888"));
    }

    @Test
    public void updateCarPositiveTest() throws Exception {
        carDTO.setId(3L);
        mockMvc.perform(put("/cars/3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(carDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCarReturnsNotConsistDataException() throws Exception {
        carDTO.setId(5L);
        mockMvc.perform(put("/cars/3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(carDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error")
                        .value(" No consistent data exception. Mismatch ID. Expected id 5 but found 3"));
    }

    @Test
    public void findCarByColourReturnStatusOk() throws Exception {
        mockMvc.perform(get("/cars/byColour/GREEN"))
                .andExpect(status().isOk());
    }
}