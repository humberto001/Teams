package com.example.wordcup.team.infra.exceptions;

public class TeamValidationException extends RuntimeException {

    public TeamValidationException(String message) {
        super(message);

    }
}
