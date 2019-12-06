package com.projects.exceptions;

public class IncorrectValueException extends DataException {

    public IncorrectValueException() {
    }

    public IncorrectValueException(String message) {
        super(message);
    }
}
