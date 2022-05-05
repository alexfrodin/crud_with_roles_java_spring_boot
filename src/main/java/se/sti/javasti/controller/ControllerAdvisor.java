package se.sti.javasti.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.sti.javasti.controller.response.ErrorResponse;
import se.sti.javasti.exception.RoleNotExistsException;
import se.sti.javasti.exception.UserAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST).getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotExistsException.class)
    public ResponseEntity<Object> handleRoleNotExistsException(RoleNotExistsException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST).getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

}
