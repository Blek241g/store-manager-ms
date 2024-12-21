package org.scalke.storeservice.exceptions;

import org.springframework.http.HttpStatus;

public class MerchnatServiceLogicException extends Exception {
    public HttpStatus status;

    public MerchnatServiceLogicException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
