package com.larissa.virtual.lojinha.repository;

import com.larissa.virtual.lojinha.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
