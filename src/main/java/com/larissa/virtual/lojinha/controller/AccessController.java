package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RestController
public class AccessController {

    @Autowired
    private AccessService accessService;


    @ResponseBody
    @PostMapping(value = "/saveAccess")
    public ResponseEntity<Access> saveAccess(@RequestBody Access access) throws ExceptionLoja {
        Boolean exists = accessService.existsByDescription(access.getDescription());
        if (exists){
            throw new ExceptionLoja("Já existe um acesso com essa descrição.");
        }
        Access accessSaved = accessService.save(access);
        return new ResponseEntity<>(accessSaved, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/deleteAccess")
    public ResponseEntity<?> deleteAccess(@RequestBody Access access){
        accessService.delete(access);
        return new ResponseEntity<>("Acesso removido", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/deleteAccessById/{id}")
    public ResponseEntity<?> deleteByIdAccess(@PathVariable Long id){
        accessService.deleteById(id);
        return new ResponseEntity<>("Acesso removido", HttpStatus.OK);
    }

    @GetMapping(value = "/getAccessById/{id}")
    public ResponseEntity<Access> getByIdAccess(@PathVariable Long id) throws ExceptionLoja {
        Optional<Access> access = accessService.getById(id);
        if (access.isEmpty()){
            throw new ExceptionLoja("Acesso não encontrado com id: " + id);
        }
        return new ResponseEntity<>(access.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAccess/{desc}")
    public ResponseEntity<List<Access>> findAccessByDesc(@PathVariable String desc){
        List<Access> access = accessService.findByDesc(desc);
        return new ResponseEntity<>(access, HttpStatus.OK);
    }
}
