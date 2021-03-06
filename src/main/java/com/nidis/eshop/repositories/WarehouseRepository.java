package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findAll();
}
