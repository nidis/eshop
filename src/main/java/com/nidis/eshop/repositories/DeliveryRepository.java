package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAll();
}
