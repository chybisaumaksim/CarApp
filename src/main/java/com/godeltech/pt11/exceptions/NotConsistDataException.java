package com.godeltech.pt11.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class NotConsistDataException extends RuntimeException {

    private long expectedId;
    private long foundId;

    public NotConsistDataException(long expectedId, long foundId) {
        super(" No consistent data exception. Mismatch ID. Expected id " + expectedId + " but found " + foundId);
        this.expectedId = expectedId;
        this.foundId = foundId;
    }
}
