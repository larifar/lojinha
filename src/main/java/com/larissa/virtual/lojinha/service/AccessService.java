package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.repository.AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessService {
    @Autowired
    private AccessRepository accessRepository;

    public List<Access> getAll(){
        return accessRepository.findAll().stream().toList();
    }

    public Boolean existsByDescription(String desc){
        return accessRepository.existsByDescriptionIgnoreCase(desc);
    }

    public Access save(Access access){
        return accessRepository.save(access);
    }

    public void delete(Access access){
        accessRepository.delete(access);
    }

    public void deleteById(Long id){
        accessRepository.deleteById(id);
    }

    public Optional<Access> getById(Long id) {
        return accessRepository.findById(id);
    }

    public List<Access> findByDesc(String desc) {
        return accessRepository.findAccessByDescription(desc);
    }
}
