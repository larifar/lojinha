package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    public Access save(Access access){
        return accessRepository.save(access);
    }
}
