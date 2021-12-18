package com.murilonerdx.aondeta.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String mssage;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public String getMssage() {
        return mssage;
    }

    public void setMssage(String mssage) {
        this.mssage = mssage;
    }
}
