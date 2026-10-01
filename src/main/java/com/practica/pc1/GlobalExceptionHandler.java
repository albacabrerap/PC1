package com.practica.pc1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EventNotFoundException.class})
    public ResponseEntity<String> handleNotFound(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({UserAlreadyExists.class})
    public ResponseEntity<String> handleConflict(HttpClientErrorException.Conflict ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({InvalidCredentialsException.class})
    public ResponseEntity<String> handleInvalidCreds(HttpClientErrorException.Unauthorized ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    @ExceptionHandler({TicketTypeNotFound.class})
    public ResponseEntity<String> handleTicketNotFound(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({EventNotPublishedException.class})
    public ResponseEntity<String> handleEventConflict(HttpClientErrorException.Conflict ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({TicketTypeFullException.class})
    public ResponseEntity<String> handleTicketFull(HttpClientErrorException.Conflict ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({AlreadyRegisteredException.class})
    public ResponseEntity<String> handleRegisteredalready(HttpClientErrorException.Conflict ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({ForbiddenEventActionException.class})
    public ResponseEntity<String> handleforbidden(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

}
