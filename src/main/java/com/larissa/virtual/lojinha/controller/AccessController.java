package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccessController {

    @Autowired
    private AccessService accessService;

    public Access saveAccess(Access access){
        return accessService.save(access);
    }
}
