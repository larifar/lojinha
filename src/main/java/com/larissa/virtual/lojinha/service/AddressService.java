package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.dto.ViaCepDto;
import com.larissa.virtual.lojinha.model.Address;
import com.larissa.virtual.lojinha.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Boolean isCepEquals(Long id, String cep){
        String cepRepo = addressRepository.getReferenceById(id).getZipcode();
        return cep.equals(cepRepo);
    }

    public ViaCepDto searchCep(String cep){
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/"+ cep +"/json/", ViaCepDto.class).getBody();
    }

    public void saveAddressCep(Address address){
        ViaCepDto cep = searchCep(address.getZipcode());
        address.setCity(cep.city());
        address.setComplement(cep.complement());
        address.setNeighbor(cep.neighbor());
        address.setUF(cep.uf());
        address.setStreet(cep.street());
    }
}
