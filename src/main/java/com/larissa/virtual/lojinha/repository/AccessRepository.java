package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccessRepository extends JpaRepository<Access, Long> {
    @Query("SELECT a FROM Access a WHERE LOWER(a.description) LIKE LOWER(CONCAT('%', :desc, '%'))")
    List<Access> findAccessByDescription(@Param("desc") String desc);
}
