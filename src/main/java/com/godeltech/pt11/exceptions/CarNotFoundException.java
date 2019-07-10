package com.godeltech.pt11.exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id) {
        super("Car id not found : " + id);
    }
}
