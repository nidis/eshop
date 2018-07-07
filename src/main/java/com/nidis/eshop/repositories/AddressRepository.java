package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();
}
