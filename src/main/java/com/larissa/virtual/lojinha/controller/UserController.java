package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.Address;
import com.larissa.virtual.lojinha.model.User;
import com.larissa.virtual.lojinha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @PostMapping(value = "/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws ExceptionLoja {
        if (user == null){
            throw new ExceptionLoja("Usuário não pode ser NULL.");
        }

        if (user.getId() == null && service.findUserByEmail(user.getEmail()) != null){
            throw new ExceptionLoja("Esse email já foi cadastrado em outra conta.");
        }

        if (user.getId() == null && service.findUserByCPF(user.getCPF()) != null){
            throw new ExceptionLoja("Esse CPF já foi cadastrado em outra conta.");
        }

        for (Address address : user.getAddresses()) {
            address.setUser(user);
        }

        user = service.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
