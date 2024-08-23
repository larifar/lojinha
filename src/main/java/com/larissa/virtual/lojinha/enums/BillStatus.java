package com.larissa.virtual.lojinha.enums;

public enum BillStatus {
    EXPIRED("Vencido"),
    OPEN("Aberta"),
    PAYED("Quitada"),
    CHARGE("Cobrança");

    private String status;

    BillStatus(String status) {
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
