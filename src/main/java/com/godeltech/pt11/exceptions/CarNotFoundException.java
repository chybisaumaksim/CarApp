package com.godeltech.pt11.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CarNotFoundException extends RuntimeException {

    private final Long id;

    public CarNotFoundException(Long id) {
        super(" Car with id not found : " + id);
        this.id = id;
    }
}
