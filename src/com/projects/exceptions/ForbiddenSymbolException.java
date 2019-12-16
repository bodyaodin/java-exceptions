package com.projects.exceptions;

public class ForbiddenSymbolException extends DataException {

    public ForbiddenSymbolException() {
    }

    public ForbiddenSymbolException(String message) {
        super(message);
    }
}
