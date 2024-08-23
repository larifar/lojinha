package com.larissa.virtual.lojinha.enums;

public enum AddressType {

    BILL("Cobrança"),
    DELIVERY("Entrega");

    private String description;
    AddressType(String description){
        this.description=description;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return this.description;
    }
}
