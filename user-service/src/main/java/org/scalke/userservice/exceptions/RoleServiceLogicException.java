package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class RoleServiceLogicException extends GeneraleLogicException {
    public RoleServiceLogicException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
