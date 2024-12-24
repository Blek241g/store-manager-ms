package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends GeneraleLogicException {
    public UserNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
