package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class PermissionServiceLogicException extends GeneraleLogicException {
    public PermissionServiceLogicException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);

    }
}
