package com.larissa.virtual.lojinha.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepDto(
        @JsonAlias("cep") String zipcode, @JsonAlias(value = "logradouro") String street,
        @JsonAlias("complemento") String complement, @JsonAlias("bairro") String neighbor,
        @JsonAlias("localidade") String city, String uf
        ) {
}
