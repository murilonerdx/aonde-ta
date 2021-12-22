package com.murilonerdx.aondeta.exceptions;

public class EmailNotFoundError extends RuntimeException {

    private String message;

    public EmailNotFoundError(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    protected EmailNotFoundError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
