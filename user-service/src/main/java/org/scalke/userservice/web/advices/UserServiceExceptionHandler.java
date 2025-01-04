package org.scalke.userservice.web.advices;

import org.scalke.models.ErrorDetails;
import org.scalke.userservice.exceptions.UserNotFoundException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserServiceExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.status).body(ErrorDetails.builder()
                .message(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(UserServiceLogicException.class)
    public ResponseEntity<?> handleUserServiceLogicException(UserServiceLogicException e) {
        return ResponseEntity.status(e.status).body(ErrorDetails.builder()
                .message(e.getMessage())
                .build()
        );
    }
}
