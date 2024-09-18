package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where upper(trim(p.name)) = upper(trim(:name))")
    Product findByName(String name);
}
