package com.murilonerdx.aondeta.entities.enums;

public enum StatusLocal {
    DANGER("Perigoso"), MEDIUM("Mediano"), NORMAL("Normal"), LOW_RISK("Risco baixo");

    private String status;

    StatusLocal(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
