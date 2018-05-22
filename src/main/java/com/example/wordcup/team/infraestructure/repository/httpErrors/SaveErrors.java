package com.example.wordcup.team.infraestructure.repository.httpErrors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SaveErrors {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> blabla (IllegalArgumentException e){

        ErrorMessage message = new ErrorMessage(e.getMessage());

        return ResponseEntity.badRequest().body(message);
    }
}
