package com.example.wordcup.team.infraestructure.repository.httpErrors;

import com.example.wordcup.team.infra.exceptions.FindTeamValidationException;
import com.example.wordcup.team.infra.exceptions.TeamValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControl {

    @ExceptionHandler(TeamValidationException.class)
    public ResponseEntity<ErrorMessage> handleSaveTeamException (TeamValidationException e){

        ErrorMessage message = new ErrorMessage(e.getMessage());

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(FindTeamValidationException.class)
    public ResponseEntity<ErrorMessage> handleFindTeamException (FindTeamValidationException e){

        //ErrorMessage message = new ErrorMessage(e.getMessage());

        return ResponseEntity.notFound().build();
    }
}
