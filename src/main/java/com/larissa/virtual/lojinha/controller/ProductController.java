package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.Product;
import com.larissa.virtual.lojinha.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @ResponseBody
    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) throws ExceptionLoja {
        if (product.getId() <=0 && service.existsByName(product.getName())){
            throw new ExceptionLoja("Já existe um produto cadastrado com esse nome.");
        }
        return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
    }
}
