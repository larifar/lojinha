package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AccessController {

    @Autowired
    private AccessService accessService;


    @PostMapping(value = "/saveAccess")
    public ResponseEntity<Access> saveAccess(@RequestBody Access access){
        Access accessSaved = accessService.save(access);
        return new ResponseEntity<>(accessSaved, HttpStatus.OK);
    }
}
