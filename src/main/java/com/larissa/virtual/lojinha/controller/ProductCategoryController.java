package com.larissa.virtual.lojinha.controller;

import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.ProductCategoryModel;
import com.larissa.virtual.lojinha.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService service;

    @ResponseBody
    @GetMapping
    public ResponseEntity<List<ProductCategoryModel>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Long id){
        return new ResponseEntity<>("Categoria deletada", HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<ProductCategoryModel> save(@RequestBody ProductCategoryModel model) throws ExceptionLoja {
        if (model.getId() <= 0 && service.existsByName(model.getName())){
            throw new ExceptionLoja("Já existe uma categoria com esse nome: " + model.getName());
        }
        return new ResponseEntity<>(service.save(model), HttpStatus.CREATED);
    }
}
