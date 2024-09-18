package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.ProductCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryModel, Long> {

    @Query("select c from ProductCategoryModel c where upper(c.name) = upper(?1)")
    ProductCategoryModel findByName(String name);
}
