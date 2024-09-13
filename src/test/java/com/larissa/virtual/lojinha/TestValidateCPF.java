package com.larissa.virtual.lojinha;

import com.larissa.virtual.lojinha.util.ValidateCPF;

public class TestValidateCPF {
    public static void main(String[] args) {
        Boolean cpf = ValidateCPF.isCPF("323.614.308-86");
        System.out.println(cpf);
    }
}
