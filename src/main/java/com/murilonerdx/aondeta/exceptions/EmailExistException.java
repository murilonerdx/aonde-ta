package com.murilonerdx.aondeta.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class EmailExistException extends DataIntegrityViolationException {
    public EmailExistException(String msg) {
        super(msg);
    }

    public EmailExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
