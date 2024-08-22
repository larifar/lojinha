package com.larissa.virtual.lojinha.enums;

public enum InvoiceStatus {
    EXPIRED("Vencido"),
    OPEN("Aberta"),
    PAYED("Quitada"),
    CHARGE("Cobrança");

    private String status;

    InvoiceStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return this.status;
    }
}
