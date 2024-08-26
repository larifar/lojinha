package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AccessRepository extends JpaRepository<Access, Long> {
}
