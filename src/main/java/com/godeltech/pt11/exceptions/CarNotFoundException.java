package com.godeltech.pt11.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Car id not found : " + id);
        log.error("Car id not found : " + id);
    }
}
