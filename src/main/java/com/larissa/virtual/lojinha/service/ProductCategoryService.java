package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.model.ProductCategoryModel;
import com.larissa.virtual.lojinha.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    public ProductCategoryModel save(ProductCategoryModel model){
        return repository.save(model);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public ProductCategoryModel findById(Long id){
        return repository.getReferenceById(id);
    }

    public List<ProductCategoryModel> getAll(){
        return repository.findAll().stream().toList();
    }

    public ProductCategoryModel findByName(String name){
        return repository.findByName(name);
    }

    public Boolean existsByName(String name){
        return repository.findByName(name) != null;
    }
}
