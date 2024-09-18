package com.larissa.virtual.lojinha.service;

import com.larissa.virtual.lojinha.model.Product;
import com.larissa.virtual.lojinha.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Boolean existsByName(String name){
        return repository.findByName(name) != null;
    }

    public Product getReferenceById(Long aLong) {
        return repository.getReferenceById(aLong);
    }

    public <S extends Product> List<S> findAll(Example<S> example) {
        return repository.findAll(example);
    }

    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllById(Iterable<Long> longs) {
        return repository.findAllById(longs);
    }

    public <S extends Product> S save(S entity) {
        return repository.save(entity);
    }

    public Optional<Product> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return repository.existsById(aLong);
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    public void delete(Product entity) {
        repository.delete(entity);
    }

    public List<Product> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public <S extends Product> boolean exists(Example<S> example) {
        return repository.exists(example);
    }
}
