package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class UserServiceLogicException extends GeneraleLogicException{
    public UserServiceLogicException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
