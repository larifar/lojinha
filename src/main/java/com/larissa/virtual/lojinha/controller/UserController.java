package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.dto.ViaCepDto;
import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.Address;
import com.larissa.virtual.lojinha.model.User;
import com.larissa.virtual.lojinha.service.AddressService;
import com.larissa.virtual.lojinha.service.UserService;
import com.larissa.virtual.lojinha.util.ValidateCPF;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AddressService addressService;

    @ResponseBody
    @GetMapping("/viaCep/{cep}")
    public ResponseEntity<ViaCepDto> search(@PathVariable String cep){
        return new ResponseEntity<>(addressService.searchCep(cep), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/getUsers/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name){
        return new ResponseEntity<>(service.getUsersByName(name), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/getUser/{cpf}")
    public ResponseEntity<User> getUserByCPF(@PathVariable String cpf){
        return new ResponseEntity<>(service.findUserByCPF(cpf), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) throws ExceptionLoja {
        if (user == null){
            throw new ExceptionLoja("Usuário não pode ser NULL.");
        }

        if (user.getId() == null && service.findUserByEmail(user.getEmail()) != null){
            throw new ExceptionLoja("Esse email já foi cadastrado em outra conta.");
        }

        if (user.getId() == null && service.findUserByCPF(user.getCPF()) != null){
            throw new ExceptionLoja("Esse CPF já foi cadastrado em outra conta.");
        }

        if (!ValidateCPF.isCPF(user.getCPF())){
            throw new ExceptionLoja("Esse CPF não é válido.");
        }

        for (Address address : user.getAddresses()) {
            address.setUser(user);
        }

        if(user.getId() == null || user.getId() <=0){
            for (Address address : user.getAddresses()){
                addressService.saveAddressCep(address);
            }
        } else {
            for (Address address : user.getAddresses()){
                if(!addressService.isCepEquals(address.getId(), address.getZipcode())){
                    addressService.saveAddressCep(address);
                }
            }
        }

        user = service.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
