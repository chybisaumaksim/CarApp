package com.godeltech.pt11.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotConsistDataException extends RuntimeException {

    public NotConsistDataException() {
        super("No consistent data exception. Mismatch ID");
    }
}
